import java.util.Scanner;

public class FLftN implements RExprN{

	public FLftN(){

	}

	//PARSE METHOD
	//SENS  ::= "fuelLeft" | "oppLR" | "oppFB" | "numBarrels" |
	//"barrelLR" | "barrelFB" | "wallDist"
	public RExprN parse(Scanner scan) {
		if(!Parser.checkFor("fuelLeft", scan)){Parser.fail("FuelLeftNode Fail", scan);}
		return this;
	}
	
	public String toString() {
		return "fuelLeft";
	}

	public int evaluate(Robot r) {
		return r.getFuel();
	}	
}