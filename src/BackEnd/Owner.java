package BackEnd;

public class Owner extends User 
{
	private Restaurant restaurant;
	private double balance;
	//Constructor for added new Owners
	public Owner(String userName,String password,userType usertype,double balance)
	{
		this.userName=userName;
		this.password=password;
		this.usertype=usertype;
		this.balance=balance;
	}
	//Constructor for default
	public Owner(String userName,String password,userType usertype,
			Restaurant restaurant,double balance)
	{
		this.userName=userName;
		this.password=password;
		this.usertype=usertype;
		this.restaurant=restaurant;
		this.balance=balance;
	}
	public Restaurant getRestaurant() 
	{
		return this.restaurant;
	}
	public void setRestaurant(Restaurant restaurant) 
	{
		this.restaurant = restaurant;
	}
	public double getBalance() 
	{
		return balance;
	}
	public void setBalance(double balance) 
	{
		this.balance = balance;
	}
}