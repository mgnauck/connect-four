
// Simple game of Connect 4 (markus@unik.de)
// Features a particularly nice console output and AI with alpha-beta pruning
// Initialize players and playing field size in main method

public class Connect4 {

	private Board	board;
	private Player  player1;
	private Player  player2;

    public Connect4( int boardRows, int boardCols, int winCount, Player p1, Player p2 ) {

        player1 = p1;
        player2 = p2;

    	board = new Board(boardRows, boardCols, winCount);

	    player1.setBoardRef(board);
	    player2.setBoardRef(board);
    }

    public boolean movePlayer(Token t) {

    	if(t == player1.getToken())
    		return board.insert(player1.getNextMove(), t);

    	return board.insert(player2.getNextMove(), t);
    }

	public void play() {

		boolean	endOfGame	= false;
		boolean	draw		= false;
        Token	turn		= Token.x;		// x begins

        System.out.println( "\nConnect4" );
		System.out.println( "-> boardsize is " + board.getRows() + "x" + board.getCols() + ", you need " + board.getWinCount() + " in a series to win" );
        System.out.println( "-> " + player1.getName() + " is " + ( player1.isHuman() ? "human" : "artificial" ) );
        System.out.println( "-> " + player2.getName() + " is " + ( player2.isHuman() ? "human" : "artificial" ) );
		System.out.println( "-> crtl+c quits the game immediately" );

		board.print();

		while(!endOfGame) {

            movePlayer( turn );
			board.print();

			endOfGame = draw = board.gameCompletedWithDraw();
            endOfGame = board.gameCompletedWithWin( turn );

            if(!endOfGame)
            	turn = turn.invert();
		}

		if(draw)
			System.out.println("its a draw!");
		else {
			if(player1.getToken()==turn)
				System.out.println(player1.getName() + " has won!");
			else
				System.out.println(player2.getName() + " has won!");
		}
	}

	public static void main( String args[] ) {

        Player		x		= new Computer("x", Token.x, new AlphaBetaAI(Token.x));
        Player		o		= new Computer("o", Token.o, new AlphaBetaAI(Token.o));
        Connect4	game	= new Connect4( 6, 7, 4, x, o );

        game.play();
	}
}
