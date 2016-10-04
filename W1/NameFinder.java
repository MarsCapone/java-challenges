import java.io.*;
import java.net.*;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class NameFinder {

	public static NameFinder nf = new NameFinder();

	public static void main(String args[]) throws Exception {
		while (true) {
			System.out.println();
			System.out.println("Press Ctrl-C to quit.");
			System.out.println();

			nf.printInfo();
		}
	}

	public String Reader(String message) {
		/* A buffered reader in a method */
		System.out.println(message);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String result = null;
		try {
			result = br.readLine();
		} catch (IOException ioe) {
			System.err.println("INPUT ERROR"+ioe);
		}
		return result;
	}


	public Boolean testID(String ID) {
		/**
		 * Uses regex to check is the ID might be real
		 * Don't want to load a webpage is there is no chance that the email id is real
		 */
		Boolean test = false;
		if ( ID.matches("[a-z]{2,5}?[0-9]{1,4}?[a-z]{1}?[0-9]{0,2}?") || ID.matches("[a-z]{2,5}?[0-9]{0,5}?")) {
			test = true;
		}
		return test;
	}

	public String getHTML(String url) throws Exception {
		/**
		 * Gets the HTML source from a page.
		 * @param 	url		url to get source from
		 * @return 			html source
		 */
		URL userURL = new URL(url);
		BufferedReader br = new BufferedReader(new InputStreamReader(userURL.openStream()));

		String line;
		String html = new String("");

		while ((line = br.readLine()) != null) {
			html = html + line;
		}
		return html;
	}

	public Map processHTML(String html) {
		/**
		 * Processes vcard div from getHTML().
		 * @param html		the output of getHTML
		 * @return			dictionary about the person
		 *
		 * Changed to use regex because it looks neater
		 * And it's probably more efficient
		 */

		Map<String, String> attributes = new LinkedHashMap<String, String>();

		// First grab the name
		/*Matcher name = Pattern.compile("<meta content=\"(.*?)\".*?>").matcher(html);
		while (name.find()) {
			String t = name.group(1);
			attributes.put("Name", t);
		}*/

		// Now grab other attributes
		String[] headers = {"organization-unit", "email", "role", "organization-name", "country-name",   "telephone", "url", "name"};
		String[] headerNames = {"Faculty", "Email", "Role", "University", "Country", "Telephone", "Website", "Name"};
		for (int i = 0; i<headers.length; i++) {
			Matcher p = Pattern.compile("<(a|h1) .*? ((class|property)=[\'\"]"+headers[i]+"[\'\"]).*?>(.*?)</(a|h1)>").matcher(html);

			while (p.find()) {
				String t = p.group(4);
				attributes.put(headerNames[i], t);
			}
		}
		return attributes;
	}

	public void printInfo() throws Exception {
		/**
		 * Neatly prints everything.
		 */
		String ID = nf.Reader("Enter email ID: "); 	// get the ID
		Boolean test = nf.testID(ID);	// find out if ID is maybe real
		Map attr;

		if (test) { // id might be real
			String html = nf.getHTML("http://www.ecs.soton.ac.uk/people/"+ID);

			attr = nf.processHTML(html);
			if (attr.get("Name").equals(null) || attr.get("Name").equals("")) {
				System.out.println();
				System.out.println("This page does not exist");
			} else {
				System.out.println();
				//System.out.println(attr); //for testing uncomment this line and comment out below.
				Iterator it = attr.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry p = (Map.Entry)it.next();
					System.out.printf("%12s : %s %n",p.getKey(), p.getValue());
					it.remove();
				}
			}
		} else {
			System.out.println("I'm sorry, that email ID doesn't look real. Please try another.");
		}
		System.out.println();
	}
}
