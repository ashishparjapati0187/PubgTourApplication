package com.stackroute.favouriteservice.exception;

public class MovieNotFoundException extends Exception {

	

	String message;
	
	public String getmessage()
	{
		return message;
	}
	public void setmessege(String message)
	{
		this.message=message;
	}
	public MovieNotFoundException(final String message)
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
