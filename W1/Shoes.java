/**
 * by git Samson Danziger
 * 
 * The Andy Newton method of finding a user
 * 
 * Prompt for username, then enter shoes until the name is printed
 * 
 * Mostly copied and pasted from my original code.
 */

import java.io.*;
import java.net.*;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Shoes {
	public static void main (String args[]) throws Exception {
		System.out.println("Please enter email ID:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String id = null;
		try {
			id = br.readLine();
		} catch (IOException e) {
			System.err.println("INPUT ERROR");
		}
		
		String url = "http://ecs.soton.ac.uk/people/"+id;
		
		URL userURL = new URL(url);
		BufferedReader Br = new BufferedReader(new InputStreamReader(userURL.openStream()));
		
		String line;
		String html = new String("");
		
		while ((line = Br.readLine()) != null) {
			html = html + line;
		}
		
		Matcher name = Pattern.compile("<meta content=\"(.*?)\".*?>").matcher(html);
		Map<String, String> attributes = new LinkedHashMap<String, String>();		
		while (name.find()) {
			String t = name.group(1);
			attributes.put("Name", t);
		}	
		
		Random random = new Random();
		int randomInt = random.nextInt(25);
		
		for (int i = 0; i<randomInt; i++) {
			System.out.println("Please enter 'shoes':");
			BufferedReader cr = new BufferedReader(new InputStreamReader(System.in));
			String jd = null;
			try {
				jd = cr.readLine();
				jd = jd.toLowerCase();
				if (!(jd.equals("shoes"))) {
					System.out.println("You were meant to type shoes. But it doesn't really matter.");
				} 
			} catch (IOException e) {
				System.err.println("INPUT ERROR");
			}
		}
		
		System.out.println(attributes);
	}
}