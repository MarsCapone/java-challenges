import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;

/* Class to implement printing hashmap neatly */
public class Printing {
	public static HashMap<String,Integer> THE_VARIABLES = new Program().THE_VARIABLES;
	
	
	public void neatPrint() {
		Set variableSet = THE_VARIABLES.entrySet();
		Iterator it = variableSet.iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			System.out.printf("Variable: %s | Value: %d %n", pairs.getKey(), pairs.getValue());
		}
	}
	
	public void neatPrint(ArrayList<String> line) {
		System.out.println("Line: "+line.toString());
		Set variableSet = THE_VARIABLES.entrySet();
		Iterator it = variableSet.iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			System.out.printf("Variable: %s | Value: %d %n", pairs.getKey(), pairs.getValue());
		}
	}
}