import java.util.Scanner;

public class WallDN implements RExprN{

	public WallDN(){
		
	}

	public RExprN parse(Scanner scan) {
		if(!Parser.checkFor("wallDist", scan)){Parser.fail("WallDistNode Fail", scan);} //WD
		return this;
	}
	
	public String toString(){
		return "wallDist";
	}

	public int evaluate(Robot r) {
		return r.getDistanceToWall();
	}
}