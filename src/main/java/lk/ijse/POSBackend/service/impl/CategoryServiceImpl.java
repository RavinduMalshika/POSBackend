package lk.ijse.POSBackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.ijse.POSBackend.dto.CategoryDto;
import lk.ijse.POSBackend.entity.CategoryEntity;
import lk.ijse.POSBackend.repository.CategoryRepository;
import lk.ijse.POSBackend.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryEntity createCategory(CategoryDto categoryDto) {
        if (categoryRepository.findById(categoryDto.getId()).orElse(null) == null) {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(categoryDto.getId());
            categoryEntity.setName(categoryDto.getName());
            
            return categoryRepository.save(categoryEntity);
        } else {
            return null;
        }
    }

    @Override
    public CategoryEntity updateCategory(CategoryDto categoryDto, String id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);

        if (categoryEntity != null) {
            categoryEntity.setName(categoryDto.getName());

            return categoryRepository.save(categoryEntity);
        } else {
            return null;
        }
    }

    @Override
    public CategoryDto findCategoryById(String id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);

        if(categoryEntity != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(categoryEntity.getId());
            categoryDto.setName(categoryEntity.getName());

            return categoryDto;
        } else {
            return null;
        }
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntities) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(categoryEntity.getId());
            categoryDto.setName(categoryEntity.getName());
            categoryDtos.add(categoryDto);
        }

        return categoryDtos;
    }

    @Override
    public String deleteCategory(String id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);

        if(categoryEntity != null) {
            categoryRepository.delete(categoryEntity);
            return "Deleted Successfully";
        } else {
            return "Failed to Delete";
        }
    }

    @Override
    public String generateId() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();

        if(categoryEntities.size() > 0) {
            CategoryEntity categoryEntity = categoryEntities.get(categoryEntities.size() - 1);

            String lastId = categoryEntity.getId().substring(3);
            Integer incrementedId = Integer.parseInt(lastId) + 1;
            String id = "CAT" + String.format("%04d", incrementedId);
            return id; 
        } else {
            return "CAT0001";
        }
    }
    
}
