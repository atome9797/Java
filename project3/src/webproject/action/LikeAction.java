package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;

public class LikeAction implements WebActionImp{
	
	@Override
	public void execute(HttpServletRequest req) {
		WebDAO dao = WebDAO.getInstance();
		HttpSession session = req.getSession();
		
		
		int post_num = Integer.parseInt(req.getParameter("post_Num"));
		int account_num =  (Integer)session.getAttribute("account_Num"); 
		dao.likeplus(post_num);
		dao.insertlike(post_num, account_num);
		
		
		//like db에 추가하는 메소드
		
		//post db에 like 갯수를 올리는 메소드
		
		
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
	}
	
	public int return_like(HttpServletRequest req) {
		WebDAO dao = WebDAO.getInstance();
		int post_num = Integer.parseInt(req.getParameter("post_Num"));
	
		return dao.return_like(post_num);
	}

}
