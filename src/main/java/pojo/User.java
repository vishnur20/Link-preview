package pojo;

public class User {
	private String fName;
	private String lName;
	private String age;
	private String url;
	
	public User() {}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return this.fName + ", "
				+ this.lName + ", "
				+ this.age + ", "
				+ this.url;
	}
}
