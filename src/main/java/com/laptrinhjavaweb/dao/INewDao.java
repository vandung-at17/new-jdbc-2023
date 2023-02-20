package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewDao extends GenericDao<NewModel>{
	NewModel findOne(Long id);
	List<NewModel> findByCategoryId (Long categoryId);
	Long save (NewModel newModel);
	void update (NewModel updateNew);
	void delete (Long id);
	// Phương thức lấy ra tất cả
	List<NewModel> findAll (Pageble pageble);
	int getTotalItem ();
}
