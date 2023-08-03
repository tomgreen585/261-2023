import java.util.Scanner;

public class SbtrctN implements RExprN{
	
	private ExprsnN fN;
	private ExprsnN sN;
	
	public SbtrctN(){
		this.fN = null;
		this.sN = null;
	}

	//PARSING METHOD
	//OP    ::= "add" | "sub" | "mul" | "div"
	public RExprN parse(Scanner scan) {

		if (!Parser.checkFor("sub", scan)) {Parser.fail("SbtrctN Fail", scan);} //SUB
		if (!Parser.checkFor(Parser.OPENPAREN, scan)) {Parser.fail("OpenParen Fail", scan);} //OPENPAREN
		
		fN = new ExprsnN(); fN.parse(scan); //check first expr
	
		if (!Parser.checkFor(Parser.COM, scan)) {Parser.fail("Comma Fail", scan);} //COM

		sN = new ExprsnN(); sN.parse(scan); //check second expr
	
		if (!Parser.checkFor(Parser.CLOSEPAREN, scan)) {Parser.fail("CloseParen Fail", scan);} //CLOSEPAREN
		
		return this;
	}
	
	public String toString() {
		return "sub(" + fN + ", " + sN + ")";
	}

	public int evaluate(Robot r) {
		int fValue = fN.evaluate(r);
        int sValue = sN.evaluate(r);
        return fValue - sValue;
	}
}