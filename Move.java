
public class Move 
{
	public int 		rating;			// the rating of the move
	public int	 	column;			// where to place toke
	public int[]	possibleMoves;	
	
	public Move(int r, int c)
	{
		rating			= r;
		column			= c;
	}	
}