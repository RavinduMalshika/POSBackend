package lk.ijse.POSBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lk.ijse.POSBackend.dto.StockDto;
import lk.ijse.POSBackend.entity.StockEntity;
import lk.ijse.POSBackend.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Stock", description = "Stock Management APIs")
@RestController
@CrossOrigin(origins = "*")
public class StockController {
    @Autowired
    StockService stockService;

    @Operation(
        summary = "Generate an ID for a stock",
        description = "Get the next available ID for a new stock",
        tags = {"GET"}
    )
    @GetMapping("/stock/generateId")
    public ResponseEntity<String> generateStockId() {
        return ResponseEntity.ok().body(stockService.generateId());
    }
    
    @Operation(
        summary = "Retrieve all stocks",
        description = "Get a list of stock DTOs",
        tags = {"GET"}
    )
    @GetMapping("/auth/stock")
    public ResponseEntity<List<StockDto>> getAllStocks() {
        return ResponseEntity.ok().body(stockService.findAllStocks());
    }

    @Operation(
        summary = "Retrieve all stocks by item",
        description = "Get a list of stock DTOs of a specific item by specifying the item",
        tags = {"GET"}
    )
    @GetMapping("auth/stock/item/{id}")
    public ResponseEntity<List<StockDto>> getStocksByItem(@PathVariable String id) {
        return ResponseEntity.ok().body(stockService.findByItem(id));
    }
    
    @Operation(
        summary = "Create a stock",
        description = "Save a new stock to the database",
        tags = {"POST"}
    )
    @PostMapping("/stock")
    public ResponseEntity<StockEntity> createStock(@RequestBody StockDto stockDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.createStock(stockDto));
    }
    
    @Operation(
        summary = "Update a stock",
        description = "Update a stock by specifying its ID",
        tags = {"PUT"}
    )
    @PutMapping("/stock/{id}")
    public ResponseEntity<StockEntity> updateStock(@PathVariable String id, @RequestBody StockDto stockDto) {
        return ResponseEntity.ok().body(stockService.updateStock(stockDto, id));
    }

    @Operation(
        summary = "Delete a stock",
        description = "Delete a stock by specifying its ID",
        tags = {"DELETE"}
    )
    @DeleteMapping("/stock/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable String id) {
        return ResponseEntity.ok().body(stockService.deleteStock(id));
    }
}
