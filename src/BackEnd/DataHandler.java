package BackEnd;


import org.json.simple.JSONArray;


public class DataHandler 
{

	public static WriteData wd = new WriteData();
	public JSONArray user_list = new JSONArray();
	
	public static boolean ReadDatasFromJSON()
	{
		//burada has next gibi bir method olmas� laz�m
		//while d�ng�s�ne sokabilmek i�in
		//ve burada user olu�turulmas� laz�m ki bunu listeye ekleyebilelim
		//User current= dosyadan al�nan verilerle yeni user olu�turulmas�
		User current=null;//=new User(get json username,get json password,get json usertype);
		userType currentType;
		String usertypes="";//mesela usertype i�in �ekti�imiz string usertypes olsun
		if(usertypes.equals("Owner"))
		{
			currentType=userType.OWNER;
		}
		else if(usertypes.equals("Customer"))
		{
			currentType=userType.CUSTOMER;
		}
		//TAM BURADA USER NEW USER DENEREK OLU�TURULMALI
		//User a=new User(); �stte ayarlad���m�z currentType kullan�larak
		if(current.usertype.equals(userType.ADMIN))
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
			wd.write_customer_data(current.getUserName(), current.getPassword(), current.getBalance());
		}
		else if(user.usertype.equals(userType.OWNER))
		{
			Owner current=((Owner)user);
			wd.write_owner_data(current.getUserName(), current.getPassword());
		}
		else
		{
			System.out.println("An error has occurred!!");
		}
		return false;//�imdilik �ylesine yazd�m
	}
}