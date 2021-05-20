package mod;

/**
 * This class, LogMessage, creates a message in the hacking database.
 * It consists of a machine ID and a description.
 * The class also checks if the word inputed by the user is part of the database.
 * @author diyaa
 *
 */
public class LogMessage {

	private String machineId;
	private String description;
	
	public String getMachineId() { return machineId; }
	public String getDescription() { return description; }
	
	public LogMessage(String Id, String desc) { 
		machineId = Id;
		description = desc;
	}
	
	/**
	 * Checks if @param machineID or @param desc 
	 * contain @param keyword
	 */
	public boolean containsWord(String machineID, String desc, String keyword)
	{
		String kw = keyword.toLowerCase();
		String m = machineID.toLowerCase();
		String d = desc.toLowerCase();
		
		//case 1: word is the description
		if(m.equals(kw) || d.equals(kw)) {
			return true;
		}
		//case 2: beginning of description, has space after
		if(m.indexOf(kw + " ") == 0
				|| d.indexOf(kw + " ") == 0) {
			return true;
		}
		//case3: is in the middle of string (surrounded by spaces)
		if(m.indexOf(" " + kw + " ") != -1
				|| d.indexOf(" " + kw + " ") != -1){
			return true;
		}

		//case4: end of description, has space before
		if(m.length() > kw.length()) {
			int end = m.length() - kw.length() - 1;
			if(m.substring(end).equals(" " + kw)){
				return true;
			}
		}
		if(d.length() > kw.length()) {
			int end = d.length() - kw.length() - 1;
			if(d.substring(end).equals(" " + kw)){
				return true;
			}
		}
		return false;
	}

}
