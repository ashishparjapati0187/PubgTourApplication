package com.stackroute.favouriteservice.exception;

public class MatchNotFoundException extends Exception {

	

	String message;
	
	public String getmessage()
	{
		return message;
	}
	public void setmessege(String message)
	{
		this.message=message;
	}
	public MatchNotFoundException(final String message)
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
