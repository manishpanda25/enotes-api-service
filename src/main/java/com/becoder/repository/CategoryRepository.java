package com.becoder.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becoder.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	List<Category> findByActiveTrueAndDeletedFalse();

	Optional<Category> findByIdAndDeletedFalse(Integer id);

	List<Category> findByDeletedFalse();

}
