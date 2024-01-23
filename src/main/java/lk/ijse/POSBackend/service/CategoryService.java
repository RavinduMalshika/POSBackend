package lk.ijse.POSBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.POSBackend.dto.CategoryDto;
import lk.ijse.POSBackend.entity.CategoryEntity;

@Service
public interface CategoryService {
    public CategoryEntity createCategory(CategoryDto categoryDto);

    public CategoryEntity updateCategory(CategoryDto categoryDto, String id);

    public CategoryDto findCategoryById(String id);

    public List<CategoryDto> findAllCategories();

    public String deleteCategory(String id);

    public String generateId();
}
