package lk.ijse.POSBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.POSBackend.dto.CategoryDto;
import lk.ijse.POSBackend.entity.CategoryEntity;
import lk.ijse.POSBackend.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/auth/category")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAllCategories());
    }

    @GetMapping("/category/generateId")
    public ResponseEntity<String> generateCategoryId() {
        return ResponseEntity.ok().body(categoryService.generateId());
    }
    
    
    @PostMapping("/category")
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDto));
    }
    
    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable String id, @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok().body(categoryService.updateCategory(categoryDto, id));
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) {
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }
}
