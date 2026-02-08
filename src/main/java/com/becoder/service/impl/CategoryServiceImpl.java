package com.becoder.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.becoder.dto.CategoryDto;
import com.becoder.dto.CategoryResponse;
import com.becoder.entity.Category;
import com.becoder.repository.CategoryRepository;
import com.becoder.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository cRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public boolean saveCategory(CategoryDto categoryDto) {
		
//		Category category = new Category();
//		category.setName(categoryDto.getName());
//		category.setDescription(categoryDto.getDescription());
//		category.setActive(categoryDto.isActive());
		
		Category category = modelMapper.map(categoryDto, Category.class);
		category.setDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
		
		Category saveCategory = cRepository.save(category);
	    if (ObjectUtils.isEmpty(saveCategory)) {
			return false;
		}
		return true;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		 List<Category> categories = cRepository.findByDeletedFalse();
		    List<CategoryDto> categoryDtoList =
		        categories.stream()
		            .map(category -> modelMapper.map(category, CategoryDto.class))
		            .toList();

		    return categoryDtoList;
	}

	@Override
	public List<CategoryResponse> getActiveCategory() {
		List<Category> categories = cRepository.findByActiveTrueAndDeletedFalse();
		List<CategoryResponse> categoryList = categories.stream().map(cat -> modelMapper.map(cat, CategoryResponse.class)).toList();
		return categoryList;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		Optional<Category> findbyCategory = cRepository.findByIdAndDeletedFalse(id);
		if(findbyCategory.isPresent()) {
			Category category = findbyCategory.get();
			return modelMapper.map(category, CategoryDto.class);
		}
		return null;
	}

	@Override
	public Boolean deleteCategory(Integer id) {
		Optional<Category> findbyCategory = cRepository.findById(id);
		if(findbyCategory.isPresent()) {
			Category category = findbyCategory.get();
			category.setDeleted(true);
			cRepository.save(category);
			return true;
		}
		return false;
	}

	
}
