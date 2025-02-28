package com.victor.inventoryManager.dto;

public record PaginationResponse(Integer page,
                                 Integer pageSize,
                                 Long elements,
                                 Integer totalPages) {
}
