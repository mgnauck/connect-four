
// enum alternative
public class Token {
	
	public final static Token	e	= new Token( 0 );	// empty
	public final static Token	x	= new Token( 1 );	// x token
	public final static Token	o	= new Token( 2 );	// o token
	
	private 			int		value;	
	
	// for quick reading
	public int toInt() {
		return value;
	}
	
	// prevent serializing probs
	private Object readResolve() throws java.io.ObjectStreamException {
        switch(value) {
			case 1:
				return x;
			case 2:
				return o;
		}
		
		return e;
    }
	
	private Token(int val) {
        value = val;
    }
    
    public boolean equals(Object o)
    {
        return this.equals((Token)o);
    }

    public boolean equals(Token t)
    {
        return (this.value == t.value);
    }
    
    public Token invert()
    {
        return (this == x ? o : x);            
    }
}
