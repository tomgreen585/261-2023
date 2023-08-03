import java.util.Scanner;

public class AsgmtN implements ProgramNode{

	private ExprsnN exp;
    private String varN; 
	
	public AsgmtN(){
		this.exp = null;
		this.varN = "";
	}

	public void setE(ExprsnN e) {this.exp = e;}
	public void setN(String n) {this.varN = n;}
	
	public ProgramNode parse(Scanner scan) {
		if (scan.hasNext(Parser.VAR)) {varN = scan.next();} 
		if(scan.hasNext("=")) { 
			scan.next();
		    exp = new ExprsnN(); exp.parse(scan); 
		}
		if(scan.hasNext(Parser.SEMI)) {varN = scan.next();} //SEMI
		
		return this;
	}
	
    public String toString() {
		String str = varN + " = ";
		if (exp != null) {str += exp.toString();}
		str += ";";
		return str;
	}
	
	public void execute(Robot r) {
		int result = exp.evaluate(r); 
		ExprsnN.varMap.put(varN, result); 
	}	
}

	
	
	
