import java.util.Scanner;

public class ActN implements ProgramNode{
	
	private ProgramNode n;

	public ActN(){
		this.n = null;
	}

	//PARSE METHOD 
	//ACT   ::= "move" [ "(" EXPR ")" ] | "turnL" | "turnR" | "turnAround" | 
	//"shieldOn" | "shieldOff" | "takeFuel" | "wait" [ "(" EXPR ")" ]
	public ProgramNode parse(Scanner scan) {
		if (scan.hasNext("move")) { n = new MoveN(); } // "move" 
		else if (scan.hasNext("turnL")) { n = new TurnLN(); } // "turnL" 
		else if (scan.hasNext("turnR")) { n = new TurnRN(); } // "turnR" 
		else if (scan.hasNext("turnAround")) { n = new TurnAN(); } // "turnAround" 
		else if (scan.hasNext("shieldOn")) { n = new SOnN(); } // "shieldOn" 
		else if (scan.hasNext("shieldOff")) { n = new SOffN(); } // "shieldOff" 
		else if (scan.hasNext("takeFuel")) { n = new TakeFN(); } // "takeFuel" 
		else if (scan.hasNext("wait")) { n = new WaitN(); } // "wait" 
		
		else{ Parser.fail("ACTN Fail", scan);} 	
		n.parse(scan); 
		if (!Parser.checkFor(Parser.SEMI, scan)) {Parser.fail("SemiCol Fail", scan);} //SEMI
		
		return n; 	
	}
	
	public String toString(){
		return n.toString();
	}

	public void execute(Robot r) {
		if (n != null) {n.execute(r);} 
		else {throw new IllegalStateException("ACTN Null");}
	}
}