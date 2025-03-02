package com.victor.inventoryManager.dto;

import com.victor.inventoryManager.entity.enums.MovementType;

public record StockMovementDto(Long productId,
                               Integer quantity,
                               MovementType type) {
}
