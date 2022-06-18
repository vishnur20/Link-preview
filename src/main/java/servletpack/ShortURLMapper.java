package servletpack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Storage;

public class ShortURLMapper extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			System.out.println("ERROR: " + e);
		}
		
		String shortURL = request.getRequestURL().toString();
		Map<String, String> map = Storage.getShortURLMap();
		
		if(map.containsKey(shortURL)) {
			String ogURL = map.get(shortURL);
			
			// update the click count & adding client's userAgent for this shortURL
			Map<String, ShortURLData> shortURLDataMap = Storage.getShortURLDataMap();
			ShortURLData shortURLData = shortURLDataMap.get(shortURL);
			// incrementing clicks
			shortURLData.incClicks();
			
			//adding client userAgent
			shortURLData.addUserAgent(request.getHeader("user-agent"));
			
			shortURLDataMap.put(shortURL, shortURLData);
			
			try {
				response.sendRedirect(ogURL);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			response.setContentType("text/html");
			out.print("<h1>Invalid URL</h1>");
		}
		
		// printing shortURLDataMap
		System.out.println("*SHORT-URL-DATA MAP:");
		Storage.printShortURLDataMap();
	}
}
