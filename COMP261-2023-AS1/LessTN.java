import java.util.Scanner;


public class LessTN implements RCondN{
	
	public RExprN fN;
	public RExprN sN;

	public LessTN(){
		this.fN = null;
		this.sN = null;
	}

	//PARSE METHOD
	//RELOP ::= "lt" | "gt" | "eq"
	public RCondN parse(Scanner scan) {
		if(!Parser.checkFor("lt", scan)){Parser.fail("LessThanNode Fail", scan);} //LT
		if(!Parser.checkFor(Parser.OPENPAREN, scan)){Parser.fail("OpenParen Fail", scan);} //OPENPAREN
		fN = new ExprsnN().parse(scan); //first expr
		if (!Parser.checkFor(Parser.COM,scan)){Parser.fail("Comma Fail", scan);} //COM
		sN = new ExprsnN().parse(scan); //second expr
		if (!Parser.checkFor(Parser.CLOSEPAREN, scan)) {Parser.fail("CloseParen Fail", scan);}
		
		return this;
	}
	
	public String toString(){
		return "lt(" + fN + ", " + sN + ")";
	}

	public boolean evaluate(Robot r) {
		int fValue = fN.evaluate(r);
        int sValue = sN.evaluate(r);
        return fValue < sValue;
	}
	

}
