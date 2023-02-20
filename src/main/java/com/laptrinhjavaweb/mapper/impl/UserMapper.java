package com.laptrinhjavaweb.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.mapper.RowMapper;
import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		// TODO Auto-generated method stub
		try {
			UserModel user = new UserModel();
			user.setId(resultSet.getLong("id"));
			user.setUserName(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setFullName(resultSet.getString("fullname"));
			user.setStatus(resultSet.getInt("status"));			
			user.setRoleId(resultSet.getLong("roleid"));
			user.setCreatedDate(resultSet.getTimestamp("createddate"));
			user.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			user.setCreatedBy(resultSet.getString("createdby"));
			user.setModifiedBy(resultSet.getString("modifiedby"));
			try {
				RoleModel role = new RoleModel();
				role.setCode(resultSet.getString("code"));
				role.setName(resultSet.getString("name"));
				user.setRoleModel(role);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Lỗi ở phần UserMapper");
				System.out.println(e.getMessage());
			}		
			return user;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Lỗi ở phần UserMapper");
			return null;
		}
	}

}
