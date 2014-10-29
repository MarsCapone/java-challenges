import java.util.HashMap;

public class Statement {
	public HashMap<String,Integer> variables = new HashMap<String,Integer>();
	
	public void addVar(String key, Integer val) {
		variables.put(key, val);
	}
	
	public HashMap getVar() {
		return variables;
	}
	
	public void printVar() {
		System.out.println(getVar().toString());
	}
}