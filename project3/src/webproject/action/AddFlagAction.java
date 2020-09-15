package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webproject.dao.WebDAO;

public class AddFlagAction implements WebActionImp{

	@Override
	public void execute(HttpServletRequest req) {
		WebDAO dao = WebDAO.getInstance();
		
		int post_num = Integer.parseInt(req.getParameter("post_Num"));
		int account_num = Integer.parseInt(req.getParameter("account_Num"));
		String kind = (String)req.getParameter("selected_Flag");
		dao.addflag(post_num, kind);
	    dao.addflagtable(post_num, account_num);
		
		dao.addflag(post_num);
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
			WebDAO dao = WebDAO.getInstance();
		
		int follow_num = Integer.parseInt(req.getParameter("follow_Num"));
		System.out.println(req.getParameter("follow_Num"));
		int account_num = Integer.parseInt(req.getParameter("account_Num"));
		System.out.println(req.getParameter("account_Num"));
		String kind = (String)req.getParameter("selected_Flag");
		dao.addaflag(follow_num, kind);
	    dao.addaflagtable(follow_num, account_num);
		
	}
	
}
