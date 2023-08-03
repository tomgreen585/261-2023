import java.util.Scanner;

public class StmtN implements ProgramNode{

	private ProgramNode n;

	public StmtN(){
		this.n = null;
	}
	
	//PARSING METHOD
	//STMT  ::= ACT ";" | LOOP | IF | WHILE
	public ProgramNode parse(Scanner scan) {

		if (scan.hasNext(Parser.ACT)) {n = new ActN();} 
		else if (scan.hasNext("loop")) { n = new LoopN();} 
		else if (scan.hasNext("if")) {n = new IfN();} 
		else if (scan.hasNext("while")) {n = new WhileN();}
		else if (scan.hasNext(Parser.VAR)) {n = new AsgmtN();}
		else {Parser.fail("STMTNode Fail", scan);}
		
		n.parse(scan);
		
		return this;
	}
	
	public String toString(){
		return n.toString();
	}

	public void execute(Robot r) {
		if (n != null) {
			n.execute(r);
		} else {
			throw new IllegalStateException("ACTN Null");	
		}
	}

}