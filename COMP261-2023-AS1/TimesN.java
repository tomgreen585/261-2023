import java.util.Scanner;

public class TimesN implements RExprN{
	
	private ExprsnN fN;
	private ExprsnN sN;

	public TimesN(){
		this.fN = null;
		this.sN = null;
	}

	//PARSE METHOD
	//OP    ::= "add" | "sub" | "mul" | "div"
	public RExprN parse(Scanner scan) {
		
		if (!Parser.checkFor("mul", scan)) {Parser.fail("Times Fail", scan);} // MUL
		if (!Parser.checkFor(Parser.OPENPAREN, scan)) {Parser.fail("OpenParen Fail", scan);} // OPENPAREN
		
		fN = new ExprsnN(); fN.parse(scan); //check first expr
	
		if (!Parser.checkFor(Parser.COM, scan)) {Parser.fail("Comma Fail", scan);}
		
		sN = new ExprsnN(); sN.parse(scan);
		
		if (!Parser.checkFor(Parser.CLOSEPAREN, scan)) {Parser.fail("CloseParen Fail", scan);}
		
		return this;
	}
	
	public String toString() {
		return "mul(" + fN + ", " +sN + ")";
	}

	public int evaluate(Robot r) {
		
		int fValue = fN.evaluate(r);
        int sValue = sN.evaluate(r);
        return fValue * sValue;
	}

}