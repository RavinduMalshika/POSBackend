package lk.ijse.POSBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.POSBackend.dto.StockDto;
import lk.ijse.POSBackend.entity.StockEntity;

@Service
public interface StockService {
    public StockEntity createStock(StockDto stockDto);

    public StockEntity updateStock(StockDto stockDto, String id);

    public StockDto findStockById(String id);

    public List<StockDto> findAllStocks();

    public String deleteStock(String id);

    public String generateId();

    public List<StockDto> findByItem(String id);
}
