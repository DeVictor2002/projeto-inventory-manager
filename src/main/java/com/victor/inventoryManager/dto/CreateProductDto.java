package com.victor.inventoryManager.dto;

import java.math.BigDecimal;

public record CreateProductDto(String name,
                               String description,
                               Integer quantity,
                               BigDecimal price) {
}
