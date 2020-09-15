package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.dao.WebDAO;

public class FlagDeleteAction implements WebActionImp{

	@Override
	public void execute(HttpServletRequest req) {
	WebDAO dao = WebDAO.getInstance();
	dao.followDelMethod(req.getParameter("account_Name"));
	dao.accountDelMethod(Integer.parseInt(req.getParameter("account_Num")));
		
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		WebDAO dao = WebDAO.getInstance();
		dao.copostDelMethod(Integer.parseInt(req.getParameter("post_Num")));
		dao.postDelMethod(Integer.parseInt(req.getParameter("post_Num")));
	}

}
