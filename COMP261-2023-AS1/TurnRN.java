import java.util.Scanner;

public class TurnRN implements ProgramNode {
	
	public TurnRN(){
		
	}

	//PARSING METHOD
	//ACT   ::= "move" [ "(" EXPR ")" ] | "turnL" | "turnR" | "turnAround" | 
    // "shieldOn" | "shieldOff" | "takeFuel" | "wait" [ "(" EXPR ")" ]
	public ProgramNode parse(Scanner scan) {
	
		if (!Parser.checkFor("turnR", scan)) {Parser.fail("TurnRNode Fail", scan);} //TR
		
		return this;
	}
	
	public String toString(){
		return "turnR";
	}

	public void execute(Robot r) {
		r.turnRight();
	}

}