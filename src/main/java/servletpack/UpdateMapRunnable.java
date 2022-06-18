package servletpack;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pojo.PreviewData;

public class UpdateMapRunnable implements Runnable {
	private String ogURL;
	private String userAgent;
	private PreviewData previewData;
	
	public UpdateMapRunnable() {}
	
	public UpdateMapRunnable(String userAgent, Map.Entry<String, PreviewData> entry) {
		this.ogURL = entry.getKey();
		this.userAgent = userAgent;
		this.previewData = entry.getValue();
	}
	
	@Override
	public void run() {
		Document doc = null;
		try {
			doc = Jsoup.connect(this.ogURL)
					.userAgent(this.userAgent)
					.get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(doc == null) {
			return;
		}
		
		Elements metaElements = doc.getElementsByTag("meta");
		String updatedOgTitle = "";
		String updatedOgDescription = "";
		String updatedOgURL = "";
		String updatedOgImage = "";
		
		for(Element metaElement : metaElements) {
			String prop = metaElement.attr("property");
			if("og:title".equals(prop)) {
				updatedOgTitle = metaElement.attr("content");
			}
			else if("og:description".equals(prop)) {
				updatedOgDescription = metaElement.attr("content");
			}
			else if("og:url".equals(prop)) {
				updatedOgURL = metaElement.attr("abs:content");
			}
			else if("og:image".equals(prop)) {
				updatedOgImage = metaElement.attr("abs:content");
			}
		}
		
		this.previewData.setOgTitle(updatedOgTitle);
		this.previewData.setOgDescription(updatedOgDescription);
		this.previewData.setOgURL(updatedOgURL);
		this.previewData.setOgImage(updatedOgImage);
	}
}
