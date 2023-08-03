import java.util.Scanner;

public class BLRN implements RExprN{
	
	RExprN n;
	private int distance;

	public BLRN(){
		this.n = null;
	}

	//PARSE METHOD
	//EXPR   ::= NUM | SENS | OP "(" EXPR "," EXPR ")" 
	//SENS  ::= "fuelLeft" | "oppLR" | "oppFB" | "numBarrels" |
	//"barrelLR" | "barrelFB" | "wallDist"
	public RExprN parse(Scanner scan) {
		
		if (!Parser.checkFor("barrelLR", scan)) {Parser.fail("BarrelLR fail", scan);} // BLFTRGT
		if (scan.hasNext(Parser.OPENPAREN)) {Parser.checkFor(Parser.OPENPAREN, scan);} //OPENPAREN
		if (scan.hasNext(Parser.NUMPAT) || scan.hasNext(Parser.SNSR) || scan.hasNext(Parser.OP) || scan.hasNext(Parser.VAR)) { // METHODS
			n = new ExprsnN(); n.parse(scan);
		}
		if (scan.hasNext(Parser.CLOSEPAREN)) {Parser.checkFor(Parser.CLOSEPAREN, scan);} // CLOSEPAREN
	
		return this;
	}
	
	public String toString(){
		String result;
		if (n != null) {result = "barrelLR(" + n + ")";}
		else {result = "barrelLR";}
		return result;
	}

	public int evaluate(Robot r) {
	if (n == null) {distance = r.getClosestBarrelLR();} 
		else {distance = r.getBarrelLR(n.evaluate(r));}
		return distance; 
	}
}