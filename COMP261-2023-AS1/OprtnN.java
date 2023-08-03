import java.util.Scanner;

public class OprtnN implements RExprN{

	RExprN n;

	public OprtnN(){
		this.n = null;
	}

	//PARSING METHOD
	//OP    ::= "add" | "sub" | "mul" | "div"
	public RExprN parse(Scanner scan) {
		if (scan.hasNext("add")){n = new AddN();} 
		else if (scan.hasNext("sub")){n = new SbtrctN();} 
		else if (scan.hasNext("mul")){n = new TimesN();} 
		else if (scan.hasNext("div")){n = new DivdN();}
		else {Parser.fail("OP Fail", scan);} 
		n.parse(scan); 
		return n;
	}
	
	public String toString() {
		return n.toString(); 
	}

	public int evaluate(Robot r) {
		if(n == null) {throw new IllegalArgumentException("OprtnN Null");}
		return n.evaluate(r);
	}
}