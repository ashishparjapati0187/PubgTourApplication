package com.stackroute.favouriteservice.exception;

@SuppressWarnings("serial")
public class MovieAlreadyExistsException extends Exception {

	
	String message;
	
	public String getmessage()
	{
		return message;
	}
	public void setmessege(String message)
	{
		this.message=message;
	}
	public MovieAlreadyExistsException(final String message)
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
