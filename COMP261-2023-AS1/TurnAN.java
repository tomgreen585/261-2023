import java.util.Scanner;

public class TurnAN implements ProgramNode {

	public TurnAN(){
		
	}

	//ACT   ::= "move" [ "(" EXPR ")" ] | "turnL" | "turnR" | "turnAround" | 
    // "shieldOn" | "shieldOff" | "takeFuel" | "wait" [ "(" EXPR ")" ]
	public ProgramNode parse(Scanner scan) {
		
		if (!Parser.checkFor("turnAround", scan)) {Parser.fail("TurnAroundNode Fail", scan);} //TA
		
		return this;
	}
	
	public String toString() {
		return "turnAround;";
	}

	public void execute(Robot r) {
		r.turnAround();
	}
	
}