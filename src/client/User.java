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
	
	//takes change password request variables
	//returns empty string on success, error message on failure
	public String changePassword(char[] currentPass, char[] newPass, char[] repeatedNewPass)
	{
		if(newPass == repeatedNewPass)
		{
			System.out.println("Password change successful");
			
			//send signal to server for official password change
			
			this.password = newPass;
			return "";
		}
		System.err.println("Error on password change");
		return "Error on password change";
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
