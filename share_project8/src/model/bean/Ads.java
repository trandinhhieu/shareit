package model.bean;

public class Ads {
	private int id_ad;
	private String name;
	private String picture;
	private String link;
	private int active;
	

	public Ads(int id_ad, String name, String picture, String link, int active) {
		super();
		this.id_ad = id_ad;
		this.name = name;
		this.picture = picture;
		this.link = link;
		this.active = active;
	}

	public Ads() {
	}

	public int getId_ad() {
		return id_ad;
	}

	public void setId_ad(int id_ad) {
		this.id_ad = id_ad;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Ads [id_ad=" + id_ad + ", name=" + name + ", picture=" + picture + ", link=" + link + ", active="
				+ active + "]";
	}

}
