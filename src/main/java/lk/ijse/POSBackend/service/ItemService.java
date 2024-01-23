package lk.ijse.POSBackend.service;

import java.util.List;

import lk.ijse.POSBackend.dto.ItemDto;
import lk.ijse.POSBackend.entity.ItemEntity;

public interface ItemService {
    public ItemEntity createItem(ItemDto itemDto);

    public ItemEntity updateItem(ItemDto itemDto, String id);

    public ItemDto findItemById(String id);

    public List<ItemDto> findAllItems();

    public String deleteItem(String id);

    public String generateId();
}
