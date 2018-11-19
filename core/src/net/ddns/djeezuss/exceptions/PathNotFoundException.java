package net.ddns.djeezuss.exceptions;

public class PathNotFoundException extends Exception
{
	@Override
	public String getMessage()
	{
		return "Path not found";
	}
}
