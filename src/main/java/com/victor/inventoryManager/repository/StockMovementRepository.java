package com.victor.inventoryManager.repository;

import com.victor.inventoryManager.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}
