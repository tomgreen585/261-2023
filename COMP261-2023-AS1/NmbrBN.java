import java.util.Scanner;

public class NmbrBN implements RExprN{

	public NmbrBN(){

	}

	//PARSING METHOD
	//EXPR   ::= NUM | SENS | OP "(" EXPR "," EXPR ")"  
	//SENS  ::= "fuelLeft" | "oppLR" | "oppFB" | "numBarrels" | "barrelLR" | "barrelFB" | "wallDist"
	public RExprN parse(Scanner scan) {
		if(!Parser.checkFor("numBarrels", scan)){Parser.fail("NmbrBN Fail", scan);}
		return this;
	}
	
	public String toString() {
		return "numBarrels";
	}

	public int evaluate(Robot r) {
		int numBarrels = r.numBarrels();
		return numBarrels;
	}
}