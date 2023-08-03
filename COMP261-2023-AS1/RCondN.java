import java.util.Scanner;

public interface RCondN {

	public boolean evaluate(Robot robot);

	public RCondN parse(Scanner scan);
	
	public String toString();
		
}