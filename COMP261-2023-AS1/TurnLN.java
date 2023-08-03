import java.util.Scanner;

public class TurnLN implements ProgramNode {
	
	public TurnLN(){
		
	}

	//PARSING METHOD
	//ACT   ::= "move" [ "(" EXPR ")" ] | "turnL" | "turnR" | "turnAround" | 
    // "shieldOn" | "shieldOff" | "takeFuel" | "wait" [ "(" EXPR ")" ]
	public ProgramNode parse(Scanner scan) {

		if (!Parser.checkFor("turnL", scan)) {Parser.fail("TurnLNode Fail", scan);}
		
		return this;
	}
	
	public String toString(){
		return "turnL";
	}

	public void execute(Robot r) {
		r.turnLeft();
	}
}