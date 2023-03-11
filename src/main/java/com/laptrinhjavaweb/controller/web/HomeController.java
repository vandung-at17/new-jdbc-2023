package com.laptrinhjavaweb.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/trang-chu", "/dang-nhap", "/thoat" })
public class HomeController extends HttpServlet {

	@Inject
	private ICategoryService categoryService;

	/*
	 * @Inject private INewService newService;
	 */

	@Inject
	private IUserService userService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2686801510274002166L;

	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * String title = "Bài viết 4"; String content = "bai viet 4"; Long categoryId =
		 * 1L; String shortdescription = "baiviet4"; NewModel newModel = new NewModel();
		 * newModel.setTitle(title); newModel.setContent(content);
		 * newModel.setCategoryId(categoryId);
		 * newModel.setShortDescription(shortdescription); newService.save(newModel);
		 */
		String action = req.getParameter("action");
		if (action != null && action.equals("login")) {
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if (message != null && alert != null) {
				req.setAttribute("message", resourceBundle.getString(message));
				req.setAttribute("alert", alert);
			}
			Cookie arr[] = req.getCookies();
			if (arr != null) {
				for (Cookie cookie : arr) {
					if (cookie.getName().equals("username")) {
						req.setAttribute("userName", cookie.getValue());
					}
					if (cookie.getName().equals("password")) {
						req.setAttribute("password", cookie.getValue());
					}
				}
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/login.jsp");
			dispatcher.forward(req, resp);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(req, "USERMODEL");
			resp.sendRedirect(req.getContextPath() + "/trang-chu");
		} else {
			req.setAttribute("categories", categoryService.findAll());
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(req, resp);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getParameter("action");
		String remember = req.getParameter("remember");
		if (action != null && action.equals("login")) {
			UserModel model = FormUtil.toModel(UserModel.class, req);
			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
			if (model != null) {
				// Lưu account đăng nhập lên sesion
				SessionUtil.getInstance().putValue(req, "USERMODEL", model);
				// Lưu user và password lên cookie
				Cookie user = new Cookie("username", model.getUserName());
				Cookie pass = new Cookie("password", model.getPassword());
				user.setMaxAge(70);
				if (remember != null) {
					pass.setMaxAge(60);
				} else {
					pass.setMaxAge(0);
				}
				resp.addCookie(user);
				resp.addCookie(pass);
				if (model.getRoleModel().getCode().equals("USER")) {
					resp.sendRedirect(req.getContextPath() + "/trang-chu");
				} else if (model.getRoleModel().getCode().equals("ADMIN")) {
					resp.sendRedirect(req.getContextPath() + "/admin-home");
				}
			} else {
				resp.sendRedirect(req.getContextPath()
						+ "/dang-nhap?action=login&message=username_password_invalid&alert=danger");
			}
		}
	}
}
