package com.becoder.service;

import java.util.List;

import com.becoder.dto.CategoryDto;
import com.becoder.dto.CategoryResponse;
import com.becoder.entity.Category;

public interface CategoryService {
	
	public Boolean saveCategory(CategoryDto categorydto);
	
	public List<CategoryDto> getAllCategory();

	public List<CategoryResponse> getActiveCategory();

	public CategoryDto getCategoryById(Integer id);

	public Boolean deleteCategory(Integer id);
	

}
