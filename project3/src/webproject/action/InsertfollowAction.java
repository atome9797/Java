package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;

public class InsertfollowAction implements WebActionImp{

	@Override
	public void execute(HttpServletRequest req) {
		 
		WebDAO dao=new WebDAO();
		
		String name=req.getParameter("name");
		
		
		HttpSession session=req.getSession();
		session.getAttribute("account_Num");
		 		
		dao.insertFollow(name, (Integer) session.getAttribute("account_Num"));
		
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
	}

	
}
