import java.util.Scanner;

public class GrtrN implements RCondN{

	public RExprN fN;
	public RExprN sN;

	public GrtrN(){
		this.fN = null;
		this.sN = null;
	}

	//PARSE METHOD
	//RELOP ::= "lt" | "gt" | "eq"
	public RCondN parse(Scanner scan) {
		if(!Parser.checkFor("gt", scan)){Parser.fail("GreaterThanNode Fail", scan);} //GT
		if(!Parser.checkFor(Parser.OPENPAREN, scan)){Parser.fail("OpenParen Fail", scan);} //OPENPAREN

		fN = new ExprsnN(); fN.parse(scan); //check first expr
		
		if (scan.hasNext(Parser.COM)) {Parser.checkFor(Parser.COM, scan);}
		
		sN = new ExprsnN(); sN.parse(scan); //checks second expr
		
		//if (scan.hasNext(Parser.CLOSEPAREN)) {
		//	Parser.checkFor(Parser.CLOSEPAREN, scan);
		//}
		if (!Parser.checkFor(Parser.CLOSEPAREN, scan)) {Parser.fail("CloseParen Fail", scan);} // CLOSEPAREN
		
		return this;
	}

	public String toString(){
		return "gt(" + fN + ", " + sN + ")";
	}

	public boolean evaluate(Robot r) {
		int fValue = fN.evaluate(r);
        int sValue = sN.evaluate(r);
        return fValue > sValue;
	}
}