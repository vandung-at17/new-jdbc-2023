package com.laptrinhjavaweb.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.mapper.RowMapper;
import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel>{

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		// TODO Auto-generated method stub
		try {
			CategoryModel categoryModel = new CategoryModel();
			categoryModel.setId(resultSet.getLong("id"));
			categoryModel.setCode(resultSet.getString("code"));
			categoryModel.setName(resultSet.getString("name"));
			return categoryModel;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Lỗi ở phần CategoryMapper");
			return null;
		}
	}

}
