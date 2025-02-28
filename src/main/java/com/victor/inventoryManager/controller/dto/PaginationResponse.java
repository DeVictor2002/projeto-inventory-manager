package com.victor.inventoryManager.controller.dto;

public record PaginationResponse(Integer page,
                                 Integer pageSize,
                                 Long elements,
                                 Integer totalPages) {
}
