package pojo;

import java.io.Serializable;

public class PreviewData implements Serializable {
	private String ogTitle;
	private String ogDescription;
	private String ogURL;
	private String ogImage;
	
	public PreviewData() {}
	
	public PreviewData(String ogTitle, String ogDescription, String ogURL, String ogImage) {
		this.ogTitle = ogTitle;
		this.ogDescription = ogDescription;
		this.ogURL = ogURL;
		this.ogImage = ogImage;
	}

	public String getOgTitle() {
		return ogTitle;
	}

	public void setOgTitle(String ogTitle) {
		this.ogTitle = ogTitle;
	}

	public String getOgDescription() {
		return ogDescription;
	}

	public void setOgDescription(String ogDescription) {
		this.ogDescription = ogDescription;
	}

	public String getOgURL() {
		return ogURL;
	}

	public void setOgURL(String ogURL) {
		this.ogURL = ogURL;
	}

	public String getOgImage() {
		return ogImage;
	}

	public void setOgImage(String ogImage) {
		this.ogImage = ogImage;
	}
}
