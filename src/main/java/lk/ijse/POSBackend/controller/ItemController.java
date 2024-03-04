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
import lk.ijse.POSBackend.dto.ItemDto;
import lk.ijse.POSBackend.entity.ItemEntity;
import lk.ijse.POSBackend.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "Item", description = "Item Management APIs")
@RestController
@CrossOrigin(origins = "*")
public class ItemController {
    @Autowired
    ItemService itemService;

    @Operation(
        summary = "Retrieve an item by ID",
        description = "Get an item DTO by specifying its ID",
        tags = {"GET"}
    )
    @GetMapping("/item/{id}")
    public ResponseEntity<ItemDto> getItemFromId(@PathVariable String id) {
        return ResponseEntity.ok().body(itemService.findItemById(id));
    }
    
    @Operation(
        summary = "Retrieve all items",
        description = "Get a list of item DTOs",
        tags = {"GET"}
    )
    @GetMapping("/auth/item")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        return ResponseEntity.ok().body(itemService.findAllItems());
    }
    
    @Operation(
        summary = "Generate an ID for an item",
        description = "Get the next available ID for a new item",
        tags = {"GET"}
    )
    @GetMapping("/item/generateId")
    public ResponseEntity<String> generateItemId() {
        return ResponseEntity.ok().body(itemService.generateId());
    }

    @Operation(
        summary = "Retrieve all items by category",
        description = "Get a list of item DTOs in a specific category by specifying the category",
        tags = {"GET"}
    )
    @GetMapping("auth/item/category/{category}")
    public ResponseEntity<List<ItemDto>> filterByCategory(@PathVariable String category) {
        return ResponseEntity.ok().body(itemService.filterByCategory(category));
    }
    
    @Operation(
        summary = "Create an item",
        description = "Save a new item to the database",
        tags = {"POST"}
    )
    @PostMapping("/item")
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItem(itemDto));
    }
    
    @Operation(
        summary = "Update an item",
        description = "Update an item by specifying its ID",
        tags = {"PUT"}
    )
    @PutMapping("/item/{id}")
    public ResponseEntity<ItemEntity> updateItem(@PathVariable String id, @RequestBody ItemDto itemDto) {
        return ResponseEntity.ok().body(itemService.updateItem(itemDto, id));
    }

    @Operation(
        summary = "Delete an item",
        description = "Delete an item by specifying its ID",
        tags = {"DELETE"}
    )
    @DeleteMapping("/item/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable String id) {
        return ResponseEntity.ok().body(itemService.deleteItem(id));
    }
}
