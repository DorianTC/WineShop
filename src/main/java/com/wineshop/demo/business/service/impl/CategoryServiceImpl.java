package com.wineshop.demo.business.service.impl;

import com.wineshop.demo.business.exception.messages.ExceptionMessages;
import com.wineshop.demo.business.service.CategoryService;
import com.wineshop.demo.domain.entity.Category;
import com.wineshop.demo.domain.repository.CategoryRepository;
import com.wineshop.demo.web.dto.CategoryDTO;
import com.wineshop.demo.web.mapper.CategoryMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private  final CategoryMapper categoryMapper;

   @Override
    public String save(CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findByName(categoryDTO.getName());
        if(existingCategory!=null){
            return ExceptionMessages.CATEGORY_ALREADY_EXIST;
        }
        Category category=categoryMapper.toEntity(categoryDTO);
        Category savedCategory=categoryRepository.save(category);
        return "Category added successfully with ID: " + savedCategory.getId();
    }
    public String update(CategoryDTO categoryDTO) {
         Category existingCategory = categoryRepository.findByName(categoryDTO.getName());
         if(existingCategory == null){
             return ExceptionMessages.CATEGORY_NOT_FOUND;
         }
         existingCategory.setName(categoryDTO.getName());
         existingCategory.setDescription(categoryDTO.getDescription());
         if(categoryDTO.getId()!=null){
             if(existingCategory.getName().equals(categoryDTO.getName())){
                 return ExceptionMessages.CATEGORY_ALREADY_EXIST;
             }
            existingCategory.setId(categoryDTO.getId());
         }
         return "Category updated successfully with ID: " + existingCategory.getId();
    }
    public String delete(Long id) {
       Category category = categoryRepository.getCategory(id);
       if(category == null){
           return ExceptionMessages.CATEGORY_NOT_FOUND;
       }
       if(category.getId()!= null){
           categoryRepository.delete(category);
       }
       return "Category deleted successfully with ID: " + category.getId();
    }
    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepository.getAll()
                .stream()
                .map(category -> categoryMapper.toDTO(category))
                .collect(Collectors.toList());
    }

}
