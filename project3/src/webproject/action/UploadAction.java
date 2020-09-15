package webproject.action;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import webproject.dao.WebDAO;
import webproject.dto.PostDTO;

public class UploadAction implements WebMultiImp{

	@Override
	public MultipartRequest executeMulti(HttpServletRequest req) {
		 
		MultipartRequest multi = null;
		String saveDirectory = "C:/Users/goott5-22/Desktop/webproject2020/WebContent/images";
		File file = new File(saveDirectory);
		if(!file.isDirectory()) {
			file.mkdir();
		}
		
		int maxPostSize = 1*1000*1000*1000; // 1GB
		String encoding = "UTF-8";
		
		try {
			multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		HttpSession session = req.getSession();
		
		WebDAO dao = WebDAO.getInstance();
		
		PostDTO dto = new PostDTO();
		Enumeration params = multi.getParameterNames();
		while (params.hasMoreElements()) {
			String paramname = (String) params.nextElement();
			if(paramname.equalsIgnoreCase("noticetext")) {
				String noticetext = multi.getParameter("noticetext");
				dto.setPost_Content(noticetext);
			}
		}
		dto.setAccount_Name((String)session.getAttribute("account_Name"));
		dto.setPost_Img(multi.getFilesystemName("filepath"));
		dao.uploadPost(dto);
		int postnum = dao.updatepost();
		dao.addflagcategorytable(postnum);
	
		return multi;
		
	} // end executeMulti()

} // end class
