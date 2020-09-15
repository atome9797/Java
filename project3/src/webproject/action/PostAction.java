package webproject.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;
import webproject.dto.AccountPostDTO;
import webproject.dto.PostDTO;

public class PostAction implements WebActionImp{

	//여기서 follow_name을 받아와야되
	@Override
	public void execute(HttpServletRequest req) {
		WebDAO dao = WebDAO.getInstance();
		HttpSession session = req.getSession();
		
		int num = (Integer)session.getAttribute("account_Num");
		String name = (String)session.getAttribute("account_Name");
		List<PostDTO> aList = dao.followerid(num, name);
		List<AccountPostDTO> aList2 = dao.post(aList);
		
		
		req.setAttribute("aList2", aList2);
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
	}
}
