package servletpack;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Storage;

public class RemoveShortURLData extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String shortURL = request.getParameter("shortURL");
		Map<String, ShortURLData> shortURLDataMap = Storage.getShortURLDataMap();
		Map<String, String> shortURLMap = Storage.getShortURLMap();
		
		// removing from shortURLDataMap
		shortURLDataMap.remove(shortURL);
		
		// removing from shortURLMap
		shortURLMap.remove(shortURL);
		
		response.setContentType("text/plain");
		response.getWriter().print("deactivated");
	}
	
	@Override
	public void destroy() {}
}
