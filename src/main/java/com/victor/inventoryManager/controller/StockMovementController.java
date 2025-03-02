package com.victor.inventoryManager.controller;

import com.victor.inventoryManager.dto.StockMovementResponseDto;
import com.victor.inventoryManager.dto.StockMovementDto;
import com.victor.inventoryManager.service.StockMovementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock-movement")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @PostMapping
    public ResponseEntity<StockMovementResponseDto> createStockMovement(
            @RequestBody StockMovementDto stockMovementDto) {

        StockMovementResponseDto responseDto = stockMovementService.processStockMovement(stockMovementDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
