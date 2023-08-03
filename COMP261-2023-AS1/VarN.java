import java.util.Scanner;

public class VarN implements RExprN{

    private String n = "";
    private ExprsnN expr;
    
    public VarN(String n){
    	this.n = n;
		this.expr = null;
    }

	//PARSER METHOD
	public RExprN parse(Scanner scan) {
		String varName = scan.next();
	
		if (ExprsnN.varMap.containsKey(varName)) {
			VarN var = new VarN(varName);
	
			if (scan.hasNext(Parser.OPENPAREN)) {
				scan.next(); 
				var.expr = new ExprsnN(); var.expr.parse(scan);
				if(!Parser.checkFor(Parser.CLOSEPAREN, scan)){Parser.fail("CloseParen Fail", scan);} //CLOSEPAREN	
			}
			return var;
		} else {
			Parser.fail("VarN Fail", scan);
			return null;
		}
	}
	
	public String toString() {
		if (expr != null) {
			return n + "(" + expr + ")";
		} else {
			return n;
		}
	}
	

	public int evaluate(Robot r) {
		if (expr == null) {throw new IllegalStateException("Var undeclared");
		}
		return expr.evaluate(r);
	}
	

}