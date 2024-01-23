package lk.ijse.POSBackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lk.ijse.POSBackend.dto.ItemDto;
import lk.ijse.POSBackend.entity.ItemEntity;
import lk.ijse.POSBackend.repository.CategoryRepository;
import lk.ijse.POSBackend.repository.ItemRepository;
import lk.ijse.POSBackend.service.ItemService;

public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ItemEntity createItem(ItemDto itemDto) {
        if(itemRepository.findById(itemDto.getId()).orElse(null) == null) {
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setId(itemDto.getId());
            itemEntity.setName(itemDto.getName());
            itemEntity.setCategoryEntity(categoryRepository.findById(itemDto.getCategory()).orElse(null));

            return itemRepository.save(itemEntity);
        } else {
            return null;
        }
    }

    @Override
    public ItemEntity updateItem(ItemDto itemDto, String id) {
        ItemEntity itemEntity = itemRepository.findById(id).orElse(null);

        if(itemEntity != null) {
            itemEntity.setId(itemDto.getId());
            itemEntity.setName(itemDto.getName());
            itemEntity.setCategoryEntity(categoryRepository.findById(itemDto.getCategory()).orElse(null));

            return itemRepository.save(itemEntity);
        } else {
            return null;
        }
    }

    @Override
    public ItemDto findItemById(String id) {
        ItemEntity itemEntity = itemRepository.findById(id).orElse(null);
        if(itemEntity != null) {
            ItemDto itemDto = new ItemDto();
            itemDto.setId(itemEntity.getId());
            itemDto.setName(itemEntity.getName());
            itemDto.setCategory(itemEntity.getCategoryEntity().getId());

            return itemDto;
        } else {
            return null;
        }
    }

    @Override
    public List<ItemDto> findAllItems() {
        List<ItemDto> itemDtos = new ArrayList<>();
        List<ItemEntity> itemEntities = itemRepository.findAll();
        for (ItemEntity itemEntity : itemEntities) {
            ItemDto itemDto = new ItemDto();
            itemDto.setId(itemEntity.getId());
            itemDto.setName(itemEntity.getName());
            itemDto.setCategory(itemEntity.getCategoryEntity().getId());
            itemDtos.add(itemDto);
        } 

        return itemDtos;
    }

    @Override
    public String deleteItem(String id) {
        ItemEntity itemEntity = itemRepository.findById(id).orElse(null);

        if(itemEntity != null) {
            itemRepository.delete(itemEntity);
            return "Deleted Succcessfully";
        } else {
            return "Failed to Delete";
        }
    }

    @Override
    public String generateId() {
        List<ItemEntity> itemEntities = itemRepository.findAll();

        if (itemEntities.size() > 0) {
            ItemEntity itemEntity = itemEntities.get(itemEntities.size() - 1);

            String lastId = itemEntity.getId().substring(3);
            Integer incrementedId = Integer.parseInt(lastId) + 1;
            String id = "ITM" + String.format("%04d", incrementedId);
            return id;
        } else {
            return "ITM0001";
        }
    }
    
}
