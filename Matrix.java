
public class Matrix {

	private Token[][]	m;
	private int			rows;
	private int			cols;

	public Matrix( int rs, int cs ) {
		rows	= rs;
		cols	= cs;
		m		= new Token [ rows ][ cols ];
		
		empty();
	}
	
	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}
	
	// set matrix elements to 'empty'
	public void empty() {
		for( int j=0; j<rows; j++ )
			for( int i=0; i<cols; i++ )
				m[ j ][ i ] = Token.e;	
	}

	public void set( int row, int col, Token val ) {
		m[ row ][ col ] = val;
	}

	public Token get( int row, int col ) {
		return m[ row ][ col ];
	}

	public String toString() {
	
        String result = "";
		result += "\n";
	
		for( int j=0; j<cols; j++ )
            result +=  j + "|" ;
	
		result += "\n";		
	
		for( int i=rows-1; i>=0; i-- ) {
			
			for( int j=0; j<cols; j++ ) {
	
				if( get( i, j ) == Token.o )
                    result += "o|";
				if( get( i, j ) == Token.x )
                    result += "x|";
				if( get( i, j ) == Token.e )
                    result += "_|";
			}
			result += "\n";
		}
		
		result += "\n";
        
        return result;
	}
    
    
    public void print()
    {
        System.out.println(toString());
    }
    
    public Object clone()
    {
        Matrix clone = new Matrix(this.rows ,this.cols);
       
        clone.m = new Token[rows][cols];
        for (int r=0; r<this.rows; r++)
            for (int c=0; c<this.cols; c++)
                clone.m[r][c] = this.m[r][c];
                
        return clone;   
    }   
}