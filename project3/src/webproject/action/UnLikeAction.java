package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;

public class UnLikeAction implements WebActionImp{

	@Override
	public void execute(HttpServletRequest req) {
		WebDAO dao = WebDAO.getInstance();
		HttpSession session = req.getSession();

		
		int post_num = Integer.parseInt(req.getParameter("post_Num"));
		int account_num =  (Integer)session.getAttribute("account_Num"); 
		dao.dellike(post_num, account_num);
		dao.likeminus(post_num);
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
	public int return_like(HttpServletRequest req) {
		WebDAO dao = WebDAO.getInstance();
		int post_num = Integer.parseInt(req.getParameter("post_Num"));
	
		return dao.return_like(post_num);
	}

	
}
