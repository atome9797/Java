package webproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;
import webproject.dto.CommentDTO;

public class CommentAction implements WebActionImp{

	@Override
	public void execute(HttpServletRequest req) {

		WebDAO dao = WebDAO.getInstance();
		
		HttpSession session = req.getSession();
		
		CommentDTO dto = new CommentDTO();
		dto.setComment_Name((String)session.getAttribute("account_Name"));
		dto.setComment_Content(req.getParameter("text"));
		dto.setPost_Num(Integer.parseInt(req.getParameter("post_Num")));
		
		dao.insertComment(dto);
	}
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
	}
}
