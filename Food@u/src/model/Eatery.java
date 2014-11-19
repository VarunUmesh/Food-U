package model;

public class Eatery {

	private int id;
	private String name;
	private String address1;
	private String address2;
	private String address3;
	private int vegeterian;
	private int vegan;
	private int kosher;
	private int wifi;
	private int parking;
	private int lprice;
	private int mprice;
	private int hprice;
	
	public Eatery(){}
	
	public Eatery(int i, String n, String a1, String a2, String a3, int veget, int veg, int kos, int wi, int p, int lp, int mp, int hp) {
		super();
		this.id = i;
		this.name = n;
		this.address1 = a1;
		this.address2 = a2;
		this.address3 = a3;
		this.vegeterian = veget;
		this.vegan = veg;
		this.kosher = kos;
		this.wifi = wi;
		this.parking = p;
		this.lprice = lp;
		this.mprice = mp;
		this.hprice = hp;		
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getAddress1(){
		return address1;
	}
	
	public String getAddress2(){
		return address2;
	}
	public String getAddress3(){
		return address3;
	}
	
	public int getVegeterian(){
		return vegeterian;
	}
	
	public int getVegan(){
		return vegan;
	}
	
	public int getKosher(){
		return kosher;
	}
	
	public int getWifi(){
		return wifi;
	}
	
	public int getParking(){
		return parking;
	}
	
	public int getLPrice(){
		return lprice;
	}
	
	public int getMPrice(){
		return mprice;
	}
	
	public int getHPrice(){
		return hprice;
	}
}
