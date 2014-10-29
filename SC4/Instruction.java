import java.util.ArrayList;
import java.util.HashMap;

/* Contains methods for most Bare Bones instructions. */
public class Instruction extends Statement {
	
	public static HashMap<String,Integer> THE_VARIABLES = new Program().THE_VARIABLES;
	
	public void init(ArrayList<String> line) {
		String variable = (String)line.get(1);
		Integer value = new Integer(line.get(2));
		THE_VARIABLES.put(variable.toLowerCase(), value);
	}
	
	public void incr(ArrayList<String> line) {
		String key = (String)line.get(1);
		Integer oldValue = THE_VARIABLES.get(key);
		Integer newValue = oldValue+1;
		THE_VARIABLES.put(key, newValue);
	}
	
	public void decr(ArrayList<String> line) {
		String key = (String)line.get(1);
		Integer oldValue = new Integer(THE_VARIABLES.get(key));
		Integer newValue = oldValue-1;
		THE_VARIABLES.put(key, newValue);
	}
	
	public void clear(ArrayList<String> line) {
		String key = (String)line.get(1);
		THE_VARIABLES.put(key, 0);
	}
	
	public void copy(ArrayList<String> line) {
		String key1 = (String)line.get(1);
		String key2 = (String)line.get(2);
		Integer val1 = new Integer(THE_VARIABLES.get(key1));
		THE_VARIABLES.put(key2, val1);
	}
}