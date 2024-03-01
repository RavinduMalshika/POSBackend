package lk.ijse.POSBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.POSBackend.dto.ItemDto;
import lk.ijse.POSBackend.entity.ItemEntity;
import lk.ijse.POSBackend.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping("/item/{id}")
    public ResponseEntity<ItemDto> getItemFromId(@PathVariable String id) {
        return ResponseEntity.ok().body(itemService.findItemById(id));
    }
    

    @GetMapping("/auth/item")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        return ResponseEntity.ok().body(itemService.findAllItems());
    }
    
    @GetMapping("/item/generateId")
    public ResponseEntity<String> generateItemId() {
        return ResponseEntity.ok().body(itemService.generateId());
    }

    @GetMapping("auth/item/category/{category}")
    public ResponseEntity<List<ItemDto>> filterByCategory(@PathVariable String category) {
        return ResponseEntity.ok().body(itemService.filterByCategory(category));
    }
    
    
    @PostMapping("/item")
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItem(itemDto));
    }
    
    @PutMapping("/item/{id}")
    public ResponseEntity<ItemEntity> updateItem(@PathVariable String id, @RequestBody ItemDto itemDto) {
        return ResponseEntity.ok().body(itemService.updateItem(itemDto, id));
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable String id) {
        return ResponseEntity.ok().body(itemService.deleteItem(id));
    }
}
