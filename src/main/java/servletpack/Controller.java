package servletpack;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import db.Storage;
import pojo.PreviewData;

public class Controller extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(out == null) {
			throw new NullPointerException("Can not instatiate PrinterWriter");
		}
		
		String urlFromClient = request.getParameter("url");
		
		// take data form the database,
		// if the requested url data is already there in the database (previewDataMap)
		
		PreviewData previewDataInStorage = Storage.getPreviewData(urlFromClient);	// with ogURL
		
		if(previewDataInStorage != null) {
			// sending JSON object to the client
			System.out.println("Fetched from the storage => " + urlFromClient);
			
			String sameOgTitle = previewDataInStorage.getOgTitle();
			String sameOgDescription = previewDataInStorage.getOgDescription();
			String newShortURL = Storage.getShortURL(urlFromClient);
			String sameOgImage = previewDataInStorage.getOgImage();
			
			PreviewData previewDataWithNewShortURL = new PreviewData(sameOgTitle, sameOgDescription, newShortURL, sameOgImage);
			
			Storage.putShortURL(newShortURL, previewDataInStorage.getOgURL());
			previewDataWithNewShortURL.setOgURL(newShortURL);
			
			String previewDataJSON = new Gson().toJson(previewDataWithNewShortURL);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.println(previewDataJSON);
			out.flush();
			
			// printing maps
			System.out.println("SHORT-URL MAP:");
			Storage.printShortURLMap();
			System.out.println();
			
			System.out.println("PREVIEW-DATA MAP:");
			Storage.printPreviewDataMap();
			System.out.println();
			
			System.out.println("SHORT-URL-DATA MAP:");
			Storage.printShortURLDataMap();
			System.out.println();
			
			return;
		}
		
		// get meta info from the site
		Document doc = null;
		String referer = request.getHeader("referer");
		String agent = request.getHeader("user-agent");
		//
		System.out.println();
		System.out.println("Referer => " + referer);
		System.out.println("User agent => " + agent);
		
		try {
			doc = Jsoup.connect(urlFromClient)
					.userAgent(agent)
					.get();
		} catch (ConnectException e) {
			System.out.println("ERROR: While fetching data using 'Jsoup' => " + e);
			response.setContentType("text/plain");
			out.print("connection timed out");
			return;
		} catch (IOException e) {
			System.out.println("ERROR: While fetching data using 'Jsoup' => " + e);
			response.setContentType("text/plain");
			out.print("can\'t connect");
			return;
		}
		
		if(doc == null) {
			response.setContentType("text/plain");
			out.print("can\'t connect");
			return;
		}
		
		Elements metaElements = doc.getElementsByTag("meta");
		String ogTitle = "";
		String ogDescription = "";
		String ogURL = "";
		String ogImage = "";
		
		for(Element metaElement : metaElements) {
			String prop = metaElement.attr("property");
			if("og:title".equals(prop)) {
				ogTitle = metaElement.attr("content");
			}
			else if("og:description".equals(prop)) {
				ogDescription = metaElement.attr("content");
			}
			else if("og:url".equals(prop)) {
				ogURL = metaElement.attr("abs:content");
			}
			else if("og:image".equals(prop)) {
				ogImage = metaElement.attr("abs:content");
			}
		}
		
		System.out.println("ogTitle => " + ogTitle);
		System.out.println("ogDescription => " + ogDescription);
		System.out.println("ogURL => " + ogURL);
		System.out.println("ogImage => " + ogImage);
		System.out.println();
		
		// generate short url
		// String minixizedOgURL = Storage.getMinimizedURL(ogURL);
		
		PreviewData previewDataWithOgURL = new PreviewData(ogTitle, ogDescription, ogURL, ogImage);
		Storage.putPreviewData(urlFromClient, previewDataWithOgURL);
		
		PreviewData previewDataWithShortURL = Storage.getPreviewDataWithshortURL(urlFromClient, previewDataWithOgURL);
		
		/// sending JSON object to the client
		String previewDataJSON = new Gson().toJson(previewDataWithShortURL);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(previewDataJSON);
		out.flush();
		
		// printing maps
		System.out.println("SHORT-URL MAP:");
		Storage.printShortURLMap();
		System.out.println();
		
		System.out.println("PREVIEW-DATA MAP:");
		Storage.printPreviewDataMap();
		System.out.println();
		
		System.out.println("SHORT-URL-DATA MAP:");
		Storage.printShortURLDataMap();
		System.out.println();
		
	}
}
