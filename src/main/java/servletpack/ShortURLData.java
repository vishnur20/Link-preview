package servletpack;

import java.util.LinkedList;
import java.util.List;

public class ShortURLData {
	private String userURL;
	private String shortURL;
	private long clicks;
	private List<String> userAgents;
	
	public ShortURLData(String userURL, String shortURL) {
		this.userURL = userURL;
		this.shortURL = shortURL;
		this.clicks = 0;
		this.userAgents = new LinkedList<>();
	}
	
	public void incClicks() {
		this.clicks = this.clicks + 1;
	}
	
	public void addUserAgent(String userAgent) {
		this.userAgents.add(userAgent);
	}
	
	@Override
	public String toString() {
		String ogURL = this.userURL;
		String shortURL = this.shortURL;
		long clicks = this.clicks;
		List<String> userAgent = this.userAgents;
		
		return ogURL + ", " + shortURL + ", " + clicks + " and, " + userAgent.toString();
	}
}
