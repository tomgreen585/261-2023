import java.util.Scanner;

public class DivdN implements RExprN{
	
	private ExprsnN fN;
	private ExprsnN sN;
	
	public DivdN(){
		this.fN = null;
		this.sN = null;
	}

	//PARSE METHOD
	//EXPR   ::= NUM | SENS | OP "(" EXPR "," EXPR ")" 
	//OP    ::= "add" | "sub" | "mul" | "div"
	public RExprN parse(Scanner scan) {
		
		if (!Parser.checkFor("div", scan)) {Parser.fail("DivideN F", scan);} //DIV
		if (!Parser.checkFor(Parser.OPENPAREN, scan)) {Parser.fail("Openparen fail", scan);} //OPENPAREN
		fN = new ExprsnN(); fN.parse(scan); //check first expr

		if (!Parser.checkFor(Parser.COM, scan)) {Parser.fail("Comma fail", scan);} //COM
	
		sN = new ExprsnN(); sN.parse(scan); //check second expr
		
		if (!Parser.checkFor(Parser.CLOSEPAREN, scan)) {Parser.fail("CloseParen fail", scan);} //CLOSEPAREN
		
		return this;
	}
	
	public String toString() {
		return "div(" + fN + ", " + sN + ")"; 
	}

	public int evaluate(Robot r) {
		int fValue = fN.evaluate(r);
        int sValue = sN.evaluate(r);
        return fValue / sValue;
	}
}