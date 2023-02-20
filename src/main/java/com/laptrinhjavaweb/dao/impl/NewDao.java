package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.dao.INewDao;
import com.laptrinhjavaweb.mapper.impl.NewMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;

public class NewDao extends AbstractDao<NewModel> implements INewDao{

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM news WHERE categoryid = ?";
		return query(sql, new NewMapper(), categoryId);
	}

	@Override
	public Long save(NewModel newModel) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO `newservlet12month2022`.`news` (`title`, `thumbnall`, `shortdescription`, `content`, `categoryid`, `createddate`, `createdby`) VALUES (?, ?, ?, ?, ?, ?, ?);";
		return insert(sql , newModel.getTitle(), newModel.getThumbnail(), newModel.getShortDescription(), newModel.getContent(), newModel.getCategoryId(),newModel.getCreatedDate(), newModel.getCreatedBy());
	}

	@Override
	public NewModel findOne(Long id) {
		// TODO Auto-generated method stub
        String sql = "SELECT * FROM news WHERE id = ?";
        List<NewModel> news = query(sql, new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewModel updateNew) {
		// TODO Auto-generated method stub
		String sql = "UPDATE `newservlet12month2022`.`news` SET `title` = ?, `thumbnall` = ?, `shortdescription` = ?, `content` = ?, `categoryid` = ?, `createddate` = ?, `modifieddate` = ?, `createdby` = ?, `modifiedby` = ? WHERE (`id` = ?);" ; 
		update(sql, updateNew.getTitle(), updateNew.getThumbnail(), updateNew.getShortDescription(),updateNew.getContent(), updateNew.getCategoryId(), updateNew.getCreatedDate(), updateNew.getModifiedDate(), updateNew.getCreatedBy(), updateNew.getModifiedBy(), updateNew.getId());
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM `newservlet12month2022`.`news` WHERE (`id` = ?);";
		update(sql, id);
	}

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		// TODO Auto-generated method stub
		//String sql = "SELECT * FROM newservlet12month2022.news LIMIT ?,?;";
		StringBuilder sql= new StringBuilder("SELECT * FROM newservlet12month2022.news ");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+" ");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+pageble.getOffset()+","+pageble.getLimit()+";");
		}
		return query(sql.toString(), new NewMapper());
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) FROM news";
		return count(sql);
	}
}