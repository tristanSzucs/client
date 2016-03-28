package client;

public class User 
{
	private String userName;
	private char[] password;
	
	//user constructor
	public User(String user, char[] pass)
	{
		this.userName = user;
		this.password = pass;
	}
	
	//returns user's name
	public String getUserName()
	{
		return this.userName;
	}
	
	//returns user's password
	public String getPassword()
	{
		return this.password.toString();
	}
}
