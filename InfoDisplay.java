package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import cont.JOP;
import mod.Database;

/**
 * This class, InfoDisplay, controls all the GUI and button actions.
 * It creates the frame, panel, buttons, and image(s).
 * @author diyaa
 *
 */
public class InfoDisplay implements ActionListener {
	
	private JFrame _fra;
	private JPanel _pan;
	private JButton _add, _search, _remove, _ran, _img;
	private JTextPane _jtp1, _jtp2;
	private Database _db;
	
	public InfoDisplay() {
		intro();
		_db = new Database();
		makeImg();
		makeButton();
		makeJTextArea();
		makePanel();
		makeFrame();
	}
	
	public void intro() {
		JOP.msg("Welcome to the Hacking Database.\n\n"
				+"Here, you can find a record of all the\n"
				+ "software hacked in Miami, FL, starting\n"
				+ "on January 1, 2021. Add a hack of your own"
				+ "\nto the database as well.");
	
	}
	
	//instantiates the JFrame
	public void makeFrame() {
		_fra = new JFrame("Hacking Database");
		_fra.setLayout(new BorderLayout());
		_fra.setResizable(false);
		_fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_fra.setSize(600, 600);
		_fra.setLocationRelativeTo(null);
		_fra.add(_pan);
		validateFrame();
	}
	
	//instantiates JPanel and adds buttons/images to it
	private void makePanel() {
		_pan = new JPanel();
		_pan.setLayout(new FlowLayout());
		_pan.setSize(600, 600);
		_pan.add(_img);
		_pan.add(_add);
		_pan.add(_search);
		_pan.add(_remove);
		_pan.add(_ran);
		_pan.add(_jtp1);
		_pan.add(_jtp2);
		_pan.setBackground(Color.DARK_GRAY);
		validatePanel();
	}
	
	//creates image
	private void makeImg() {
		_img = new JButton(new ImageIcon(getClass().getResource("hack.jpg")));
		_img.setPreferredSize(new Dimension(600, 150));
		_img.addActionListener(this);
	}
	
	//instantiates and sets the color of the buttons
	private void makeButton() {
		_add = new JButton("ADD MachineID and Message");
		_search = new JButton("SEARCH by MachineID or Keyword");
		_remove = new JButton("REMOVE by MachineID or Keyword");
		_ran = new JButton("GENERATE Random Description");
		
		_add.setBackground(Color.orange);
		_search.setBackground(Color.orange);
		_remove.setBackground(Color.orange);
		_ran.setBackground(Color.orange);
		
		_add.addActionListener(this);
		_search.addActionListener(this);
		_remove.addActionListener(this);
		_ran.addActionListener(this);
	}
	
	//creates the text areas to display the hack list and 
	//different machineIDs/messages
	private void makeJTextArea() {
		_jtp1 = new JTextPane();
		_jtp1.setText(_db.printList());
		_jtp1.setBounds(200, 200, 350, 350);
		_jtp1.setEditable(false);
		_jtp1.setBackground(Color.orange);
		
		_jtp2 = new JTextPane();
		_jtp2.setText(_db.countAgain());
		_jtp2.setBounds(200, 200, 350, 350);
		_jtp2.setEditable(false);
		_jtp2.setBackground(Color.orange);
	}
	
	private void validatePanel() {
		_pan.repaint();
		_pan.validate();
		_pan.setVisible(true);
	}
	
	private void validateFrame() {
		_fra.repaint();
		_fra.validate();
		_fra.setVisible(true);
	}

	/**
	 * Controls all the actions of the buttons.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == _img) {
			JOP.msg("INSTRUCTIONS:" +
					"\n\n- ADD a hack to the pre-existing list (MachineID:Message)"
					+ "\n- SEARCH by MachineID or any keyword"
					+ "\n- REMOVE by MachineID or any keyword"
					+ "\n- GENERATE random description using existing MachineID"
					+ "\n- View number of descriptions per MachineID"
					+ "\n\nHave fun hacking!");
		}
		if(e.getSource() == _add) {
			String id = JOP.in("Enter the MachineID that was hacked.");
			String m = JOP.in("Enter what was hacked (the message).");
			if(id != null || m != null) {
				_db.addLog(id, m);
				_jtp1.setText(_db.printList());
				_jtp2.setText(_db.countAgain());
				JOP.msg("Congratulations, you have successfully entered"
						+ "\na hack in the Database!"
						+ "\n(MachineID that was hacked and the message)");
			}
			else {
				JOP.msg("Sorry, try again.");
			}
		}
		if(e.getSource() == _search) {
			String s = JOP.in("Would you like to search by\n"
					+ "1) Machine ID\n"
					+ "2) Any Keyword\n\n"
					+ "Enter the number (1 or 2).");
			if(s.equals("1")) {
				String word = JOP.in("Enter a machineID to search:");
				JOP.msg(_db.searchID(_db.getGenList(), word));
			}
			if(s.equals("2")) {
				String word = JOP.in("Enter a word to search:");
				JOP.msg(_db.searchKeyword(_db.getGenList(), word));
			}
		}
		if(e.getSource() == _remove) {
			String s = JOP.in("Would you like to remove by\n"
					+ "1) Machine ID\n"
					+ "2) Any Keyword\n\n"
					+ "Enter the number (1 or 2).");
			if(s.equals("1")) {
				String word = JOP.in("Enter a machineID to remove:");
			_db.removeLogID(_db.getGenList(), word);
			_jtp1.setText(_db.printList());
			_jtp2.setText(_db.countAgain());
			}
			if(s.equals("2")) {
				String word = JOP.in("Enter a keyword to remove:");
				_db.removeLogKeyword(_db.getGenList(), word);
				_jtp1.setText(_db.printList());
				_jtp2.setText(_db.countAgain());
			}
	
		}
		if(e.getSource() == _ran) {
			String id = JOP.in("Type an existing machineID from the list,\n"
					+ "and we'll generate a random description for you:\n");
			if(!_db.checkID(id)) {
				JOP.msg("Sorry, this machineID doesn't exist yet.");
			}
			else {
				_db.addLog(id, _db.getRandomMes());
				_jtp1.setText(_db.printList());
				_jtp2.setText(_db.countAgain());
			}
		}
	}
	
}
