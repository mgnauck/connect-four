
public class AlphaBetaAI extends AI {
   
	final static int	DEFAULT_MAX_RECURSION_DEPTH	= 10;
	final static int	UNDEF_BEST_RATING			= 200;
	final static int	UNDEF_WORST_RATING			= -200;
	final static int	BEST_RATING					= 100;
	final static int	WORST_RATING				= -100;
	final static int	DRAW_RATING					= 0;
	final static int	INFINITY_POSITIVE			= UNDEF_BEST_RATING - 1;
	final static int	INFINITY_NEGATIVE			= UNDEF_WORST_RATING + 1;
	
    private int alphaBetaPruning;
    private int noPruning;
    private int maxDepth;
    
    public AlphaBetaAI(Token t, int maxRecursionDepth)
    {
    	super(t);
    	maxDepth = maxRecursionDepth;
    }
    
    public AlphaBetaAI(Token t)
    {
    	super(t);
    	maxDepth = DEFAULT_MAX_RECURSION_DEPTH;
    }

    public int calcNextMove(Board board) {
    	
    	alphaBetaPruning	= 0;
    	noPruning			= 0;
    	
    	// prepare initial state (copy board!)
    	State	initialState	= new State(board, ownToken, INFINITY_NEGATIVE, INFINITY_POSITIVE);

    	// minimax algorithm (treedepthfirstsearchtofindmaxratingofleafs)    	
    	int		col				= maxMove(initialState).column;

    	// stats
    	System.out.println("alpha beta pruning count: " + alphaBetaPruning);
    	System.out.println("no pruning count        : " + noPruning);
 
    	return col;
	}
    
    public Move maxMove(State s)
    {
    	// test for termination at leafs, 4 cases..
    	
  		// 1 draw
    	if(s.board.gameCompletedWithDraw())
    		return new Move(DRAW_RATING, s.column);
    	
    	// 2 Max has won   	
    	if(s.board.gameCompletedWithWin(ownToken))
    		return new Move(BEST_RATING - s.depth, s.column);
		
    	// 3 Min has lost
    	if(s.board.gameCompletedWithWin(ownToken.invert()))
    		return new Move(WORST_RATING + s.depth, s.column);
    		
    	// 4 max recursion depth
    	if(s.depth>=maxDepth)
    		return new Move(DRAW_RATING, s.column);
    	
    	// initial empty move
    	Move bestMove	= new Move(UNDEF_WORST_RATING, -1);
    	Move[] moves	= new Move[s.board.getCols()];

    	// all possible moves
    	for(int i=0; i<s.board.getCols(); i++)
    	{
    		if(!s.board.isColumnFull(i))
    		{
    			// gen new state and do move
    			State newState = new State((Board)s.board.clone(), i, s.depth+1, s.turn.invert(), s.alpha, s.beta);
    			newState.board.insert(i, s.turn);   			
    			
    			// recurse
    			moves[i] = minMove(newState);
				moves[i].column	= i;
    			
    			// find best move (max)
    			if(moves[i].rating > bestMove.rating)
    				bestMove = moves[i];
    			
    			if(bestMove.rating >= s.beta)
    			{
   					alphaBetaPruning++;
    				return bestMove;
    			}

    			// min beta
    			s.alpha = Math.max(s.alpha, bestMove.rating);
    		}
    	}
    	
    	noPruning++;
    	
    	// print    	
    	if(s.depth == 0)
    	{
	    	for(int i=0; i<s.board.getCols(); i++)
	    	{
	    		if(!s.board.isColumnFull(i))
	    		{
	    			if(moves[i].rating==bestMove.rating)
	    				System.out.print("<"+moves[i].rating+"/"+moves[i].column+">");
	    			else
	    				System.out.print("("+moves[i].rating+"/"+moves[i].column+")");
	    		}
	    	}
	    	
	    	System.out.println("");
    	}    	
    	    	
    	return bestMove;
    }
    
    /////////////// die zeile ist hier weil man ständing unbewusst in der falschen methode ändert /////////////////
    
    public Move minMove(State s)
    {
    	// test for termination at leafs, 4 cases..
    	
  		// 1 draw
    	if(s.board.gameCompletedWithDraw())
    		return new Move(DRAW_RATING, s.column);
    	
    	// 2 Max has won   	
    	if(s.board.gameCompletedWithWin(ownToken))
    		return new Move(BEST_RATING - s.depth, s.column);
		
    	// 3 Min has lost
    	if(s.board.gameCompletedWithWin(ownToken.invert()))
    		return new Move(WORST_RATING + s.depth, s.column);
    		
    	// 4 max recursion depth
    	if(s.depth>=maxDepth)
    		return new Move(DRAW_RATING, s.column);
    	
    	// initial empty move
    	Move	bestMove	= new Move(UNDEF_BEST_RATING, -1);
    	Move[]	moves		= new Move[s.board.getCols()];

    	// all possible moves
    	for(int i=0; i<s.board.getCols(); i++)
    	{
    		if(!s.board.isColumnFull(i))
    		{
    			// gen new state and do move
    			State newState = new State((Board)s.board.clone(), i, s.depth+1, s.turn.invert(), s.alpha, s.beta);
    			newState.board.insert(i, s.turn);   			
    			
    			// recurse
    			moves[i] = maxMove(newState);
				moves[i].column	= i;
    			
    			// find best move (min)
    			if(moves[i].rating < bestMove.rating)
    				bestMove = moves[i];
    			
    			if(bestMove.rating <= s.alpha)
    			{
    				alphaBetaPruning++;
    				return bestMove;
    			}
    				
    			// min beta
    			s.beta = Math.min(s.beta, bestMove.rating);
    		}
    	}
    	
    	noPruning++;
    	
    	return bestMove;
    }
    
    public Move max(Move m1, Move m2)
    {
    	if(m1.rating>=m2.rating)
    		return m1;
    	
    	return m2;
    }

    public Move min(Move m1, Move m2)
    {
    	if(m1.rating<=m2.rating)
    		return m1;
    	
    	return m2;
    }
}
