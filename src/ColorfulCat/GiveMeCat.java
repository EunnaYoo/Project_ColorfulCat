package ColorfulCat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GiveMeCat extends HttpServlet {
	//get 방식 처리 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");
		process(request, response);
	}
	//post 방식 처리 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()");
		process(request, response);
	}
	//공통된 로직으로만 구현되는 사용자 정의 메소드
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder builder = null;
		String color = request.getParameter("color"); // <input type="text" placeholder="id 입력" name="id"><br>
		String title = color.toUpperCase() + " CAT";
		String htmlSetting = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>"
				+ title + "</title><style>input { display: block; margin: 0px auto;}"
						+ "img { display: block; margin: 0px auto; width: 480px;  max-width: 100%; height: auto; border:1px solid gray; padding: 0px}</style></head><body>"; 
		ArrayList<String> imgList = ColorfulCatCrawler.getImgList("고양이", color);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		builder = new StringBuilder();
		builder.append(htmlSetting);
		builder.append("<input type=\"button\" value=\"뒤로 가기\" onclick=\"history.back(-1);\">");
		for(String v : imgList) {
			builder.append("<p><img width=\"240px\" src=\"");
			builder.append(v);
			builder.append("\"></p>");
		}
		builder.append("</body>");
		out.println(builder.toString());
	}

}
