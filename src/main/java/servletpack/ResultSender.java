package servletpack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import db.Storage;

public class ResultSender extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		//
		System.out.println("ResultSender servlet is called.");
		
		List<ShortURLData> results = new LinkedList<>();
		String jsonResults = "";
		
		Map<String, ShortURLData> shortURLDataMap = Storage.getShortURLDataMap();
		for(Map.Entry<String, ShortURLData> entry : shortURLDataMap.entrySet()) {
			results.add(entry.getValue());
		}
		
		jsonResults += "[";
		for(ShortURLData shortURLData : results) {
			jsonResults += new Gson().toJson(shortURLData);
			jsonResults += ",";
		}
		
		if(jsonResults.length() > 1) {
			jsonResults = jsonResults.substring(0, jsonResults.length()-1);
		}
		jsonResults += "]";
		
		// sending response
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(out == null) {
			System.out.println("ERROR: can' instantiate out.");
			return;
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(jsonResults);
		
		// printing shortURLDataMap
		Storage.printShortURLDataMap();
		//
		System.out.println("JsonResults => " + jsonResults);
	}
}
