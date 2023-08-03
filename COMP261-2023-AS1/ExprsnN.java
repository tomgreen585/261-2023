import java.util.HashMap;
import java.util.Scanner;

public class ExprsnN implements RExprN {
	
	public static HashMap <String, Integer> varMap = new HashMap <String, Integer> (); 
	private RExprN n;

	public ExprsnN(){
		this.n = null;
	}

	//PARSER METHOD
	//EXPR   ::= NUM | SENS | OP "(" EXPR "," EXPR ")" 
	public RExprN parse(Scanner scan) {
		
		if (scan.hasNext(Parser.NUMPAT)) {n = new NmbrN();}
		else if (scan.hasNext(Parser.SNSR)) {n = new SnsrN();}
		else if (scan.hasNext(Parser.OP)) {n = new OprtnN();}
		else if (scan.hasNext(Parser.VAR)) {
			String varName = scan.next();
            if (!varMap.containsKey(varName)) {
                varMap.put(varName, 0);
            }
            n = new VarN(varName);
		} 
		else {Parser.fail("ExpressionNode Fail", scan);}
		
		n.parse(scan); 
		return n;
	}
	
	public String toString() {
		if (n != null) {return n.toString();} 
		else {return "No expression to display";}
	}

	public int evaluate(Robot r) {
		if (n != null) {
			return n.evaluate(r);
		}
		throw new NullPointerException("ExpressionNode is null");
	}

}