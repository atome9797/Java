package webproject.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webproject.dao.WebDAO;
import webproject.dto.AccountPostDTO;
import webproject.dto.PostDTO;

public class LikeListAction implements WebActionImp {
	@Override
	public void execute(HttpServletRequest req) {
		WebDAO dao = WebDAO.getInstance();
		HttpSession session = req.getSession();

		int account_num = (Integer) session.getAttribute("account_Num");
		List<PostDTO> aList3 = dao.likelist(account_num);
		String name = (String) session.getAttribute("account_Name");
		List<PostDTO> aList = dao.followerid(account_num, name);
		List<AccountPostDTO> aList2 = dao.post(aList);
		int[] chk = new int[aList2.size()];
		for (int i = 0; i < aList2.size(); i++) {
			for (int j = 0; j < aList3.size(); j++) {
				if (aList2.get(i).getPost_Num() == aList3.get(j).getPost_Num()) {
					chk[i] = 1;
					break;
				} else {
					chk[i] = 0;
				}
			}
		}

		req.setAttribute("aList3", chk);

	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		WebDAO dao = WebDAO.getInstance();
		HttpSession session = req.getSession();

		int account_num = (Integer) session.getAttribute("account_Num");
		int post_num = Integer.parseInt(req.getParameter("post_Num")); 
		int num =dao.commentlikechk(post_num, account_num);
		req.setAttribute("num", num);
		req.setAttribute("post_Num", req.getParameter("post_Num"));
	}
}
