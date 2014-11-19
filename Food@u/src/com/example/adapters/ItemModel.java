package com.example.adapters;

import java.util.List;

public class ItemModel {
	private int icon;
	private String title;
	private String description;
	private String subdescription;
	private String extra;
	private String id;
	private int key;
	private List<String> cuisine;
    
    public ItemModel(){
        super();
    }
    
    public ItemModel(int icon, String title, String desc, String desc2, String ex, String id, int key) {
        super();
        this.icon = icon;
        this.title = title;
        this.id = id;
        this.description = desc;
        this.subdescription = desc2;
        this.extra = ex;
        this.key = key;
    }
    
    
    public String getTitle(){
    	return title;
    }
    
    public String getId(){
    	return id;
    }
    
    public int getKey(){
    	return key;
    }
    
    public String getDescription(){
    	return description;
    }
    
    public String getSubDescription(){
    	return subdescription;
    }
    
    public String getExtra(){
    	return extra;
    }
    
    public int getIcon(){
    	return icon;
    }
    
    public List<String> getCuisine(){
    	return cuisine;
    }
}
