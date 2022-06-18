package db;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pojo.Banner;
import pojo.PreviewData;
import pojo.User;
import servletpack.ShortURLData;
import servletpack.UpdateMapRunnable;

public class Storage {
	private static Map<String, PreviewData> previewDataMap = new  HashMap<>();	// urlFromClient, metaInfo
	private static Map<String, String> shortURLMap = new HashMap<>();	// shortURL, og:URL
	private static Map<String, ShortURLData> shortURLDataMap = new HashMap<>();	// shortURL, shortURL data
	private static Map<String, Banner> bannerMap = new HashMap<>();	// url, banner
	private static Map<String, User> userMap = new HashMap<>();	// url, user
	
	public static Map<String, String> getShortURLMap() {
		return shortURLMap;
	}
	
	public static Map<String, ShortURLData> getShortURLDataMap() {
		return shortURLDataMap;
	}
	
	public static Map<String, Banner> getBannerMap() {
		return bannerMap;
	}
	
	public static Map<String, User> getUserMap() {
		return userMap;
	}
	
	public static PreviewData getPreviewDataWithshortURL
	(String urlFromClient, PreviewData previewDataWithOgURL) {	// Note: short URL with RANDOM code
		String shortURL = getShortURL(urlFromClient);
		
		shortURLMap.put(shortURL, previewDataWithOgURL.getOgURL());
		
		String ogTitle = previewDataWithOgURL.getOgTitle();
		String ogDescription = previewDataWithOgURL.getOgDescription();
		String ogURL = previewDataWithOgURL.getOgURL();
		String ogImage = previewDataWithOgURL.getOgImage();
		
		PreviewData previewDataWithShortURL = new PreviewData(ogTitle, ogDescription, shortURL, ogImage);
		
		return previewDataWithShortURL;
	}
	
	public static void putShortURL(String shortURL, String ogURL) {
		shortURLMap.put(shortURL, ogURL);
	}
	
	public static String getShortURL(String urlFromClient) {
		String randomCode = "" + getRandomCode();
		String shortURL =  "http://localhost:8181/LinkPreviewer/shorturl/" + randomCode;
		
		while(shortURLMap.containsKey(shortURL)) {
			randomCode = "" + getRandomCode();
			shortURL = "http://localhost:8181/LinkPreviewer/shorturl/" + randomCode;
		}
		
		// adding shortURL to shortURLDataMap
		shortURLDataMap.put(shortURL, new ShortURLData(urlFromClient, shortURL));
		
		return shortURL;
	}
	
	static String getRandomCode() {
		return "" + getRandomSmall() + getRandomCaps() + getRandomDigit() + getRandomCaps() + getRandomSmall();
	}
	
	static char getRandomCaps() {
		return (char) ((int) (Math.random() * 26) + 65);
	}
	
	static char getRandomSmall() {
		return (char) ((int) (Math.random() * 26) + 97);
	}
	
	static char getRandomDigit() {
		return (char) ((int) (Math.random() * 10) + 48);
	}
	
	// ==========================================================================================================
	
	public static PreviewData getPreviewData(String urlFromClient) {
		return previewDataMap.get(urlFromClient);
	}
	
	public static void putPreviewData(String urlFromClient, PreviewData previewDataWithOgURL) {
		previewDataMap.put(urlFromClient, previewDataWithOgURL);
	}
	
	static void updatePreviewDataMap() {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(Map.Entry<String, PreviewData> entry : previewDataMap.entrySet()) {
			executor.submit(new UpdateMapRunnable());
		}
	}
	
	public static void periodicUpdate(String args[]) {
		// update for every 1 min
	}
	
	public static void printShortURLMap() {
		System.out.println("{");
		for(Map.Entry<String, String> entry : shortURLMap.entrySet()) {
			System.out.println("# " + entry.getKey() + " => " + entry.getValue());
		}
		System.out.println("}");
	}
	
	public static void printPreviewDataMap() {
		System.out.println("{");
		for(Map.Entry<String, PreviewData> entry : previewDataMap.entrySet()) {
			System.out.println("## " + entry.getKey() + " => " + entry.getValue());
		}
		System.out.println("}");
	}
	
	public static void printShortURLDataMap() {
		System.out.println("{");
		for(Map.Entry<String, ShortURLData> entry : shortURLDataMap.entrySet()) {
			System.out.println("### " + entry.getKey() + " => " + entry.getValue().toString());
		}
		System.out.println("}");
	}
	
	public static void printBannerMap() {
		System.out.println("{");
		for(Map.Entry<String, Banner> entry : bannerMap.entrySet()) {
			System.out.println("#### " + entry.getKey() + " => " + entry.getValue().toString());
		}
		System.out.println("}");
	}
	
	public static void printUserMap() {
		System.out.println("{");
		for(Map.Entry<String, User> entry : userMap.entrySet()) {
			System.out.println("##### " + entry.getKey() + " => " + entry.getValue().toString());
		}
		System.out.println("}");
	}
}
