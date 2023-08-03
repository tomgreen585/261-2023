import java.util.Scanner;

public class AndN implements RCondN{
	
	private CndtnN fN;
	private CndtnN sN;

	public AndN(){
		this.fN = null;
		this.sN = null;
	}
	
	//PARSE METHOD
	//COND  ::= "and" "(" COND "," COND ")" | "or" "(" COND "," COND ")" | "not" "(" COND ")"  | 
	//RELOP "(" EXPR "," EXPR ")
	public RCondN parse(Scanner scan) {
		if(!Parser.checkFor("and", scan)){ Parser.fail("And fail", scan);} // AND
		if (!Parser.checkFor(Parser.OPENPAREN, scan)) {Parser.fail("OPENPAREN fail", scan);} // OPENPAREN
		fN = new CndtnN(); fN.parse(scan); //first cndtnN
		if (!Parser.checkFor(Parser.COM, scan)) {Parser.fail("COM fail, expected", scan);} // COMMA
		sN = new CndtnN(); sN.parse(scan); //second cndtnN
		if (!Parser.checkFor(Parser.CLOSEPAREN, scan)) {Parser.fail("CLOSEPAREN fail", scan);} // CLOSEPAREN
		
		return this;
	}
	
	public String toString() {
		return "and(" + fN + ", " + sN + ")";
	}	

	public boolean evaluate(Robot r) {
		boolean fValue = fN.evaluate(r); 
		boolean sValue = sN.evaluate(r); 
		return fValue && sValue;
	}
}