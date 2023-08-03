import java.util.Scanner;

public class OrN implements RCondN{
	
    CndtnN fN;
    CndtnN sN;
    
	public OrN(){
		this.fN = null;
		this.sN = null;
	}

	//PARSING METHOD
	//COND  ::= "and" "(" COND "," COND ")" | "or" "(" COND "," COND ")" 
	// | "not" "(" COND ")"  | RELOP "(" EXPR "," EXPR ")
	public RCondN parse(Scanner scan) {
		
		if(!Parser.checkFor("or", scan)){Parser.fail("ORNode Fail", scan);}
		if (!Parser.checkFor(Parser.OPENPAREN, scan)) {Parser.fail("OpenParen Fail", scan);}
		
		fN = new CndtnN(); fN.parse(scan);
		
		if (scan.hasNext(Parser.CLOSEPAREN)) {Parser.checkFor(Parser.CLOSEPAREN, scan);}
		if (scan.hasNext(Parser.COM)) {Parser.checkFor(Parser.COM, scan);}

		sN = new CndtnN(); sN.parse(scan);
		
		if (!Parser.checkFor(Parser.CLOSEPAREN, scan)) {Parser.fail("CloseParen Fail", scan);}
		
		return this;
	}
	
    public String toString() {
    	return fN + " or " + sN;
    }	

	public boolean evaluate(Robot r) {
		boolean first = fN.evaluate(r);
		boolean second = sN.evaluate(r);
		
		return first || second;
	}
	

}