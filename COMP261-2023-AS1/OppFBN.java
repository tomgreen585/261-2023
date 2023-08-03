import java.util.Scanner;

public class OppFBN implements RExprN{

	public OppFBN(){
		
	}

	//PARSING METHOD
	//SENS  ::= "fuelLeft" | "oppLR" | "oppFB" | "numBarrels" |
	//"barrelLR" | "barrelFB" | "wallDist"
	public RExprN parse(Scanner scan) {
	
		if(!Parser.checkFor("oppFB", scan)){Parser.fail("OpponentFB Fail", scan);} //OFRTBCK
		
		return this;
	}
	
	public String toString(){
		return "OpponentFB";
	}

	public int evaluate(Robot r) {
		int OppFrontBack = r.getOpponentFB();
		return OppFrontBack;
	}
}