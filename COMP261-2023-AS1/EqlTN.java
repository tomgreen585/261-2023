import java.util.Scanner;

public class EqlTN implements RCondN{

	public RExprN fN;
	public RExprN sN;

	public EqlTN(){
		this.fN = null;
		this.sN = null;
	}

	//PARSE METHOD
	//COND  ::= "and" "(" COND "," COND ")" | "or" "(" COND "," COND ")" 
	//| "not" "(" COND ")"  | RELOP "(" EXPR "," EXPR ") | 
	//RELOP ::= "lt" | "gt" | "eq"
	public RCondN parse(Scanner scan) {

		if(!Parser.checkFor("eq", scan)){Parser.fail("EqlTN Fail", scan);} //EQ
		if(!Parser.checkFor(Parser.OPENPAREN, scan)){Parser.fail("OpenParen Fail", scan);} //OPENPAREN

		fN = new ExprsnN(); fN.parse(scan); //check first expr
		
		if (!Parser.checkFor(Parser.COM, scan)) {Parser.fail("Comma Fail", scan);} //COM
		
		sN = new ExprsnN(); sN.parse(scan); //check second expr
		
		//if (scan.hasNext(Parser.CLOSEPAREN)) {
		//	Parser.checkFor(Parser.CLOSEPAREN, scan);
		//}
		if(!Parser.checkFor(Parser.CLOSEPAREN, scan)){Parser.fail("OpenParen Fail", scan);} //CLOSEPAREN
		return this;
	}

	
	public String toString(){
		return "eq(" + fN + ", " + sN + ")";
	}

	public boolean evaluate(Robot r) {
		int fValue = fN.evaluate(r);
        int sValue = sN.evaluate(r);
        return fValue == sValue;
	}
}