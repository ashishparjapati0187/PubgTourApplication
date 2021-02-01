package com.stackroute.userservice.exceptions;

@SuppressWarnings("serial")
public class UserAlreadyExistException extends Exception {

	
	String message;
	
	public String getmessage()
	{
		return message;
	}
	public void setmessege(String message)
	{
		this.message=message;
	}
	public UserAlreadyExistException(final String message)
	{
		super(message);
		this.message=message;
	}
	
	@Override
	public String toString()
	{
		return "MovieAlreadyExistsException [message="+ message+"]";
	}
}
