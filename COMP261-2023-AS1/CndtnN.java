import java.util.Scanner;


public class CndtnN implements RCondN{

	private RCondN n; 
	
	public CndtnN(){
		this.n = null;
	}
	
	//PARSING METHOD
	//COND  ::= "and" "(" COND "," COND ")" | "or" "(" COND "," COND ")" | "not" "(" COND ")"  | RELOP "(" EXPR "," EXPR ")
	//EXPR   ::= NUM | SENS | OP "(" EXPR "," EXPR ")"  
	public RCondN parse(Scanner scan) {
	
		if (scan.hasNext("gt")) {n = new GrtrN();} //GT
		else if (scan.hasNext("lt")) {n = new LessTN();} //LT
		else if (scan.hasNext("eq")) {n = new EqlTN();} //EQ
		else if (scan.hasNext("and")) {n = new AndN();} //AND
		else if (scan.hasNext("or")) {n = new OrN();} //OR
		else if (scan.hasNext("not")) {n = new NotN();} //NOT
		else {Parser.fail("CndtnN Fail" , scan);}
		
		n.parse(scan);
		return n;
	}
	
	public String toString(){
		return n.toString();
	}

	public boolean evaluate(Robot r) {
		if (n == null) {throw new IllegalStateException("CndtnN Null");}
		return n.evaluate(r);
	}
	
}