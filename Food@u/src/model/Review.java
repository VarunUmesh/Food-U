package model;

import java.util.Date;

public class Review {
	private String email;
	private int eatery;
	private float food;
	private float ambience;
	private float economy;
	private float cleanliness;
	private float service;
	private Date date;
	private String comment;
	
	
	public Review(String m, int r, float f, float a, float e, float c, float s, String text, Date d) {
		super();
		this.email = m;
		this.eatery = r;
		this.food = f;
		this.ambience = a;
		this.economy = e;
		this.cleanliness = c;
		this.service = s;
		this.comment = text;
		this.date = d;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String m){
		this.email = m;
	}
	
	public int getEatery(){
		return eatery;
	}
	
	public void setEatery(int r){
		this.eatery = r;
	}
	
	public float getFood(){
		return food;
	}
	
	public void setFood(int f){
		this.food = f;
	}
	
	public float getAmbience(){
		return ambience;
	}
	
	public void setAmbience(int a){
		this.ambience = a;
	}
	
	public float getEconomy(){
		return economy;
	}
	
	public void setEconomy(int e){
		this.economy = e;
	}
	
	public float getCleanliness(){
		return cleanliness;
	}
	
	public void setCleanliness(int c){
		this.cleanliness = c;
	}
	
	public float getService(){
		return service;
	}
	
	public void setService(int s){
		this.service = s;
	}
	
	public String getComment(){
		return comment;
	}
	
	public void setComment(String c){
		this.comment = c;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setDate(Date d){
		this.date = d;
	}


}
