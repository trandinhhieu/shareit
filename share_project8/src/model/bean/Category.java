package model.bean;

public class Category {
	private int id_cat;
	private String name;
	private int id_parent;
	
	public Category() {
	}

	public Category(int id_cat, String name, int id_parent) {
		this.id_cat = id_cat;
		this.name = name;
		this.id_parent = id_parent;
	}

	public int getId_cat() {
		return id_cat;
	}

	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getId_parent() {
		return id_parent;
	}

	public void setId_parent(int id_parent) {
		this.id_parent = id_parent;
	}

	@Override
	public String toString() {
		return "Category [id_cat=" + id_cat + ", name=" + name + "]";
	}
}
