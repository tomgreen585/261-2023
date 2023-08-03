import java.util.ArrayList;
import java.util.Scanner;


public class IfN implements ProgramNode {
	
	private ArrayList<RCondN> condN = new ArrayList<RCondN>();
	private ArrayList<ProgramNode> blockN = new ArrayList<ProgramNode>();
	private CndtnN COND;
	private ProgramNode ACT;
	private ProgramNode BLOCK;
	private ProgramNode ELSE;

	public IfN(){
		this.COND = null;
		this.ACT = null;
		this.BLOCK = null;
		this.ELSE = null;
	}
	
	//PARSING METHOD
	//IF    ::= "if" "(" COND ")" BLOCK
	public ProgramNode parse(Scanner scan) {		
	
		//IF
		if (!Parser.checkFor("if", scan)){Parser.fail("IfNode Fail", scan);} //IF
		if (!Parser.checkFor(Parser.OPENPAREN, scan)) {Parser.fail("OpenParen Fail", scan);} //OPENPAREN
		if (scan.hasNext(Parser.CLOSEPAREN)){Parser.fail("NO EXPRESSION", scan);} //CLOSEPAREN FAIL
		
		if (scan.hasNext(Parser.ACT)) {
			ACT = new ActN(); ACT.parse(scan); 	
		} 
		
		//if (!scan.hasNext(Parser.COND)) {
		//if (scan.hasNext(Parser.CLOSEPAREN)){Parser.fail("NO EXPRESSION", scan);} //CLOSEPAREN FAIL
		if (scan.hasNext(Parser.COND)) {
			COND = new CndtnN(); condN.add((COND).parse(scan));
			
			if(scan.hasNext(Parser.CLOSEPAREN)){Parser.checkFor(Parser.CLOSEPAREN, scan);} //CLOSEPAREN
			//Parser.fail("CloseParen Fail", scan);
		} 
		
		//scan.next(); //added
		//checks for )
		//if (scan.hasNext(Parser.CLOSEPAREN)) {
		//	Parser.checkFor(Parser.CLOSEPAREN, scan);
		//} 
		//if (!Parser.checkFor(Parser.CLOSEPAREN, scan)) {
		//	Parser.fail("CloseParen Fail", scan);
		//} 

		BLOCK = new BlockN();
		blockN.add(BLOCK.parse(scan));
		
		//ELIF
		while(scan.hasNext("elif")){
			if(!Parser.checkFor("elif", scan)){Parser.fail("elif Fail", scan);} //ELIF
			if(!Parser.checkFor(Parser.OPENPAREN, scan)){Parser.fail("OpenParen Fail", scan);}
			//if (scan.hasNext(Parser.CLOSEPAREN)){Parser.fail("NO EXPRESSION", scan);} //CLOSEPAREN FAIL
			
			
			
			if (scan.hasNext(Parser.COND)) { //COND
				COND = new CndtnN(); condN.add((COND).parse(scan));
			}	
			
			if(!Parser.checkFor(Parser.CLOSEPAREN, scan)){Parser.fail("CloseParen Fail", scan);} //CLOSEPAREN	
			//if(scan.hasNext(Parser.CLOSEPAREN)){Parser.checkFor(Parser.CLOSEPAREN, scan);} //CLOSEPAREN

			
			BLOCK = new BlockN();
			blockN.add(BLOCK.parse(scan));
		}

		//ELSE	
		if (scan.hasNext("else")) {
			if (!Parser.checkFor("else", scan)) {Parser.fail("ElseNode Fail", scan);} //ELSE
			
			//ELSE = new CndtnN(); ELSE.parse(scan);
			ELSE = new BlockN(); ELSE.parse(scan);
		}		
		return this;
	}

	public String toString() {
		String result = "";
		
		// IF
		if (!condN.isEmpty()) {result += condN.get(0) + ")";}
		if (!blockN.isEmpty()) {result += blockN.get(0);}
		
		// ELIF
		for (int i = 1; i < condN.size(); i++) {
			result += "elif(" + condN.get(i) + ")";
			result += blockN.get(i);
		}
		
		// ELSE
		if (ELSE != null) {result += "else" + ELSE;}
		
		return result;
	}
	
	
	public void execute(Robot r) {
		int size = condN.size();
		
		for (int i = 0; i < size; i++) {
			if (condN.get(i).evaluate(r)) {
				blockN.get(i).execute(r);
				return; 
			}
		}
		if (ELSE != null) {ELSE.execute(r);} 
	}
}




	