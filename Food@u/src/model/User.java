package model;

public class User {
	
	private String email;
	private String password;
	private String firstname;
	private String lastname;
	private int vegeterian;
	private int vegan;
	private int gluten;
	private double budget;
	
	public User(){}
	
	public User(String mail,String pwd, String fname, String lname, int veget, int veg, int glu, double bud) {
		super();
		this.firstname = fname;
		this.lastname = lname;
		this.vegeterian = veget;
		this.email = mail;
		this.password = pwd;
		this.vegan = veg;
		this.gluten = glu;
		this.budget = bud;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String fname) {
		this.firstname = fname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lname) {
		this.lastname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String mail) {
		this.email = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String pwd) {
		this.password = pwd;
	}
	public int getVegeterian() {
		return vegeterian;
	}
	public void setVegeterian(int veget) {
		this.vegeterian = veget;
	}
	
	public int getVegan() {
		return vegan;
	}
	public void setVegan(int veg) {
		this.vegan = veg;
	}
	
	public int getGluten() {
		return gluten;
	}
	public void setGluten(int glu) {
		this.gluten = glu;
	}
	
	public double getBudget() {
		return budget;
	}
	public void setBudget(double bud) {
		this.budget = bud;
	}
	
	@Override
	public String toString() {
		return this.firstname + " " + this.lastname;
	}
	

}
