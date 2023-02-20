package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.paging.impl.PageRequest;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;
@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3461613811928964367L;
	
	@Inject
	private INewService newService;
	
	@Inject
	private ICategoryService categoryService;
	
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	NewModel model = FormUtil.toModel(NewModel.class, req);
//		Bước sau là chuyển Parameter trong url sang String rồi mới ép kiểu thay vào đó chúng ta dùng FormUtil hàm toModel
//    	String pageStr = req.getParameter("page");
//    	String maxPageItemStr = req.getParameter("maxPageItem");
//    	if (pageStr != null) {
//    		model.setPage(Integer.parseInt(pageStr));
//    	}else {
//    		model.setPage(1);
//    	}
//    	if (maxPageItemStr != null) {
//    		model.setMaxPageItem(Integer.parseInt(maxPageItemStr));
//    	}
    	String view = "";
    	if (model.getType().equals(SystemConstant.LIST)) {
    		Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(),model.getSortBy()));
        	model.setListResult(newService.findAll(pageble));
        	model.setTotalItem(newService.getTotalItem());
        	model.setTotalPages((int) Math.ceil((double) model.getTotalItem() /model.getMaxPageItem()));
        	view = "/views/admin/new/list.jsp";
    	} else if (model.getType().equals(SystemConstant.EDIT)) {
    		if (model.getId() != null) {
    			model = newService.findOne(model.getId());
    		} else {
    			
    		}
    		req.setAttribute("categories", categoryService.findAll());
    		view = "/views/admin/new/edit.jsp";
    	}
    	MessageUtil.showMessage(req);
    	req.setAttribute(SystemConstant.MODEL, model);
    	RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    }
}
