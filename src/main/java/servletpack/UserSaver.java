package servletpack;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import db.Storage;
import pojo.User;

public class UserSaver extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = new Gson().fromJson(request.getReader(), User.class);
		String url = user.getUrl();
		
		Map<String, User> userMap = Storage.getUserMap();
		userMap.put(url, user);
		
		// print userMap
		System.out.println("USER MAP:");
		Storage.printUserMap();
		
		response.setContentType("text/plain");
		response.getWriter().print("User saved.");
	}
}
