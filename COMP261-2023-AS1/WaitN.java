import java.util.Scanner;

public class WaitN implements ProgramNode{
	
	private ExprsnN n;

	public WaitN(){

	}

	//PARSER METHOD
	//ACT   ::= "move" [ "(" EXPR ")" ] | "turnL" | "turnR" | "turnAround" | 
    // "shieldOn" | "shieldOff" | "takeFuel" | "wait" [ "(" EXPR ")" ]
	public ProgramNode parse(Scanner scan) {

		if (!Parser.checkFor("wait", scan)) {Parser.fail("WaitNode Fail", scan);} // WAIT
		if (scan.hasNext(Parser.OPENPAREN)) {Parser.checkFor(Parser.OPENPAREN, scan);} // OPENPAREN	
		if (scan.hasNext(Parser.SNSR) || scan.hasNext(Parser.NUMPAT) || scan.hasNext(Parser.OP)) {
			n = new ExprsnN(); n.parse(scan); 
		}
		
		if (scan.hasNext(Parser.CLOSEPAREN)) {Parser.checkFor(Parser.CLOSEPAREN, scan);} //CLOSEPAREN	
		
		return this;
	}
	
	
	public String toString() {
   	 	String result = "wait";
    	if (n != null) {
        	result += " (" + n + ")";
    	}
    	return result;
	}


	public void execute(Robot r) {
		if(n == null){
			r.idleWait();
		} else {
			int timesToMove = n.evaluate(r); 
            for(int i = 0; i < timesToMove; i++){ 
				r.idleWait();
            } 
		}
	}
}