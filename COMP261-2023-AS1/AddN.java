import java.util.Scanner;

public class AddN implements RExprN{

	private ExprsnN fN;
	private ExprsnN sN;

	public AddN(){
		this.fN = null;
		this.sN = null;
	}

	//PARSE METHOD
	//EXPR   ::= NUM | SENS | OP "(" EXPR "," EXPR ")" 
	//OP    ::= "add" | "sub" | "mul" | "div"
	public RExprN parse(Scanner scan) {
		if (!Parser.checkFor("add", scan)) {Parser.fail("Add fail", scan);} // ADD
		if (!Parser.checkFor(Parser.OPENPAREN, scan)) {Parser.fail("OPENPAREN fail", scan);} // OPENPAREN
		fN = new ExprsnN(); fN.parse(scan); //check first expr
		if (!Parser.checkFor(Parser.COM, scan)) {Parser.fail("COM fail", scan);} // COMMA
		sN = new ExprsnN(); sN.parse(scan); //check second expr
		if (!Parser.checkFor(Parser.CLOSEPAREN, scan)) {Parser.fail("CLOSEPAREN fail", scan);} // CLOSEPAREN
		
		return this;
	}
	
	public String toString() {	
		return "and(" + fN + "," + sN + ")";
	}

	public int evaluate(Robot r) {
		int fValue = fN.evaluate(r);
        int sValue = sN.evaluate(r);
        
		return fValue + sValue;
	}		
}