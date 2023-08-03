import java.util.Scanner;

public class SOffN implements ProgramNode {

	public SOffN(){
		
	}

	//PARSER METHOD
	//ACT   ::= "move" [ "(" EXPR ")" ] | "turnL" | "turnR" | "turnAround" | 
    // "shieldOn" | "shieldOff" | "takeFuel" | "wait" [ "(" EXPR ")" ]
	public ProgramNode parse(Scanner scan) {
		if(!Parser.checkFor("shieldOff", scan)){Parser.fail("ShieldOffNode Fail", scan);}
		
		return this;
	}
	
	public String toString(){
		return "shieldOff;";
	}
	
	public void execute(Robot r) {
		r.setShield(false);
	}
}