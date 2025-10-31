package io.teamchallenge.dto.order;

import io.teamchallenge.enumerated.DeliveryStatus;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OrderFilterDto {
    private List<String> paymentMethods;
    private Boolean isPaid;
    private List<String> deliveryMethods;
    private List<DeliveryStatus> statuses;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdBefore;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAfter;

    @Min(0)
    private BigDecimal totalMore;

    @Min(0)
    private BigDecimal totalLess;
}