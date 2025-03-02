package com.victor.inventoryManager.service;

import com.victor.inventoryManager.dto.StockMovementResponseDto;
import com.victor.inventoryManager.dto.StockMovementDto;
import com.victor.inventoryManager.entity.Product;
import com.victor.inventoryManager.entity.StockMovement;
import com.victor.inventoryManager.entity.enums.MovementType;
import com.victor.inventoryManager.exception.ProductNotFoundException;
import com.victor.inventoryManager.repository.ProductRepository;
import com.victor.inventoryManager.repository.StockMovementRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    public StockMovementService(StockMovementRepository stockMovementRepository, ProductRepository productRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public StockMovementResponseDto processStockMovement(StockMovementDto stockMovementDto) {

        if (stockMovementDto.type() == null) {
            throw new IllegalArgumentException("Tipo de movimentação não fornecido");
        }


        Product product = productRepository.findById(stockMovementDto.productId())
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        StockMovement stockMovement = new StockMovement();
        stockMovement.setProduct(product);
        stockMovement.setQuantity(stockMovementDto.quantity());
        stockMovement.setType(stockMovementDto.type());

        if (stockMovement.getType() == MovementType.IN) {
            product.adjustStockQuantity(stockMovement.getQuantity());
        } else if (stockMovement.getType() == MovementType.OUT) {
            product.adjustStockQuantity(-stockMovement.getQuantity());
        }

        stockMovementRepository.save(stockMovement);

        productRepository.save(product);

        return convertToDto(stockMovement);
    }

    public StockMovementResponseDto convertToDto(StockMovement stockMovement) {
        return new StockMovementResponseDto(
                stockMovement.getId(),
                stockMovement.getProduct().getId(),
                stockMovement.getQuantity(),
                stockMovement.getType(),
                stockMovement.getCreatedAt()
        );
    }
}
