package com.laptrinhjavaweb.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.mapper.RowMapper;
import com.laptrinhjavaweb.model.NewModel;

public class NewMapper implements RowMapper<NewModel>{

	@Override
	public NewModel mapRow(ResultSet resultSet) {
		// TODO Auto-generated method stub
		NewModel newModel = new NewModel();
		try {
			newModel.setId(resultSet.getLong("id"));
			newModel.setTitle(resultSet.getString("title"));
			newModel.setThumbnail(resultSet.getString("thumbnall"));
			newModel.setContent(resultSet.getString("content"));
			newModel.setShortDescription(resultSet.getString("shortdescription"));
			newModel.setCategoryId(resultSet.getLong("categoryid"));
			newModel.setCreatedDate(resultSet.getTimestamp("createddate"));
			newModel.setCreatedBy(resultSet.getString("createdby"));
		    if (resultSet.getTimestamp("modifieddate") != null) {
		    	newModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
		    }
		    if (resultSet.getString("modifiedby") != null) {
		    	newModel.setModifiedBy(resultSet.getString("modifiedby"));
		    }
			return newModel;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Lỗi ở phần NewMapper");
			return null;
		}
	}

}
