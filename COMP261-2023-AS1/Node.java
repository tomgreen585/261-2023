public abstract class Node implements ProgramNode {
	
	public Node() {
		
	}
	
	public abstract String toString();
	
	public abstract void execute(Robot r);
	
}