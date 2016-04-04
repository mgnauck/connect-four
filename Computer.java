
public class Computer extends Player {

	private AI ai;	

	public Computer(String n, Token t, AI concreteAI) {
		super(n, t);
		ai = concreteAI;		
	}
		
	public int getNextMove() {
		System.out.println(name + " is thinking about his next move..");
		return ai.calcNextMove(board);
	}
	
	public boolean isHuman() {
		return false;
	}	
}