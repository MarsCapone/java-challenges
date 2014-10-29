import java.util.*;
import java.io.*;

public class Program {
	
	//the only place to store variables. all variables go here.
	public static HashMap<String,Integer> THE_VARIABLES = new Variable().variables; 
	
	/* load 	loads the program from the file
	 * @param file 	the file to load
	 * @return ArrayList containing each Arrays for each line
	 */
	public ArrayList<ArrayList<String>> load(String file) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<ArrayList<String>> allStatements = new ArrayList<ArrayList<String>>();
		String line;
		while ((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line, " ;=,.:@", false);
			ArrayList<String> statement = new ArrayList<String>();
			while (st.hasMoreTokens()) {
				statement.add(st.nextToken());
			}
			allStatements.add(statement);
		}
		return allStatements;
	}
	/* run the things that make the program actually go. */
	public void run() throws Exception {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter File: ");
		String bbFile = in.nextLine();
		ArrayList<ArrayList<String>> file = load(bbFile);
		
		lineReadLoop(file);
	}
	
	/* reads a List of ArrayLists and process the result. 
	 * operate on each line in turn. change variables according to 
	 * HashMap THE_VARIABLES.
	 * 
	 * @param file 		List of ArrayLists of Strings. 
	 * 					Each ArrayList contains a line of the program.
	 * 					Each String of the ArrayList is a key word in the line.
	 */
	public void lineReadLoop(List<ArrayList<String>> inFile) {
		Instruction inst = new Instruction();
		int while_start=0; int while_end=0;
		List<ArrayList<String>> file = new ArrayList<ArrayList<String>>(inFile);
		List<ArrayList<String>> whileFile = new ArrayList<ArrayList<String>>(inFile);
		
		for (int i = 0; i<file.size(); i++) {
			ArrayList<String> line = file.get(i);
			switch (line.get(0)) { // switch statement for every operation
				case "init": inst.init(line);
					break;
				case "incr": inst.incr(line);
					break;
				case "decr": inst.decr(line);
					break;
				case "clear": inst.clear(line);
					break;
				case "copy": inst.copy(line);
					break;
				case "end": 
					break;
				case "while": 
					// first find the numbers of the lines that while and end are on.
					for (int j = 0; j < whileFile.size(); j++) {
						ArrayList<String> AL = whileFile.get(j);
						if (AL.get(0).equals("while")) {
							while_start = j; // and set to variables
						} else if (AL.get(0).equals("end")) {
							while_end = j;
						}
					}
					
					//create a new while loop to go through the while statements and implement the While class.
					While whileLoop = new While(file, while_start, while_end, file.get(while_start).get(1)); 
					whileLoop.doLoop(); //run the loop.
				
					for (int k = while_start; k<while_end; k++) { 
						// remove the while loop afterwards, so as not to run it's contents later.
						whileFile.remove(k);
					}
					file = whileFile; //change the file to one with the while statements removed.
					break;
				default: System.out.printf("UNSUPPORTED 	: 	%s \n", line); // want to see which line is unsupported
					break;
			}
			System.out.println(THE_VARIABLES); //see the variables and which have changed
			System.out.println();
		}
	}
	
	public static void main(String args[]) throws Exception {
		System.out.println("=============================\n\n"); //so we can see where different instances start and end.
		new Program().run();
	}
}