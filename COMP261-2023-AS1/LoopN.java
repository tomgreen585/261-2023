import java.util.Scanner;


public class LoopN implements ProgramNode {

	ProgramNode n;

	public LoopN(){
		this.n = null;
	}

	//PARSE METHOD
	//LOOP  ::= "loop" BLOCK
	public ProgramNode parse(Scanner scan) {
		
		if(!Parser.checkFor("loop", scan)){Parser.fail("LoopNode Fail", scan);}	//LOOP

		n = new BlockN(); n.parse(scan); 
		return n;
	}
	
	public String toString() {
		return "loop" + n;
	}

	public void execute(Robot robot) {
		while(true){
			n.execute(robot);
		}
	}
}
