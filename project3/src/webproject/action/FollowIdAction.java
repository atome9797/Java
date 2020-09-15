package webproject.action;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;
import webproject.dto.AccountDTO;


public class FollowIdAction implements WebActionImp{
	
	@Override
	public void execute(HttpServletRequest req) {
		
		WebDAO dao = WebDAO.getInstance();
		HttpSession session = req.getSession();
		
		int num = (Integer)session.getAttribute("account_Num");
		List<AccountDTO> aList = dao.follow(num);
		
		req.setAttribute("aList10", aList);
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
	}
}
