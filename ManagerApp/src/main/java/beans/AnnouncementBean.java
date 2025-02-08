package beans;

import java.io.Serializable;

public class AnnouncementBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private String content;
	public AnnouncementBean(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
