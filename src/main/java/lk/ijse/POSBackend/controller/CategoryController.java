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
import lk.ijse.POSBackend.dto.CategoryDto;
import lk.ijse.POSBackend.entity.CategoryEntity;
import lk.ijse.POSBackend.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Category", description = "Category Management APIs")
@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Operation(
        summary = "Retrieve all categories",
        description = "Get a list of category DTOs",
        tags = {"GET"}
    )
    @GetMapping("/auth/category")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAllCategories());
    }

    @Operation(
        summary = "Retrieve a category by ID",
        description = "Get a category DTO by specifying its ID",
        tags = {"GET"}
    )
    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findCategoryById(id));
    }

    @Operation(
        summary = "Generate an ID for a category",
        description = "Get the next available ID for a new category",
        tags = {"GET"}
    )
    @GetMapping("/category/generateId")
    public ResponseEntity<String> generateCategoryId() {
        return ResponseEntity.ok().body(categoryService.generateId());
    }
    
    @Operation(
        summary = "Create a category",
        description = "Save a new category to the database",
        tags = {"POST"}
    )
    @PostMapping("/category")
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDto));
    }
    
    @Operation(
        summary = "Update a category",
        description = "Update a category by specifying its ID",
        tags = {"PUT"}
    )
    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable String id, @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok().body(categoryService.updateCategory(categoryDto, id));
    }

    @Operation(
        summary = "Delete a category",
        description = "Delete a category by specifying its ID",
        tags = {"DELETE"}
    )
    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) {
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }
}
