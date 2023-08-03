import java.util.Scanner;

public class MoveN implements ProgramNode{

	private ExprsnN expN;
	private OprtnN opN;

	public MoveN(){
		this.expN = null;
		this.opN = null;
	}

	//PARSE METHOD
	//ACT   ::= "move" [ "(" EXPR ")" ] | "turnL" | "turnR" | "turnAround" | 
	public ProgramNode parse(Scanner scan) {

		if (!Parser.checkFor("move", scan)) {Parser.fail("MoveNode Fail", scan);} //MOVE
		if (scan.hasNext(Parser.OPENPAREN)) {Parser.checkFor(Parser.OPENPAREN, scan);} //OPENPAREN

		//if(!scan.hasNext()){
		//	Parser.fail("NO EXPRESSION", scan);
		//}

		//if(!Parser.checkFor(Parser.NUMPAT,scan) || !Parser.checkFor(Parser.SENSOR,scan)) {
		//	Parser.fail("Empty" , scan);
		//}
		
		if(scan.hasNext(Parser.CLOSEPAREN)){Parser.fail("No expression", scan);} //CLOSEPAREN
		else if (scan.hasNext(Parser.NUMPAT) || scan.hasNext(Parser.SNSR)) { //EXPR
			expN = new ExprsnN(); expN.parse(scan); 
		}
		else if (scan.hasNext(Parser.OP)) { //OP
			opN = new OprtnN(); opN.parse(scan);
		} 
		
		if (scan.hasNext(Parser.CLOSEPAREN)) {Parser.checkFor(Parser.CLOSEPAREN, scan);} //CLOSEPAREN
		//else{
		//	if(expN == null){
		//		Parser.fail("Empty" , scan);
		//	}
		//}
		
		return this;
	}
	
	public String toString(){
		String result = "move";
		if (expN != null) {result += "(" + expN + ")";} 
		else if (opN != null) {result += "(" + opN + ")";}
		
		return result;
	}

	public void execute(Robot r) {
		if(expN != null){
			int moveExp = expN.evaluate(r);
            for(int i = 0; i < moveExp; i++){r.move();} 
        }
		if (opN != null){
			int moveOp = opN.evaluate(r); 
            for(int i = 0; i < moveOp-1; i++){r.move();}
		} 
		if (expN == null){r.move();}
	}
}