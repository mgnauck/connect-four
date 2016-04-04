
public class Board {

	private Matrix 	boardMatrix;
	private int		winCount;   
    private int[]	columnCounter; 
                                     
	public Board(int rows, int cols, int neededWinCount) {
		boardMatrix		= new Matrix( rows, cols );
		winCount		= neededWinCount;
        columnCounter	= new int[cols];
        
        // reset columnCounter
        for(int i=0; i<cols; i++)
        	columnCounter[i] = 0;
	}

	public int getRows() {
		return boardMatrix.getRows();
	}

	public int getCols() {
		return boardMatrix.getCols();
	}
	
	public int getWinCount() {
		return winCount;
	}
	
	// insert token (empty tokens possible!)
	// returns true token got placed, false if column is fully occupied
	public boolean insert( int col, Token token ) {
		
		// check
		if( ( col < 0 ) || ( col >= getCols() ) )
			return false;
				
		if(!isColumnFull(col)) {		
			boardMatrix.set(getTopmostEmptyCell(col), col, token);
            columnCounter[col]++;
			return true;
		}
		
		return false;		
	}
	
	// get topmost empty cell in column
	// returns -1 if all cells in column are occupied
	public int getTopmostEmptyCell( int col )
	{
        return columnCounter[col];
	}
    
    public boolean isColumnFull(int col)
    {
        return (getTopmostEmptyCell(col) >= getRows());
    }
	
	// check if game is finished with win situation
	public boolean gameCompletedWithWin( Token winningToken ) {
		
		for( int j=0; j<boardMatrix.getRows() - winCount + 1; j++ ) {
			for( int i=0; i<boardMatrix.getCols() - winCount + 1; i++ ) {
			
				int diagLRWin = 0;		// diagonal upper left to lower right
				int diagRLWin = 0;		// diagonal upper right to lower left
						
				for( int y=0; y<winCount; y++ ) {
				
					int rowWin = 0;
					int colWin = 0;
				
					for( int x=0; x<winCount; x++ ) {
						rowWin += ( boardMatrix.get( j+y, i+x ) == winningToken ) ? 1 : 0;
						colWin += ( boardMatrix.get( j+x, i+y ) == winningToken ) ? 1 : 0;
					}
					
					// check for row/column win situation				
					if( ( rowWin == winCount ) || ( colWin == winCount ) )
						return true;
						
					diagLRWin += ( boardMatrix.get( j+y, i+y ) == winningToken ) ? 1 : 0;
					diagRLWin += ( boardMatrix.get( j+y, i+winCount-y-1 ) == winningToken ) ? 1 : 0;
				}
				
				// check for diagonal win situation
				if( ( diagLRWin == winCount ) || ( diagRLWin == winCount ) )
					return true;
			}
		}
		
		return false;
	}
	
	// check if its a draw. assumes that there is no win situation before!!
	public boolean gameCompletedWithDraw() {

		for(int j=0; j<getCols(); j++)
			if(getTopmostEmptyCell(j) > -1)
				return false;
				
		return true;
	}
	
	public void print() {
		boardMatrix.print();
	}
	
    public Object clone()
    {
        Board clone			= new Board(1, 1, 1);
        
        clone.winCount		= winCount;
        clone.columnCounter = new int [getCols()];
        
        // copy column counter
        for (int c=0; c<getCols(); c++)
            clone.columnCounter[c] = columnCounter[c];
        
        // copy matrix
        clone.boardMatrix = (Matrix)boardMatrix.clone();
       
        return clone;
    }
}