import java.util.Scanner;

public class NmbrN implements RExprN {

	private int num;
	
	public NmbrN(){

	}

	//PARSING METHOD
	// NUM   ::= "-?[1-9][0-9]*|0"
	public RExprN parse(Scanner s) {
		num = Parser.requireInt (Parser.NUMPAT, "NUMN Fail", s); //return int
        return this;
	}
	
	public String toString() {
		return String.valueOf(num);
	}

	public int evaluate(Robot r) {
		return num;
	}
}