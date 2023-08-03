import java.util.ArrayList;
import java.util.Scanner;


public class BlockN implements ProgramNode{
	
	public ArrayList <ProgramNode> block = new ArrayList <ProgramNode> (); 
	private ProgramNode n;

	public BlockN(){
		
	}

	//PARSE METHOD
	//BLOCK ::= "{" STMT+ "}"
	public ProgramNode parse(Scanner scan) {
		if(!Parser.checkFor(Parser.OPENBRACE, scan)){Parser.fail("BlockN Fail", scan);} //OPENBRACE
		if(!scan.hasNext()){Parser.fail("NO EXPRESSION", scan);}
		if(scan.hasNext()){
			n = new StmtN(); n.parse(scan); block.add(n); 
		}
		
		//if(!scan.hasNext(Parser.CLOSEBRACE)){ 
		while(!scan.hasNext(Parser.CLOSEBRACE)){ 
			if(scan.hasNext()){
				n = new StmtN(); n.parse(scan); 
				block.add(n); 
			}
		}

		//if (scan.hasNext(Parser.CLOSEPAREN)) {Parser.checkFor(Parser.CLOSEPAREN, scan);} // CLOSEPAREN
		//else{Parser.fail("CLOSEBRACE Fail", scan);}
		if (!Parser.checkFor(Parser.CLOSEBRACE, scan)) {Parser.fail("CLOSEBRACE Fail", scan);} //CLOSEBRACE
	
		return this;
	}
	
	public String toString() {
		String result = "{";
		
		for (int i = 0; i < block.size(); i++) {
			result += block.get(i).toString();
			
			if (i < block.size() - 1) {result += " ";}
		}
		result += "}";
		
		return result;
	}
	
	public void execute(Robot r) {
		//n.execute(r);	
		//if (n != null) {
			//n.execute(r);
		//} else {
			//throw new IllegalStateException("BLOCKN Null");	
		//}
		for(ProgramNode rN : block){rN.execute(r);} //executed element(s)
	}
}