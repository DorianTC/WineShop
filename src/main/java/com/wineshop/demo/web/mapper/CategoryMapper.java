package com.wineshop.demo.web.mapper;
import com.wineshop.demo.domain.entity.Category;
import com.wineshop.demo.web.dto.CategoryDTO;
import com.wineshop.demo.web.dto.response.CategoryResponse;
import org.springframework.stereotype.Component;


@Component
public class CategoryMapper {
    public Category toEntity(CategoryDTO dto){
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;

    }

    public CategoryDTO toDTO(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
    public CategoryResponse toResponse(Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setDescription(category.getDescription());
        return categoryResponse;
    }

}
