
public class State
{
	public int		column;			// the chosen column (to place token)
	public Board	board;			// actual board
	public int		depth;			// actual recursion depth
	public Token	turn;			// which players turn
	public int		alpha;			// alpha value
	public int		beta;			// beta value 	
	
	public State(Board b, int c, int d, Token t, int alpha, int beta)
	{
		board		= b;
		column		= c;		
		depth		= d;
		turn		= t;
		this.alpha  = alpha;
		this.beta   = beta;		
	}
	
	public State(Board b, Token t, int alpha, int beta)
	{
		board	    = b;
		turn	    = t;
		depth	    = 0;
		column		= -1;
		this.alpha  = alpha;
		this.beta   = beta;
	}
}
