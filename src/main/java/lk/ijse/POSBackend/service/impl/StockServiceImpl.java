package lk.ijse.POSBackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lk.ijse.POSBackend.dto.StockDto;
import lk.ijse.POSBackend.entity.StockEntity;
import lk.ijse.POSBackend.repository.ItemRepository;
import lk.ijse.POSBackend.repository.StockRepository;
import lk.ijse.POSBackend.service.StockService;

public class StockServiceImpl implements StockService {
    @Autowired
    StockRepository stockRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public StockEntity createStock(StockDto stockDto) {
        if(stockRepository.findById(stockDto.getId()).orElse(null) == null) {
            StockEntity stockEntity = new StockEntity();
            stockEntity.setId(stockDto.getId());
            stockEntity.setItemEntity(itemRepository.findById(stockDto.getItemId()).orElse(null));
            stockEntity.setBatch(stockDto.getBatch());
            stockEntity.setPrice(stockDto.getPrice());
            stockEntity.setQuantity(stockDto.getQuantity());
            return stockRepository.save(stockEntity);
        } else {
            return null;
        }
    }

    @Override
    public StockEntity updateStock(StockDto stockDto, String id) {
        StockEntity stockEntity = stockRepository.findById(stockDto.getId()).orElse(null);

        if(stockEntity != null) {
            stockEntity.setId(stockDto.getId());
            stockEntity.setItemEntity(itemRepository.findById(stockDto.getItemId()).orElse(null));
            stockEntity.setBatch(stockDto.getBatch());
            stockEntity.setPrice(stockDto.getPrice());
            stockEntity.setQuantity(stockDto.getQuantity());
            return stockRepository.save(stockEntity);
        } else {
            return null;
        }
    }

    @Override
    public StockDto findStockById(String id) {
        StockEntity stockEntity = stockRepository.findById(id).orElse(null);

        if(stockEntity != null) {
            StockDto stockDto = new StockDto();
            stockDto.setId(stockEntity.getId());
            stockDto.setItemId(stockEntity.getItemEntity().getId());
            stockDto.setBatch(stockEntity.getBatch());
            stockDto.setPrice(stockEntity.getPrice());
            stockDto.setQuantity(stockEntity.getQuantity());
            return stockDto;
        } else {
            return null;
        }
    }

    @Override
    public List<StockDto> findAllStocks() {
        List<StockDto> stockDtos = new ArrayList<>();
        List<StockEntity> stockEntities = stockRepository.findAll();
        for (StockEntity stockEntity : stockEntities) {
            StockDto stockDto = new StockDto();
            stockDto.setId(stockEntity.getId());
            stockDto.setItemId(stockEntity.getItemEntity().getId());
            stockDto.setBatch(stockEntity.getBatch());
            stockDto.setPrice(stockEntity.getPrice());
            stockDto.setQuantity(stockEntity.getQuantity());

            stockDtos.add(stockDto);
        }
        return stockDtos;
    }

    @Override
    public String deleteStock(String id) {
        StockEntity stockEntity = stockRepository.findById(id).orElse(null);

        if(stockEntity != null) {
            stockRepository.delete(stockEntity);
            return "Deleteed Successfully";
        } else {
            return "Failed to Delete";
        }
    }

    @Override
    public String generateId() {
        List<StockEntity> stockEntities = stockRepository.findAll();

        if (stockEntities.size() > 0) {
            StockEntity stockEntity = stockEntities.get(stockEntities.size() - 1);

            String lastId = stockEntity.getId().substring(3);
            Integer incrementedId = Integer.parseInt(lastId) + 1;
            String id = "STK" + String.format("%04d", incrementedId);
            return id;
        } else {
            return "STK0001";
        }
    }
    
}
