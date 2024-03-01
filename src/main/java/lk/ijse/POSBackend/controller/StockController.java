package lk.ijse.POSBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.POSBackend.dto.StockDto;
import lk.ijse.POSBackend.entity.StockEntity;
import lk.ijse.POSBackend.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
public class StockController {
    @Autowired
    StockService stockService;

    @GetMapping("/stock/generateId")
    public ResponseEntity<String> generateStockId() {
        return ResponseEntity.ok().body(stockService.generateId());
    }
    

    @GetMapping("/auth/stock")
    public ResponseEntity<List<StockDto>> getAllStocks() {
        return ResponseEntity.ok().body(stockService.findAllStocks());
    }

    @GetMapping("auth/stock/item/{id}")
    public ResponseEntity<List<StockDto>> getStocksByItem(@PathVariable String id) {
        return ResponseEntity.ok().body(stockService.findByItem(id));
    }
    
    @PostMapping("/stock")
    public ResponseEntity<StockEntity> createStock(@RequestBody StockDto stockDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.createStock(stockDto));
    }
    
    @PutMapping("/stock/{id}")
    public ResponseEntity<StockEntity> updateStock(@PathVariable String id, @RequestBody StockDto stockDto) {
        return ResponseEntity.ok().body(stockService.updateStock(stockDto, id));
    }

    @DeleteMapping("/stock/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable String id) {
        return ResponseEntity.ok().body(stockService.deleteStock(id));
    }
}
