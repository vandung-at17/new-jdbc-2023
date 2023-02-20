package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.ICategoryDao;
import com.laptrinhjavaweb.mapper.impl.CategoryMapper;
import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryDao extends AbstractDao<CategoryModel> implements ICategoryDao {

	@Override
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM category WHERE id = ?";
        List<CategoryModel> categorys = query(sql, new CategoryMapper(), id);
		return categorys.isEmpty() ? null : categorys.get(0);
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM category WHERE code = ?";
        List<CategoryModel> categorys = query(sql, new CategoryMapper(), code);
		return categorys.isEmpty() ? null : categorys.get(0);
	}

}
