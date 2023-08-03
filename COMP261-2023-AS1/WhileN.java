import java.util.Scanner;

public class WhileN implements ProgramNode {
	
	public BlockN aNode;
	public CndtnN bNode;

	public WhileN(){
		this.aNode = null;
		this.bNode = null;
	}

	//PARSING METHOD
	//WHILE ::= "while" "(" COND ")" BLOCK
	public ProgramNode parse(Scanner scan) {
		
		if (!Parser.checkFor("while", scan)) {Parser.fail("WhileNode Fail", scan);} //WHILE
		
		
		if (scan.hasNext(Parser.OPENPAREN)) {
			Parser.checkFor(Parser.OPENPAREN, scan);
			if (scan.hasNext(Parser.COND)) {
				bNode = new CndtnN(); bNode.parse(scan); 
			} 
			
			else {Parser.fail("Condition Fail", scan);}
			
			if(!scan.hasNext(Parser.CLOSEPAREN)){Parser.fail("WhileNode Fail", scan);} //CLOSEPAREN FAIL
			if (scan.hasNext(Parser.CLOSEPAREN)) {Parser.checkFor(Parser.CLOSEPAREN, scan);} //CLOSEPAREN	
			
			aNode = new BlockN(); aNode.parse(scan);
		}
		return this;
	}
	
	public String toString(){
		String result = "while (" + bNode.toString() + ")";
    	result += aNode.toString() + "}";
    	return result;
	}

	public void execute(Robot r) {
		if (bNode.evaluate(r)) {
			aNode.execute(r);
			execute(r); 
		}
	}	
}