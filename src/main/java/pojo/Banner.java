package pojo;

public class Banner {
	private String title;
	private String description;
	private String url;
	
	public Banner() {}
	
	public Banner(String title, String description, String url) {
		this.title = title;
		this.description = description;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return this.title + ", "
				+ this.description + ", "
				+this.url;
	}
}
