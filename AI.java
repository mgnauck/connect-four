
public abstract class AI {
	
	protected Token ownToken;
	
	public AI(Token t)
	{
		ownToken = t;
	}

	public abstract int calcNextMove(Board board);   
}
