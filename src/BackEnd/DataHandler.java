import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataHandler 
{
	Random rand = new Random();
	int userID = 1000; //rand.nextInt(1000)
	static JSONObject owner;
	static JSONObject restaurant;
	static JSONArray products;
	static JSONArray product;
	static JSONObject customer;
	public JSONArray user_list = new JSONArray();
	public static boolean ReadDatasFromJSON()
	{
		//burada has next gibi bir method olmas� laz�m
		//while d�ng�s�ne sokabilmek i�in
		//ve burada user olu�turulmas� laz�m ki bunu listeye ekleyebilelim
		//User current= dosyadan al�nan verilerle yeni user olu�turulmas�
		User current=null;//=new User(get json username,get json password,get json usertype);
		if(current.usertype.equals(userType.CUSTOMER))
		{
			Admin admin=Admin.createOrGetAdmin();
			User.addNewUserToList(admin);
			//burada database i�lemi yok ��nk� admin zaten bir tane
			//username ve passwordu sabit
		}
		if(current.usertype.equals(userType.CUSTOMER))
		{
			Customer currentCus=((Customer)current);
			//art�k json dakalan verileri currentCus �n �zerine yazaca��z
			User.addNewUserToList(currentCus);//�retilen customer �n
			//listeye customer�n eklenmesi
		}
		else if(current.usertype.equals(userType.OWNER))
		{
			Owner currentOwner=((Owner)current);
			//art�k json da kalan verileri currentOwner �n �zerine yazaca��z.
			User.addNewUserToList(currentOwner);
			//listeye owner �n eklenmesi
		}
		return false;//�ylesine
		
	}
	public static boolean WriteDataToJSON(User user)
	{
		if(user.usertype.equals(userType.CUSTOMER))
		{
			Customer current=((Customer)user);
			//database e yazma i�lemleri
		}
		else if(user.usertype.equals(userType.OWNER))
		{
			Owner current=((Owner)user);
			//database e yazma i�lemleri
		}
		else
		{
			System.out.println("An error has occurred!!");
		}
		return false;//�imdilik �ylesine yazd�m
	}
	@SuppressWarnings("unchecked")
	public void write_customer_data(String username, String password, double balance)
	{		
		customer = new JSONObject();
		customer.put("userName", username );
		customer.put("password", password);
		customer.put("balance", balance);
		customer.put("userType", String.valueOf(userType.CUSTOMER) );
		customer.put("userID", userID );
		userID += 1;
		user_list.add(customer);
		try(FileWriter file = new FileWriter("DataBase.json"))
		{
			file.write(user_list.toString());
			file.flush();
			System.out.println("Customer's data saved");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public void write_owner_data(String username, String password)
	{
		owner = new JSONObject();
		restaurant = new JSONObject();
		owner.put("userName", username );
		owner.put("password", password);
		owner.put("restaurant", restaurant);
		owner.put("userType", String.valueOf(userType.OWNER));
		owner.put("userID", userID );
		userID += 1;
		user_list.add(owner);
		try(FileWriter file = new FileWriter("DataBase.json"))
		{
			file.write(user_list.toString());
			file.flush();
			System.out.println("Owner's data saved");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public void write_restaurant_to_owner(int userID, String restaurantName, double balance)
	{
		products = new JSONArray();
		for (Object object : user_list) 
		{
			JSONObject owner = (JSONObject) object;
			System.out.println(owner.get("userID").toString().equals(String.valueOf(userID)));
			if (owner.get("userID").toString().equals(String.valueOf(userID))) 
			{
				JSONObject restaurant = (JSONObject) owner.get("restaurant");
				
				restaurant.put("name", restaurantName);
				restaurant.put("products", products);
				restaurant.put("balance", balance);
			}			
		}
		try(FileWriter file = new FileWriter("DataBase.json"))
		{
			file.write(user_list.toString());
			file.flush();
			System.out.println("Owner's restaurant data saved");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public void write_product_to_restaurant(int userID, String productName, double price)
	{
		
		for (Object object : user_list) 
		{
			
			JSONObject owner = (JSONObject) object;
			if (owner.get("userID") == String.valueOf(userID)) 
			{
				JSONArray products = (JSONArray) owner.get("products");	
				JSONObject restaurant = (JSONObject) owner.get("restaurant");
				product = new JSONArray();
				product.add(productName);
				product.add(price);
				products.add(product);
				restaurant.put("products", products);
			}			
		}
		try(FileWriter file = new FileWriter("DataBase.json",true))
		{
			file.write(user_list.toString());
			file.flush();
			System.out.println("Restaurant's product data saved");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}