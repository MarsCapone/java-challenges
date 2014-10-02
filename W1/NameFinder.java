import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class NameFinder {
	
	public static NameFinder nf = new NameFinder();
	
	public static void main(String args[]) throws Exception {
		while (true) {
			System.out.println();
			System.out.println("Press Ctrl-C to quit.");
			System.out.println();
			
			nf.printInfo();
			//nf.quit(); // not working for some reason.
		}
	}
	
	public String getEmailId() {
		/**
		 * Asks for and returns email ID. 
		 */
		System.out.println("Please enter email ID:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String id = null;
		try {
			id = br.readLine();
		} catch (IOException e) {
			System.err.println("INPUT ERROR");
		}
		return id;
	}
	
	public String getURL(String ID) {
		/**
		 * puts together the url to go to.
		 * @param ID 	the email ID of the person
		 * @return 		the url to go to. 
		 * 
		 * somewhat pointless method
		 */
		
		String emailID = new String(ID);
		String baseURL = new String("http://www.ecs.soton.ac.uk/people/");
		
		return baseURL + emailID;
	}
	
	public Boolean testID(String ID) { 
		/**
		 * Uses regex to check is the ID might be real
		 * Don't want to load a webpage is there is no chance that the email id is real
		 */
		Boolean test = false;
		if ( ID.matches("[a-z]{2,5}") || ID.matches("[a-z]{2,5}?[0-9]{1,4}?[a-z]{1}?[0-9]{0,2}?") || ID.matches("[a-z]{2,5}?[0-9]{0,5}?")) {
			test = true;
		}
		return test;
	}
	
	public String getHTML(String url) throws Exception {
		/** 
		 * Gets the vcard div from  the HTML source from a page.
		 * @param 	url		url to get source from
		 * @return 			vcard div line
		 */
		URL userURL = new URL(url);
		BufferedReader br = new BufferedReader(new InputStreamReader(userURL.openStream()));
		
		String line;
		String vcard = new String("");
		
		while ((line = br.readLine()) != null) {
			
			if (line.indexOf("vcard") > 0) {
				vcard = line;			
			}
		}
		return vcard;
	}
	
	public Map processVCARD(String vcard) {
		/** 
		 * Processes vcard div from getHTML().
		 * @param vcard		the output of getHTML
		 * @return			dictionary about the person.
		 * 
		 * This is quite messy, and i'm sure there is a better way.
		 * But it works.
		 */
		Map<String, String> attributes = new HashMap<String, String>();
		
		// find name
		int nameIndex = vcard.indexOf("'>")+2; //find name
		int facultyIndex = vcard.indexOf("organization-unit")+19; //find faculty
		int roleIndex = vcard.indexOf("role")+6; //find role
		int extIndex = vcard.indexOf("Extension")+20; //find extension
		int phoneIndex = vcard.indexOf("telephone\">")+11; //find phone
		
		if (nameIndex > 0) { // only do it if they actually exist
			attributes.put("Name", vcard.substring(nameIndex, vcard.indexOf("<", nameIndex)));
			attributes.put("Faculty", vcard.substring(facultyIndex, vcard.indexOf("<", facultyIndex)));
			attributes.put("Role", vcard.substring(roleIndex, vcard.indexOf("<", roleIndex)));
			attributes.put("Extension", vcard.substring(extIndex, vcard.indexOf("<", extIndex)));
			attributes.put("Telephone", vcard.substring(phoneIndex, vcard.indexOf("<", phoneIndex)));
		}

		return attributes;
	}
	
	public void printInfo() throws Exception {
		try {
					
			String ID = nf.getEmailId(); 	// get the ID
			Boolean test = nf.testID(ID);	// find out if ID is maybe real
			Map attr;
			String underline = "=";
		
		
			if (test) { // id might be real
				String url = nf.getURL(ID);
				String vcard = nf.getHTML(url);
			
				attr = nf.processVCARD(vcard);
				//System.out.println(ID);
				System.out.println();
				Iterator it = attr.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry p = (Map.Entry)it.next();
					System.out.println(p.getKey() + " : " + p.getValue());
					it.remove();
				}
			} else {
				System.out.println("I'm sorry, that email ID doesn't look real. Please try another.");
			}	
			System.out.println();
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("This page does not exist.");
		}
	}
	
	public void quit() {
		/**
		 * For use when in a loop. Quits the program is 'y' or 'Y' is given.
		 * else, continues.
		 */
		System.out.println("Do you want to quit? y/n");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in;
		in = null;
		try {
			in = br.readLine();
		} catch (IOException e) {
			System.err.println("INPUT ERROR");
		}
		if (in.equals('y') || in.equals('Y')) {
			System.exit(0);
		}
	}
		
}