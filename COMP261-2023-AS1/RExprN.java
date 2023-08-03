import java.util.Scanner;

public interface RExprN {	
	
	public int evaluate(Robot robot);
	
	public RExprN parse (Scanner s);
	
	public String toString();
}