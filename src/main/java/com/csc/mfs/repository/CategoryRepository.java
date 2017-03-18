package com.csc.mfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csc.mfs.model.CategoriesType;

public interface CategoryRepository extends JpaRepository<CategoriesType, Integer>{
	CategoriesType findByFileType(String type);
}
