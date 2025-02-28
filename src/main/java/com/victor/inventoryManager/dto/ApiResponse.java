package com.victor.inventoryManager.dto;

import java.util.List;

public record ApiResponse<T>(List<T> data, PaginationResponse pagination) {
}
