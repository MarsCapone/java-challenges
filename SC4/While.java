/* Implement a BareBones While loop.
 * 	
 *		while <var> not 0 do; 
 * 		<statements> 
 * 		end;
 * 
 * take index of start of while loop, end of while loop, 
 * and comparison value.
 */

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class While extends Instruction {
	private List<ArrayList<String>> while_loop; // contains just the while loop, a subset of the main file
	private int start_index;
	private int end_index;
	private String varVal;
	public static HashMap<String,Integer> THE_VARIABLES = new Program().THE_VARIABLES;
	
	
	/* While Constructor
	 * @param program 	List of ArrayLists containing program to run.
	 * @param start_index 	start index of the while loop.
	 * @param end_index 	end index of the while loop. 
	 * @param varVal 		String of variable name of variable to compare against 0.
	 */
	public While(List<ArrayList<String>> program, int start_index, int end_index, String varVal) {
		String supposed_while_start = program.get(start_index).get(0);
		String supposed_while_end = program.get(end_index).get(0);
		if (supposed_while_start.equals("while") && supposed_while_end.equals("end")) {
			while_loop = program.subList(start_index, end_index+1);
			this.start_index = start_index;
			this.end_index = end_index;
			this.varVal = varVal.toLowerCase();
			//this.variables = variables;
		}
	}
	
	/* chaeck what sublist the program is actually fetching. */
	public String getSubList() {
		return while_loop.toString();
	}
	
	/* A recursive solution to the while loop problem.
	 * feed the while loop back into the main program.
	 */
	public void doLoop() {
		printVar();
		int loopCount = 1;
		do {
			System.out.printf("This is loop number: %d\n", loopCount); loopCount++;
			List<ArrayList<String>> new_while_loop = while_loop.subList(1, while_loop.size());
			new Program().lineReadLoop(new_while_loop);
		} while (THE_VARIABLES.get(varVal.toLowerCase()) != 0);
	} 
}