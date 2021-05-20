package mod;

import java.util.ArrayList;

/**
 * This class, Database, creates a database with all the hacks. 
 * It contains: 
 *   - list of all log messages (premade and inputed by user)
 *   - stores number of different machine IDs
 *   - stores number of different messages
 */
public class Database {
	
	private LogMessage _lm;
	private ArrayList<LogMessage> _list;
	private ArrayList<String> _mID;
	private ArrayList<String> _mess;
	private int _numID; //number of unique IDs
	private int _numMes; //number of messages per ID
	
	
	public int getNumID() {  return _numID; }
	public int getNumMes() {  return _numMes; }
	
	public ArrayList<LogMessage> getGenList() { return _list; }
	public ArrayList<String> getIDList() { return _mID; }
	public ArrayList<String> getMessList() { return _mess; }
	
	public Database() {
		_lm = new LogMessage("", "");
		_list = new ArrayList<LogMessage>();
		_mID = new ArrayList<String>();
		_mess = new ArrayList<String>();
		allMachineID(_list);
		generatedList();
		countAgain();
	}
	
	//creates arraylist with 10 pre-generated hacking accounts
	public void generatedList() {
		_list.add(new LogMessage("CLIENT1", "Disk malfunction"));
		_list.add(new LogMessage("WebServer", "Personal files hacked"));
		_list.add(new LogMessage("server", "Error in software"));
		_list.add(new LogMessage("Router", "Data stolen"));
		_list.add(new LogMessage("WebServer", "Malware virus"));
		_list.add(new LogMessage("WebServer", "Disk malfunction"));
		_list.add(new LogMessage("server", "Personal files hacked"));
		_list.add(new LogMessage("CLIENT1", "Data stolen"));
		_list.add(new LogMessage("server", "Malware virus"));
		_list.add(new LogMessage("Router", "Error in software"));
	}
	
	//prints all the hacks in the database
	public String printList() {
		String s = "All Databases Hacked (MachineID:Message)\n\n";
		for(int i = 0; i < _list.size(); i++) {
			s += _list.get(i).getMachineId() + ":";
			s += _list.get(i).getDescription() + "\n";
		}
		return s;
	}
	
	//adds a hack to the list
	public void addLog(String id, String m){
		_list.add(new LogMessage(id, m));
	}
	
	//search by machineID
	public String searchID(ArrayList<LogMessage> arr, String id) {
		String lst = "MachineID searched: " + id + "\n\n";
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i).getMachineId().equalsIgnoreCase(id)) {
				lst += arr.get(i).getMachineId() + ":"
						+ arr.get(i).getDescription() + "\n";
			}
		}
		return lst;
	}
	
	//search by keyword
	public String searchKeyword(ArrayList<LogMessage> arr, String word) {
		String lst = "Word searched: " + word + "\n\n";
		
		for(int i = 0; i < arr.size(); i++) {
			if(_lm.containsWord(arr.get(i).getMachineId(),
					arr.get(i).getDescription(), word)) {
				lst += arr.get(i).getMachineId() + ":"
					+ arr.get(i).getDescription() + "\n";
			}
		}
		if(lst.equals("Word searched: " + word + "\n\n")) {
			lst += "Sorry, the word you searched is \n"
					+ "not part of the hacking database.";
		}
		return lst;
	}
	
	//remove by machineID
	public void removeLogID(ArrayList<LogMessage> arr, String id) {
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i).getMachineId().equalsIgnoreCase(id)) {
				String s = arr.get(i).getDescription();
				if(!arr.contains(s)) {
					for(int c = 0; c < _mess.size(); c++) {
						if(s.equalsIgnoreCase(_mess.get(c))) {
							_mess.remove(c);
							c--;
						}
					}
				}
			}
		}
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i).getMachineId().equalsIgnoreCase(id)) {
				arr.remove(i);
				i--;
			}
		}
		for(int i = 0; i < _mID.size(); i++) {
			if(_mID.get(i).equalsIgnoreCase(id)) {
				_mID.remove(i);
				i--;
			}
		}
	}
	
	//remove by keyword
	public void removeLogKeyword(ArrayList<LogMessage> arr, String word){
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i).getDescription().equalsIgnoreCase(word)) {
				String s = arr.get(i).getMachineId();
				if(!arr.contains(s)) {
					for(int c = 0; c < _mID.size(); c++) {
						if(s.equalsIgnoreCase(_mID.get(c))) {
							_mID.remove(c);
							c--;
						}
					}
				}
			}
		}
		
		for(int i = 0; i < arr.size(); i++) {
			if(_lm.containsWord(arr.get(i).getMachineId(), 
					arr.get(i).getDescription(), word)) {
				arr.remove(i);
				i--;
			}
		}
		for(int i = 0; i < _mess.size(); i++) {
			if(_lm.containsWord("", _mess.get(i), word)) {
				_mess.remove(i);
				i--;
			}
		}
	}
	
	//makes list of all machineIDs
	public String allMachineID(ArrayList<LogMessage> arr) {
		String s = "";
		int cnt = 0;
		for(int i = 0; i < arr.size(); i++) {
			if(!_mID.contains(arr.get(i).getMachineId())) {
				_mID.add(arr.get(i).getMachineId());
				cnt++;
				s += cnt + ") " + arr.get(i).getMachineId() +"\n";
			}
		}
		return s;
	}
	
	//makes list of all messages
	public String allMessages(ArrayList<LogMessage> arr) {
		String s = "";
		int cnt = 0;
		for(int i = 0; i < arr.size(); i++) {
			if(!_mess.contains(arr.get(i).getDescription())) {
				_mess.add(arr.get(i).getDescription());
				cnt++;
				s += cnt + ") " + arr.get(i).getDescription() +"\n";
			}
		}
		return s;
	}
	//gets a random message
	public String getRandomMes() {
		allMessages(_list);
		return _mess.get((int)(Math.random()*_mess.size()));
		
	}
	
	//gets number of messages for 1 machine id
	public int getCount(String id) {
		int cnt = 0;
		for(int c = 0; c < _list.size(); c++) {
			if(id.equalsIgnoreCase(_list.get(c).getMachineId())) {
				cnt++;
			}
		}
		return cnt;  
	}
	
	//makes list of the number of messages for ALL the machine ids
	//*uses getCount(String id) method above
	public String countAgain() {
		allMachineID(_list);
		String s = "Number of messages per each machineID\n\n";
		for(int i = 0; i < _mID.size(); i++) {
			s += _mID.get(i) + " --> " + getCount(_mID.get(i)) + "\n";
		}
		return s;
	}
	
	//checks if the userID exists
	public boolean checkID(String s) {
		for(int i = 0; i < _mID.size(); i++) {
			if(_mID.get(i).equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}
}


		


