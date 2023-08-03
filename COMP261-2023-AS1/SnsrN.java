import java.util.Scanner;

public class SnsrN implements RExprN{
	
	RExprN n;

	public SnsrN(){
		this.n = null;
	}

	//PARSING METHOD
	//SENS  ::= "fuelLeft" | "oppLR" | "oppFB" | "numBarrels" |
	//"barrelLR" | "barrelFB" | "wallDist"
	public RExprN parse(Scanner scan) {
		
		if (scan.hasNext("fuelLeft")){n = new FLftN();} // FLEFT
		else if (scan.hasNext("oppLR")){n = new OppLRN();} // OLFTRGT
		else if (scan.hasNext("oppFB")){n = new OppFBN();} // OFRTBCK
		else if (scan.hasNext("numBarrels")){n = new NmbrBN();} // NUMB
		else if (scan.hasNext("barrelLR")){n = new BLRN();} // BLFTRGT
		else if (scan.hasNext("barrelFB")){n = new BFBN();} // BFRTBCK
		else if (scan.hasNext("wallDist")){n = new WallDN();} // WD
		else {Parser.fail("OP Fail", scan);}
		if (n != null) { 
			n.parse(scan); 
		}
		return n;
	}

	public String toString(){
		if (n != null) {return n.toString();}
		return "";
	}

	public int evaluate(Robot robot){
		if (n != null) {return n.evaluate(robot);}
       	return 0;
	}
}