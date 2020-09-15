package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;

public class DeleteAction implements WebActionImp{

	@Override
	public void execute(HttpServletRequest req) {


		WebDAO dao = WebDAO.getInstance();
		
		String name=req.getParameter("name");
		HttpSession session = req.getSession();
		
		dao.deleteMethod(name,(Integer)session.getAttribute("account_Num"));
		
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

}
