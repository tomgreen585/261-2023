import java.util.Scanner;

public class TakeFN implements ProgramNode{

	ProgramNode n;

	public TakeFN(){
		this.n = null;
	}
	
	//PARSING METHOD
	//ACT   ::= "move" [ "(" EXPR ")" ] | "turnL" | "turnR" | "turnAround" | 
    // "shieldOn" | "shieldOff" | "takeFuel" | "wait" [ "(" EXPR ")" ]
	public ProgramNode parse(Scanner scan) {
		
		if(!Parser.checkFor("takeFuel", scan)){Parser.fail("TakeFuelNode Fail", scan);} //TFUEL
		return this;
	}
	
	public String toString(){
		return "takeFuel";
	}

	public void execute(Robot r) {
		r.takeFuel();
	}
}