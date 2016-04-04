
import java.io.*;

public class Human extends Player {

	private static BufferedReader stdin = new BufferedReader( new InputStreamReader( System.in ) );

	public Human(String n, Token t)
	{
		super(n, t);
	}
		
	public int getNextMove() {
	
		int nextPos = -1;

		// try until we have a valid input
		while( nextPos == -1 ) {
			nextPos = getUserInput();
			if( ( nextPos < 0 ) || ( nextPos >= board.getCols() ) ) {
				System.out.println( "try to hit a number between 0 and " + ( board.getCols() - 1 ) + "!" );
				nextPos = -1;
			} else if( board.getTopmostEmptyCell( nextPos) == -1 ) {
				System.out.println( "column is occupied. try to do another move!" );
				nextPos = -1;
			}
		}
	
		return nextPos;
	}
	
	// temporary solution \o/
	public int getUserInput() {

		System.out.print( name + ": " );
		String input = new String( "" );

		try {
			input = stdin.readLine();
		} catch( IOException ioe ) {
			System.out.println( "hmm! something went wrong.." );
		}
		
		int i = -1;
		
		try {
			i = Integer.parseInt( input );
		} catch ( NumberFormatException nfe ) {
			i = -1;
		}
		
		return i;
	}

	public boolean isHuman()
	{
		return true;
	}	
}