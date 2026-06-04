package com.wineshop.demo.business.service.impl;

import com.wineshop.demo.business.exception.CategoryAlreadyExistsException;
import com.wineshop.demo.business.exception.CategoryNotFoundException;
import com.wineshop.demo.business.service.CategoryService;
import com.wineshop.demo.domain.entity.Category;
import com.wineshop.demo.domain.repository.CategoryRepository;
import com.wineshop.demo.web.dto.CategoryDTO;
import com.wineshop.demo.web.dto.MessageResponse;
import com.wineshop.demo.web.dto.response.CategoryResponse;
import com.wineshop.demo.web.mapper.CategoryMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.wineshop.demo.business.exception.messages.ExceptionMessages.CATEGORY_DELETED_SUCCESFULLY;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private  final CategoryMapper categoryMapper;

   @Override
    public CategoryResponse save(CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findByName( categoryDTO.getName());
        if(existingCategory!=null){
            throw new CategoryAlreadyExistsException();
        }
        Category category=categoryMapper.toEntity(categoryDTO);
        Category savedCategory=categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }
    public CategoryResponse update(Long id,CategoryDTO categoryDTO) {
         Category existingCategory = categoryRepository.findById(id);
         if(existingCategory == null){
             throw new CategoryNotFoundException();
         }
         Category categoryWithSameName = categoryRepository.findByName(categoryDTO.getName());

        if (categoryWithSameName != null && !categoryWithSameName.getId().equals(id)) {
            throw new CategoryAlreadyExistsException();
        }
        existingCategory.setName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());
        Category updatedCategory = categoryRepository.save(existingCategory);

        return categoryMapper.toResponse(updatedCategory);
    }
    public MessageResponse delete(Long id) {
       Category category = categoryRepository.getCategory(id);
       if(category == null){
           throw  new CategoryNotFoundException();
       }
           categoryRepository.delete(category);

       MessageResponse messageResponse = new MessageResponse();
       messageResponse.setMessage(CATEGORY_DELETED_SUCCESFULLY);
       return messageResponse;
    }
    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepository.getAll()
                .stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toList());
    }

}
