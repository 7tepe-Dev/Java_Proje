public class Main 
{
	public static void main(String[] args) 
	{
		ReadData reader=new ReadData();
		reader.readAllUserfromDB();
		System.out.println("Before:");
		for(User user:User.getAllUsers())
		{
			if(user.usertype==userType.OWNER)
			{
				Owner owner=((Owner)user);
				if(owner.getRestaurant()!=null)
				{
					System.out.println(owner.getRestaurant().getRestaurantName());
				}
			}
			System.out.println(user.getUserName());
		}
		for(int i=0;i<User.getAllUsers().size();i++)
		{
			if(User.getAllUsers().get(i).getUserName().equals("merve"))
			{
				User.removeUserFromList("merve");
			}
		}
		System.out.println("After:");
		for(User u:User.getAllUsers())
		{
			System.out.println(u.getUserName());
		}

		
		WriteData writer=new WriteData();
		//login ve signup i�lemlerinden �nce arkaplanda
		//�nceden olu�turulan user lar� ve restoranlar�
		//json dosyas� arac�l��� ile almam�z ve olu�turmam�z laz�m
		//boolean areDefaultsReady=DefaultThingsHandler.LoadDefaultsAndCheck();
		//Download images
		//Create default customers and restaurants
		
		/*else
		{
			System.out.println("An error has occurred.");
		}*/
	}
}