import java.util.Scanner;

public class NotN implements RCondN{

	CndtnN n;

	public NotN(){
		this.n = null;
	}
	
	//PARSING METHOD
	//COND  ::= "and" "(" COND "," COND ")" | "or" "(" COND "," COND ")" 
	// | "not" "(" COND ")"  | RELOP "(" EXPR "," EXPR ")
	public RCondN parse(Scanner scan) {
	
		if(!Parser.checkFor("not", scan)){Parser.fail("NotNode Fail", scan);} //NOT
		if (!Parser.checkFor(Parser.OPENPAREN, scan)) {Parser.fail("OpenParen Fail", scan);} //OPENPAREN
		
		n = new CndtnN(); n.parse(scan); 

		if (!Parser.checkFor(Parser.CLOSEPAREN, scan)) {Parser.fail("CloseParen Fail", scan);} //CLOSEPAREN
		
		return this;
	}
	
	public String toString() {
		return "not(" + n + ")";
		
	}

	public boolean evaluate(Robot r) {
		return !n.evaluate(r);
	}

}