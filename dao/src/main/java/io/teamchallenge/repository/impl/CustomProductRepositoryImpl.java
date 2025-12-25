package io.teamchallenge.repository.impl;

import io.teamchallenge.dto.product.ProductMinMaxPriceDto;
import io.teamchallenge.entity.Product;
import io.teamchallenge.entity.reviews.Review;
import io.teamchallenge.repository.CustomProductRepository;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

/**
 * Implementation of a custom product repository interface that provides additional
 * functionality beyond the standard CRUD operations.
 * This implementation directly interacts with the underlying database through the EntityManager.
 *
 * @author Niktia Malov
 */
@Repository
@RequiredArgsConstructor
public class CustomProductRepositoryImpl implements CustomProductRepository {
    private final EntityManager entityManager;

    /**
     * Finds the minimum and maximum prices of products based on the given specification.
     * Executes a CriteriaQuery to retrieve the minimum and maximum prices of products.
     * If a Specification is provided, filters the products based on the given criteria.
     *
     * @param specification The Specification to filter products (can be null).
     * @return A ProductMinMaxPriceDto object containing the minimum and maximum prices.
     */
    @Override
    public ProductMinMaxPriceDto findProductMinMaxPrice(@Nullable Specification<Product> specification) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductMinMaxPriceDto> query = cb.createQuery(ProductMinMaxPriceDto.class);

        Root<Product> root = query.from(Product.class);
        query.multiselect(
                cb.min(root.get("price")).alias("min"),
                cb.max(root.get("price")).alias("max"));

        if (Objects.nonNull(specification)) {
            query.where(specification.toPredicate(root, query, cb));
        }

        TypedQuery<ProductMinMaxPriceDto> typedQuery = entityManager.createQuery(query);

        return typedQuery.getSingleResult();
    }

    /**
     * Finds all product IDs based on the given specification and pagination parameters.
     * Executes a CriteriaQuery to retrieve the IDs of products.
     * Applies pagination to limit the number of results.
     * If a Specification is provided, filters the products based on the given criteria.
     *
     * @param specification The Specification to filter products (can be null).
     * @param pageable      The pagination parameters.
     * @return A Page object containing the IDs of products.
     */


    @Override
    public Page<Long> findAllProductIds(@Nullable Specification<Product> specification, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Product> root = query.from(Product.class);

        query.select(root.get("id"));
        if (Objects.nonNull(specification)) {
            query.where(specification.toPredicate(root, query, criteriaBuilder));
        }
        query.groupBy(root.get("id"));
        addSortPartToQuery(pageable, query, root, criteriaBuilder);

        List<Long> productIds = entityManager.createQuery(query).setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize()).getResultList();

        var countQuery = criteriaBuilder.createQuery(Long.class);
        var rootCount = countQuery.from(Product.class);
        countQuery.select(criteriaBuilder.countDistinct(rootCount.get("id")));


        if (Objects.nonNull(specification)) {
            countQuery.where(specification.toPredicate(rootCount, countQuery, criteriaBuilder));
        }

        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(productIds, pageable, count);
    }

    private void addSortPartToQuery(Pageable pageable, CriteriaQuery<Long> query, Root<Product> root,
                                    CriteriaBuilder criteriaBuilder) {
        List<Order> orderList = new ArrayList<>();

        orderList.add(criteriaBuilder.desc(criteriaBuilder.selectCase()
                        .when(criteriaBuilder.greaterThan(root.get("quantity"), 0), 1)
                        .otherwise(0)
                )
        );

        if (Objects.nonNull(pageable.getSort().getOrderFor("price"))) {
            var priceOrder = pageable.getSort().getOrderFor("price");
            var priceSort = Sort.by(priceOrder);
            orderList.addAll(QueryUtils.toOrders(priceSort, root, criteriaBuilder));
        } else {
            Subquery<Double> subquery = query.subquery(Double.class);
            Root<Review> reviewRoot = subquery.from(Review.class);
            subquery.select(criteriaBuilder.coalesce(criteriaBuilder.avg(reviewRoot.<Double>get("rate")), 0.0))
                    .where(criteriaBuilder.equal(reviewRoot.get("id").get("productId"), root.get("id")));

            var ratingOrder = pageable.getSort().getOrderFor("rating");

            orderList.add((ratingOrder == null || ratingOrder.isDescending())
                    ? criteriaBuilder.desc(subquery)
                    : criteriaBuilder.asc(subquery));
        }
        query.orderBy(orderList);
    }

    @Override
    public Page<Long> findSearchProductIds(String query, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Product> root = cq.from(Product.class);

        cq.select(root.get("id"));

        Predicate namePredicate =
                cb.like(cb.lower(root.get("name")), "%" + query.toLowerCase() + "%");

        cq.where(namePredicate);
        cq.groupBy(root.get("id"));

        addSortPartToQuery(pageable, cq, root, cb);

        List<Long> ids = entityManager.createQuery(cq)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        // count
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Product> countRoot = countQuery.from(Product.class);
        countQuery.select(cb.countDistinct(countRoot.get("id")));
        countQuery.where(
                cb.like(cb.lower(countRoot.get("name")), "%" + query.toLowerCase() + "%")
        );

        Long total = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(ids, pageable, total);
    }


}
