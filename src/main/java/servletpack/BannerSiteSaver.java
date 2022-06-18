package servletpack;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import db.Storage;
import pojo.Banner;

public class BannerSiteSaver extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String bodyData = getBody(request);
		System.out.println("JSON-data : " + bodyData);
		
		Banner banner = new Gson().fromJson(bodyData, Banner.class);
		// storing in the map
		Map<String, Banner> bannerMap = Storage.getBannerMap();
		bannerMap.put(banner.getUrl(), banner);
		
		System.out.println("BANNER MAP:");
		Storage.printBannerMap();
//		
		response.setContentType("text/plain");
	}
	
	public String getBody(HttpServletRequest request) throws IOException {
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader bufferedReader = request.getReader();
		
		int chr;
		while((chr = bufferedReader.read()) != -1) {
			stringBuffer.append((char) chr);
		}
		
		return stringBuffer.toString();
	}
}
