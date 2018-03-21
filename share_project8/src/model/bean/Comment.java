package model.bean;

import java.sql.Timestamp;

public class Comment {
	private int id_com;
	private String name;
	private String email;
	private String content;
	private Timestamp date_create;
	private int id_news;
	private int parent_id;
	private String picture;
	private int active;
	
	
	
	public Comment(int id_com, String name, String email, String content, Timestamp date_create, int id_news,
			int parent_id, String picture, int active) {
		super();
		this.id_com = id_com;
		this.name = name;
		this.email = email;
		this.content = content;
		this.date_create = date_create;
		this.id_news = id_news;
		this.parent_id = parent_id;
		this.picture = picture;
		this.active = active;
	}
	
	

	public Comment(int id_com, String name, String email, String content, Timestamp date_create, int id_news,
			int parent_id, int active) {
		super();
		this.id_com = id_com;
		this.name = name;
		this.email = email;
		this.content = content;
		this.date_create = date_create;
		this.id_news = id_news;
		this.parent_id = parent_id;
		this.active = active;
	}



	public Comment(int id_com, String name, String email, String content, Timestamp date_create, int id_news,
			int parent_id, String picture) {
		super();
		this.id_com = id_com;
		this.name = name;
		this.email = email;
		this.content = content;
		this.date_create = date_create;
		this.id_news = id_news;
		this.parent_id = parent_id;
		this.picture = picture;
	}

	public Comment(int id_com, String name, String email, String content, Timestamp date_create, int id_news,
			int parent_id) {
		super();
		this.id_com = id_com;
		this.name = name;
		this.email = email;
		this.content = content;
		this.date_create = date_create;
		this.id_news = id_news;
		this.parent_id = parent_id;
	}

	public Comment() {
	}
	
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getId_com() {
		return id_com;
	}

	public void setId_com(int id_com) {
		this.id_com = id_com;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getDate_create() {
		return date_create;
	}

	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}

	public int getId_news() {
		return id_news;
	}

	public void setId_news(int id_news) {
		this.id_news = id_news;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	@Override
	public String toString() {
		return "Comment [id_com=" + id_com + ", name=" + name + ", email=" + email + ", content=" + content
				+ ", date_create=" + date_create + ", id_news=" + id_news + ", parent_id=" + parent_id + "]";
	}
	
}
