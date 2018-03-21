package model.bean;

import java.sql.Timestamp;

public class News {
	private int id_news;
	private String name;
	private int id_cat;
	private int slide;
	private String picture;
	private String preview_text;
	private String detail_text;
	private Timestamp create_date;
	private int view;
	private String cname;
	private int id_user;
	private int heart;
	private String uname;
	private String avatar;
	
	public News() {
	}

	
	public News(int id_news, String name, int id_cat, int slide, String picture, String preview_text,
			String detail_text, Timestamp create_date, int view, String cname, int id_user, int heart, String uname,
			String avatar) {
		super();
		this.id_news = id_news;
		this.name = name;
		this.id_cat = id_cat;
		this.slide = slide;
		this.picture = picture;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.create_date = create_date;
		this.view = view;
		this.cname = cname;
		this.id_user = id_user;
		this.heart = heart;
		this.uname = uname;
		this.avatar = avatar;
	}


	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
	

	public News(String name, int id_cat, int slide, String picture, String preview_text, String detail_text,
			Timestamp create_date, int view, int id_user, int heart) {
		super();
		this.name = name;
		this.id_cat = id_cat;
		this.slide = slide;
		this.picture = picture;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.create_date = create_date;
		this.view = view;
		this.id_user = id_user;
		this.heart = heart;
	}


	public News(String name, int id_cat, String picture, String preview_text, String detail_text,
			Timestamp create_date, int view, String cname) {
		this.name = name;
		this.id_cat = id_cat;
		this.picture = picture;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.create_date = create_date;
		this.view = view;
		this.cname = cname;
	}
	
	public News(String name, int id_cat, int slide, String picture, String preview_text, String detail_text,
			Timestamp create_date, int view) {
		this.name = name;
		this.id_cat = id_cat;
		this.slide = slide;
		this.picture = picture;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.create_date = create_date;
		this.view = view;
		
	}
	
	public News(int id_news, String name, int id_cat, String picture, String preview_text, String detail_text) {
		this.id_news = id_news;
		this.name = name;
		this.id_cat = id_cat;
		this.picture = picture;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
	}

	public int getSlide() {
		return slide;
	}



	public void setSlide(int slide) {
		this.slide = slide;
	}



	public int getId_news() {
		return id_news;
	}

	public void setId_news(int id_news) {
		this.id_news = id_news;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreview_text() {
		return preview_text;
	}

	public void setPreview_text(String preview_text) {
		this.preview_text = preview_text;
	}

	public String getDetail_text() {
		return detail_text;
	}

	public void setDetail_text(String detail_text) {
		this.detail_text = detail_text;
	}

	public int getId_cat() {
		return id_cat;
	}

	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}



	@Override
	public String toString() {
		return "News [id_news=" + id_news + ", name=" + name + ", id_cat=" + id_cat + ", picture=" + picture
				+ ", preview_text=" + preview_text + ", detail_text=" + detail_text + ", create_date=" + create_date
				+ ", view=" + view + ", cname=" + cname + ", slide=" + slide + "]";
	}

}
