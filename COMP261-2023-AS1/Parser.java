import java.util.*;
import java.util.regex.*;

/**
 * See assignment handout for the grammar.
 * You need to implement the parse(..) method and all the rest of the parser.
 * There are several methods provided for you:
 * - several utility methods to help with the parsing
 * See also the TestParser class for testing your code.
 */
public class Parser {

    //Grammer
    static Pattern NUMPAT = Pattern.compile("-?\\d+");
	static Pattern OPENPAREN = Pattern.compile("\\(");
	static Pattern CLOSEPAREN = Pattern.compile("\\)");
	static Pattern OPENBRACE = Pattern.compile("\\{");
	static Pattern CLOSEBRACE = Pattern.compile("\\}");
	static Pattern SEMI = Pattern.compile(";");
	static Pattern COM = Pattern.compile(",");	
	
    static Pattern ACT = Pattern.compile("move|takeFuel|turnL|turnR|turnAround|wait|shieldOn|shieldOff");
    static Pattern SNSR = Pattern.compile("fuelLeft|oppLR|oppFB|numBarrels|barrelLR|barrelFB|wallDist");
    static Pattern COND = Pattern.compile("and|or|not|lt|gt|eq"); 
    static Pattern OP = Pattern.compile("add|sub|mul|div");
	static Pattern VAR = Pattern.compile("\\[A-Za-z][A-Za-z0-9]"); 
	

    //----------------------------------------------------------------
    /**
     * The top of the parser, which is handed a scanner containing
     * the text of the program to parse.
     * Returns the parse tree.
     */

    ProgramNode parse(Scanner scan) {
		
        scan.useDelimiter("\\s+|(?=[{}(),;])|(?<=[{}(),;])"); 
        ProgramNode root = parseProgram(scan);
        if(root != null){return root;}
       
        return null;
    }

    static ProgramNode parseProgram(Scanner scan){
        
        ProgramNode n = null;
        if(scan != null){n = new ProgN().parse(scan);}
        
        return n;
    }

    //----------------------------------------------------------------
    // utility methods for the parser
    // - fail(..) reports a failure and throws exception
    // - require(..) consumes and returns the next token as long as it matches the pattern
    // - requireInt(..) consumes and returns the next token as an int as long as it matches the pattern
    // - checkFor(..) peeks at the next token and only consumes it if it matches the pattern

    /**
     * Report a failure in the parser.
     */
    static void fail(String message, Scanner s) {
        String msg = message + "\n   @ ...";
        for (int i = 0; i < 5 && s.hasNext(); i++) {
            msg += " " + s.next();
        }
        throw new ParserFailureException(msg + "...");
    }

    /**
     * Requires that the next token matches a pattern if it matches, it consumes
     * and returns the token, if not, it throws an exception with an error
     * message
     */
    static String require(String p, String message, Scanner s) {
        if (s.hasNext(p)) {return s.next();}
        fail(message, s);
        return null;
    }

    static String require(Pattern p, String message, Scanner s) {
        if (s.hasNext(p)) {return s.next();}
        fail(message, s);
        return null;
    }

    /**
     * Requires that the next token matches a pattern (which should only match a
     * number) if it matches, it consumes and returns the token as an integer
     * if not, it throws an exception with an error message
     */
    static int requireInt(String p, String message, Scanner s) {
        if (s.hasNext(p) && s.hasNextInt()) {return s.nextInt();}
        fail(message, s);
        return -1;
    }

    static int requireInt(Pattern p, String message, Scanner s) {
        if (s.hasNext(p) && s.hasNextInt()) {return s.nextInt();}
        fail(message, s);
        return -1;
    }

    /**
     * Checks whether the next token in the scanner matches the specified
     * pattern, if so, consumes the token and return true. Otherwise returns
     * false without consuming anything.
     */
    static boolean checkFor(String p, Scanner s) {
        if (s.hasNext(p)) {s.next(); return true;}
        return false;
    }

    static boolean checkFor(Pattern p, Scanner s) {
        if (s.hasNext(p)) {s.next(); return true;} 
        return false;
    }

}