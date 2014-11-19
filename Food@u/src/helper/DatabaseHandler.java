package helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Eatery;
import model.Review;
import model.User;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "FOOD";
    
    private static final String TABLE_USERS = "user";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_FIRSTNAME = "firstname";
    private static final String USER_LASTNAME = "lastname";
    private static final String USER_VEGETERIAN = "vegeterian";
    private static final String USER_VEGAN = "vegan";
    private static final String USER_GLUTEN = "gluten";
    private static final String USER_BUDGET = "budget";
    
    private static final String TABLE_EATERY = "eatery";
    private static final String EATERY_ID = "id";
    private static final String EATERY_NAME = "name";
    private static final String EATERY_ADDRESS1 = "address1";
    private static final String EATERY_ADDRESS2 = "address2";
    private static final String EATERY_ADDRESS3 = "address3";
    private static final String EATERY_VEGETERIAN = "vegeterian";
    private static final String EATERY_VEGAN = "vegan";
    private static final String EATERY_KOSHER = "kosher"; 
    private static final String EATERY_WIFI = "wifi";
    private static final String EATERY_PARKING = "parking";
    private static final String EATERY_LPRICE = "lprice";
    private static final String EATERY_MPRICE = "mprice";
    private static final String EATERY_HPRICE = "hprice";
    
    private static final String TABLE_REVIEW = "review";
    private static final String REVIEW_EMAIL = "email";
    private static final String REVIEW_EATERY = "eatery";
    private static final String REVIEW_FOOD = "food";
    private static final String REVIEW_AMBIENCE = "ambience";
    private static final String REVIEW_ECONOMY = "economy";
    private static final String REVIEW_CLEANLINESS = "cleanliness";
    private static final String REVIEW_SERVICE = "service";
    private static final String REVIEW_COMMENT = "comment";
    private static final String REVIEW_DATE = "date";
    
    //private static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

	public DatabaseHandler(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createDB(db);
	}

	private void createDB(SQLiteDatabase db) {

		String CREATE_USER_TABLE = "CREATE TABLE " +  TABLE_USERS + "( " +
				USER_EMAIL + " TEXT PRIMARY KEY, " + 
				USER_PASSWORD + " TEXT, " +
				USER_FIRSTNAME + " TEXT, "+
				USER_LASTNAME + " TEXT, " +
				USER_VEGETERIAN + " INTEGER, " +
				USER_VEGAN + " INTEGER, " +
				USER_GLUTEN + " INTEGER, " +
				USER_BUDGET + " REAL)";
		
		String CREATE_EATERY_TABLE = "CREATE TABLE " +  TABLE_EATERY + "( " +
				EATERY_ID + " INTEGER PRIMARY KEY, " + 
				EATERY_NAME + " TEXT, " +
				EATERY_ADDRESS1 + " TEXT, "+
				EATERY_ADDRESS2 + " TEXT, " +
				EATERY_ADDRESS3 + " TEXT, " +
				EATERY_VEGETERIAN + " INTEGER, " +
				EATERY_VEGAN + " INTEGER, " +
				EATERY_KOSHER + " INTEGER, " +
				EATERY_WIFI + " INTEGER, " +
				EATERY_PARKING + " INTEGER, " +
				EATERY_LPRICE + " INTEGER, " +
				EATERY_MPRICE + " INTEGER, " +
				EATERY_HPRICE + " INTEGER)";
		
		String CREATE_REVIEW_TABLE = "CREATE TABLE " +  TABLE_REVIEW + "( " +
				REVIEW_EMAIL + " TEXT NOT NULL, " + 
				REVIEW_EATERY + " INT NOT NULL, " +
				REVIEW_FOOD + " REAL, " +
				REVIEW_AMBIENCE + " REAL, " +
				REVIEW_ECONOMY + " REAL, " +
				REVIEW_CLEANLINESS + " REAL, " +
				REVIEW_SERVICE + " REAL, " +
				REVIEW_COMMENT + " TEXT, " +
				REVIEW_DATE + " DATE, " +
				"PRIMARY KEY(" + REVIEW_EMAIL + ", " +  REVIEW_EATERY + "))";
		
		
		db.execSQL(CREATE_USER_TABLE);
		db.execSQL(CREATE_EATERY_TABLE);
		db.execSQL(CREATE_REVIEW_TABLE);
		populate(db);
			
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EATERY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW);
        this.onCreate(db);
	}
	
	public void populate(SQLiteDatabase db){
		List<User> users = new ArrayList<User>();
		users.add(new User("poual001@umn.edu", "Joel001", "Joel", "Poualeu",  0, 0, 0, 5));
		users.add(new User("prasa077@umn.edu", "Prati077", "Prathibha", "Prasanna Kumar",  1, 0, 0, 6 ));
		users.add(new User("shahx118@umn.edu", "Sam118", "Sameera", "Shah",  1, 1, 1, 7 ));
		users.add(new User("sharm274@umn.edu", "Shrimi274", "Shrimi", "Sharma",  0, 0, 0, 8 ));
		users.add(new User("umesh001@umn.edu", "Varun001", "Varun", "Umesh",  1, 1, 1, 6 ));
		
		List<Eatery> eateries = new ArrayList<Eatery>();
		eateries.add(new Eatery(1, "Topio's", "Coffman Union's Minnesota Marketplace, Ground Level", "East Bank, Minneapolis", "55455", 0, 1, 0, 1, 0, 0, 1, 1));
		eateries.add(new Eatery(2, "Baja Sol", "Coffman Union's Minnesota Marketplace, Ground Level", "East Bank, Minneapolis", "55455", 0, 1, 0, 1, 0, 0, 0, 1));
		eateries.add(new Eatery(3, "Caribou Coffee", "Moos Tower Lower Level", "East Bank, Minneapolis", "55455", 0, 1, 0, 1, 0, 1, 0, 0));
		eateries.add(new Eatery(4, "Hay Loft Cafe", "Ben Pomeroy Center , Ground Level", " St. Paul", "55108", 0, 1, 0, 1, 0, 1, 0, 0));
		eateries.add(new Eatery(5, "Panda Express", "Carlson School of Management, Lower Level", "West Bank, Minneapolis", "55455", 0, 1, 0, 1, 0, 1, 0, 0));
		eateries.add(new Eatery(6, "Wise Owl Cafe", "Walter Library , Lower Level", "East Bank", "55455", 0, 1, 0, 1, 0, 1, 1, 1));
		eateries.add(new Eatery(7, "Jamba Juice", "Coffman Union's Minnesota Marketplace ", "First Floor (next to the Commuter's Lounge)", "55455", 0, 1, 0, 1, 0, 1, 1, 1));
		eateries.add(new Eatery(8, "Papa John's", "St. Paul Student Center's Terrace Cafe , Main Level", "St. Paul", "55108", 0, 1, 0, 1, 0, 1, 1, 1));
		eateries.add(new Eatery(9, "Burger Studio", "Carlson School of Management, Lower Level", "West Bank, Minneapolis", "55455", 0, 1, 0, 1, 0, 1, 1, 1));
		eateries.add(new Eatery(10, "Bistro West Restaurant", "Humphrey Center, Lower Level", "West Bank, Minneapolis", "55455", 0, 1, 0, 1, 0, 1, 1, 1));
		eateries.add(new Eatery(11, "Chick-fil-A", "Coffman Union's Minnesota Marketplace, Ground Level", "East Bank, Minneapolis", "55455", 0, 1, 0, 1, 0, 0, 0, 1));
		eateries.add(new Eatery(12, "Greens To Go", "St. Paul Student Center's Terrace Cafe , Main Level", "St. Paul", "55108", 0, 1, 0, 1, 0, 1, 1, 1));
		eateries.add(new Eatery(13, "Subway", "Essentials Market in Blegen Hall, Lowel Level West Bank","Minneapolis","55455",0, 1, 0, 1, 0, 1, 1, 1));
		
		List<Review> reviews = new ArrayList<Review>();
	//	reviews.add(new Review("poual001@umn.edu", 1, 2, 3, 3, 3, 2, "", new Date()));
	//	reviews.add(new Review("shahx118@umn.edu", 1, 3, 3, 3, 3, 3, "", new Date()));
		reviews.add(new Review("poual001@umn.edu", 2, 4, 1, 4, 4, 3, "Love it!", new Date()));
		reviews.add(new Review("shahx118@umn.edu", 2, 3, 4 , 3, 3, 1, "", new Date()));
		reviews.add(new Review("poual001@umn.edu", 3, 3, 3, 3, 4, 3, "Love the Mint Condition Mocha!!", new Date()));
		reviews.add(new Review("shahx118@umn.edu", 3, 3, 2, 3, 3, 1, "Pumpkin Spice Latte is awesome.", new Date()));
		reviews.add(new Review("poual001@umn.edu", 5, 2, 1, 4, 4, 3, "", new Date()));
		reviews.add(new Review("shahx118@umn.edu", 5, 4, 4, 5, 3, 1, "", new Date()));
		reviews.add(new Review("poual001@umn.edu", 7, 2, 1, 4, 4, 3, "", new Date()));
		reviews.add(new Review("shahx118@umn.edu", 7, 4, 4, 5, 3, 1, "", new Date()));
		reviews.add(new Review("poual001@umn.edu", 8, 2, 1, 4, 4, 3, "", new Date()));
		reviews.add(new Review("shahx118@umn.edu", 8, 4, 4, 5, 3, 1, "", new Date()));
		reviews.add(new Review("poual001@umn.edu", 9, 2, 1, 4, 4, 3, "Burgers are pretty good", new Date()));
		reviews.add(new Review("shahx118@umn.edu", 9, 4, 4, 5, 3, 1, "", new Date()));
		
		ContentValues values;

		for (User user : users){
			values = new ContentValues();
			values.put(USER_EMAIL, user.getEmail());
		    values.put(USER_PASSWORD, user.getPassword());
		    values.put(USER_FIRSTNAME, user.getFirstname()); 
		    values.put(USER_LASTNAME, user.getLastname());
		    values.put(USER_VEGETERIAN, user.getVegeterian());
		    values.put(USER_VEGAN, user.getVegan());
		    values.put(USER_GLUTEN, user.getGluten());
		    values.put(USER_BUDGET, user.getBudget());
		    db.insert(TABLE_USERS, null, values);
		}
		
		for(Eatery eatery : eateries){
			values = new ContentValues();
			values.put(EATERY_ID, eatery.getId());
		    values.put(EATERY_NAME, eatery.getName());
		    values.put(EATERY_ADDRESS1, eatery.getAddress1()); 
		    values.put(EATERY_ADDRESS2, eatery.getAddress2());
		    values.put(EATERY_ADDRESS3, eatery.getAddress3());
		    values.put(EATERY_VEGETERIAN, eatery.getVegeterian());
		    values.put(EATERY_VEGAN, eatery.getVegan());
		    values.put(EATERY_KOSHER, eatery.getKosher());
		    values.put(EATERY_WIFI, eatery.getWifi());
		    values.put(EATERY_PARKING, eatery.getParking());
		    values.put(EATERY_LPRICE, eatery.getLPrice());
		    values.put(EATERY_MPRICE, eatery.getMPrice());
		    values.put(EATERY_HPRICE, eatery.getHPrice());
		    db.insert(TABLE_EATERY, null, values);
		}
		
		for(Review rev : reviews){
			values = new ContentValues();
			values.put(REVIEW_EMAIL, rev.getEmail());
		    values.put(REVIEW_EATERY, rev.getEatery());
		    values.put(REVIEW_FOOD,rev.getFood()); 
		    values.put(REVIEW_AMBIENCE, rev.getAmbience());
		    values.put(REVIEW_CLEANLINESS, rev.getCleanliness());
		    values.put(REVIEW_ECONOMY, rev.getEconomy());
		    values.put(REVIEW_SERVICE, rev.getService());
		    values.put(REVIEW_COMMENT, rev.getComment());
		    db.insert(TABLE_REVIEW, null, values);
		}
		
	
	}
	

	public void addUser(User user) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(USER_EMAIL, user.getEmail()); 
	    values.put(USER_PASSWORD, user.getPassword());
	    values.put(USER_FIRSTNAME, user.getFirstname()); 
	    values.put(USER_LASTNAME, user.getLastname());
	    values.put(USER_VEGETERIAN, user.getVegeterian());
	    values.put(USER_VEGAN, user.getVegan());
	    values.put(USER_GLUTEN, user.getGluten());
	    values.put(USER_BUDGET, user.getBudget());
	    
	 
	    db.insert(TABLE_USERS, null, values);
	    db.close(); 
	}
	
	public List<Eatery> getEateries() {
	    List<Eatery> eateries = new ArrayList<Eatery>();
	    SQLiteDatabase db = this.getWritableDatabase();
	    //String query = "SELECT  * FROM " + TABLE_EATERY;
	    //Cursor cursor = db.rawQuery(query, null);
	    Cursor cursor = db.query(TABLE_EATERY, null,
                null, null, null, null, null);

	    if (cursor.moveToFirst()) {
	           do {
	        	   eateries.add(
	        			   new Eatery(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
	        					   cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5))
	        					   , Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7))
	        					   , Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9))
	        					   , Integer.parseInt(cursor.getString(10)), Integer.parseInt(cursor.getString(11))
	        					   , Integer.parseInt(cursor.getString(12))));
	           } while (cursor.moveToNext());
	       }
	    return eateries;
	  }
	
	public List<Review> getEateryReviews(int eatery) {
	    List<Review> reviews = new ArrayList<Review>();
	    SQLiteDatabase db = this.getWritableDatabase();
	    //String query = "SELECT  * FROM " + TABLE_EATERY;
	    //Cursor cursor = db.rawQuery(query, null);
	    Cursor cursor = db.query(TABLE_REVIEW, null,
	    		REVIEW_EATERY + "=?", new String[] { String.valueOf(eatery) }, null, null, null);
	    
	    if (cursor.moveToFirst()) {
	           do {
	   
					reviews.add(new Review(cursor.getString(0), cursor.getInt(1), cursor.getInt(2), 
							   cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getString(7), new Date()));

	           } while (cursor.moveToNext());
	       }
	    return reviews;
	  }
	
	public List<Review> getUserReviews(String user) {
	    List<Review> reviews = new ArrayList<Review>();
	    SQLiteDatabase db = this.getWritableDatabase();
	    //String query = "SELECT  * FROM " + TABLE_EATERY;
	    //Cursor cursor = db.rawQuery(query, null);
	    Cursor cursor = db.query(TABLE_REVIEW, null,
	    		REVIEW_EMAIL + "=?", new String[] { user }, null, null, null);

	    if (cursor.moveToFirst()) {
	           do {
					reviews.add(new Review(cursor.getString(0), cursor.getInt(1), cursor.getInt(2), 
							   cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getString(7), new Date()));
	           } while (cursor.moveToNext());
	       }
	    return reviews;
	  }
	
	public List<Review> getReviews() {
	    List<Review> reviews = new ArrayList<Review>();
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.query(TABLE_REVIEW, null,
	    		null, null, null, null, null);

	    if (cursor.moveToFirst()) {
	           do {
	        	   //try {
					reviews.add(new Review(cursor.getString(0), cursor.getInt(1), cursor.getInt(2), 
							   cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getString(7), new Date()));
				//} catch (ParseException e) {
					//e.printStackTrace();
				//}
	           } while (cursor.moveToNext());
	       }
	    return reviews;
	  }
	
	public void addReview(Review review) {
	    SQLiteDatabase db = this.getWritableDatabase();
	    
	    ContentValues values = new ContentValues();
	    values.put(REVIEW_EATERY,review.getEatery());
	    values.put(REVIEW_FOOD, review.getFood()); 
	    values.put(REVIEW_AMBIENCE, review.getAmbience());
	    values.put(REVIEW_ECONOMY, review.getEconomy()); 
	    values.put(REVIEW_CLEANLINESS, review.getCleanliness());
	    values.put(REVIEW_SERVICE, review.getService());
	    values.put(REVIEW_COMMENT, review.getComment());
	    values.put(REVIEW_EMAIL, review.getEmail());
	    db.insert(TABLE_REVIEW, null, values);
	    db.close(); 
	}
	
	public User getUser(String id) {
	    SQLiteDatabase db = this.getReadableDatabase();
	    Cursor cursor = db.query(TABLE_USERS, new String[] { USER_EMAIL, USER_PASSWORD,
	    		USER_FIRSTNAME, USER_LASTNAME, USER_VEGETERIAN, USER_VEGAN, USER_GLUTEN, USER_BUDGET}, USER_EMAIL + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
	    if (cursor != null)
	        cursor.moveToFirst();
	    User user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), 
	    		cursor.getString(3), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)),
	    		Integer.parseInt(cursor.getString(6)), Double.parseDouble(cursor.getString(7)));
	    return user;
	}
	
	public Eatery getEatery(int id) {
	    SQLiteDatabase db = this.getReadableDatabase();
	    Cursor cursor = db.query(TABLE_EATERY, null, EATERY_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
	    if (cursor != null)
	        cursor.moveToFirst();
	    Eatery eatery = new Eatery(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
				   cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5))
				   , Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7))
				   , Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9))
				   , Integer.parseInt(cursor.getString(10)), Integer.parseInt(cursor.getString(11))
				   , Integer.parseInt(cursor.getString(12)));
	    return eatery;
	}
	
	public boolean validateUser(String id, String password){
		SQLiteDatabase db = this.getReadableDatabase();
	    Cursor cursor = db.query(TABLE_USERS, new String[] { USER_EMAIL, USER_PASSWORD,
	    		USER_FIRSTNAME, USER_LASTNAME, USER_VEGETERIAN}, USER_EMAIL + "=? AND " + USER_PASSWORD + "=?",
	            new String[] { String.valueOf(id), String.valueOf(password) }, null, null, null, null);
	    boolean result = (cursor.getCount() > 0);
	    cursor.close();
	    return  result;
	}
	
	public int updateUser(User user) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(USER_EMAIL, user.getEmail()); 
	    values.put(USER_PASSWORD, user.getPassword());
	    values.put(USER_FIRSTNAME, user.getFirstname()); 
	    values.put(USER_LASTNAME, user.getLastname());
	    values.put(USER_VEGETERIAN, user.getVegeterian());
	 
	    // updating row
	    return db.update(TABLE_USERS, values, USER_EMAIL + " = ?",
	            new String[] { user.getEmail() });
	}
	
	

}
