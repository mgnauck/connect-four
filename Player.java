
public abstract class Player {
	
	protected String	name;
	protected Token		token;
	protected Board		board;
	
	public Player(String n, Token t)
	{
		name 	= n;
		token	= t;
		board	= null;
	}

	public String getName()
	{
		return name;
	}

	public Token getToken()
	{
		return token;
	}
	
	public void setBoardRef( Board b )
	{
		board = b;
	}

	public abstract boolean isHuman();
	public abstract int getNextMove();	
}