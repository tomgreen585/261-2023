import java.util.Scanner;

public class SOnN implements ProgramNode {

	public SOnN(){
		
	}

	//PARSER METHOD
	//ACT   ::= "move" [ "(" EXPR ")" ] | "turnL" | "turnR" | "turnAround" | 
    // "shieldOn" | "shieldOff" | "takeFuel" | "wait" [ "(" EXPR ")" ]
	public ProgramNode parse(Scanner scan) {
		//if shield is not on then return fail
		if(!Parser.checkFor("shieldOn", scan)){Parser.fail("ShieldOn Fail", scan);
		}
		return this;
	}
	
	public String toString(){
		return "shieldOn;";
	}

	public void execute(Robot r) {
		r.setShield(true);
	}
}