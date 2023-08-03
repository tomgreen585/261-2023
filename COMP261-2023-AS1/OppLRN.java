import java.util.Scanner;

public class OppLRN implements RExprN{
	
	RExprN opN;
	
	public OppLRN(){
		this.opN = null;
	}

	//PARSING METHOD
	//SENS  ::= "fuelLeft" | "oppLR" | "oppFB" | "numBarrels" |
	//"barrelLR" | "barrelFB" | "wallDist"
	public RExprN parse(Scanner scan) {
		
		if (!Parser.checkFor("oppLR", scan)) {Parser.fail("OpponentLR Fail", scan);} //OLFTRGT
		
		return this;
	}
	
	public String toString() {
		return "OpponentLR";
	}

	public int evaluate(Robot r) {
		int OppLeftRight = r.getOpponentLR();
		return OppLeftRight;
	}

}