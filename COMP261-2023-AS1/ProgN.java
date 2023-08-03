import java.util.ArrayList;
import java.util.Scanner;

public class ProgN implements ProgramNode{	
	
	private ArrayList <ProgramNode> state = new ArrayList <ProgramNode> ();

	public ProgN(){
		
	}

	//PARSING METHOD
	//PROG  ::= [ STMT ]*
	public ProgramNode parse(Scanner scan) {
		while (scan.hasNext()) { 
			StmtN n = new StmtN();
			state.add(n.parse(scan)); 
		}
		return this;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder(); 
		for (ProgramNode pN : state) { 
			result.append(pN.toString()).append(";");
		}
		return result.toString(); 
	}
	
	public void execute(Robot r) {
		for(ProgramNode STMT : state){ 
			STMT.execute(r); 
		}	
	}
}