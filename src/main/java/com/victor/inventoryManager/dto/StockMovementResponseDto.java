package com.victor.inventoryManager.dto;

import com.victor.inventoryManager.entity.enums.MovementType;

import java.time.LocalDateTime;

public record StockMovementResponseDto(
        Long id,
        Long productId,
        Integer quantity,
        MovementType type,
        LocalDateTime createdAt
) {
}
