package io.teamchallenge.controller;

import io.teamchallenge.dto.product.ShortProductResponseDto;
import io.teamchallenge.service.impl.FavoriteProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("api/v1/favorite-products")
@RequiredArgsConstructor
public class FavoriteProductsController {
    private final FavoriteProductsService favoriteProductsService;

    @GetMapping
    public ResponseEntity<Set<ShortProductResponseDto>> getAll(Principal principal) {
        return ResponseEntity.ok(favoriteProductsService.getAll(principal.getName()));
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Void> add(Principal principal, @PathVariable Long productId) {
        favoriteProductsService.add(principal.getName(), productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> remove(Principal principal, @PathVariable Long productId) {
        favoriteProductsService.remove(principal.getName(), productId);
        return ResponseEntity.ok().build();
    }
}
