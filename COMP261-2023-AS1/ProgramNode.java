import java.util.Scanner;
/**
 * Interface for all nodes that can be executed,
 * including the top level program node
 */

interface ProgramNode {
    public void execute(Robot robot);

    public ProgramNode parse(Scanner s);

    public String toString();
}
