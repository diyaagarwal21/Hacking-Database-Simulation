package cont;

import javax.swing.JOptionPane;

/**
 * The JOP class manages all the JOptionPane commands for the program.
 */
public class JOP {

	//displays a message
	public static void msg(Object msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
	//displays a message and saves the user's input
	public static String in(Object msg){
		return JOptionPane.showInputDialog(msg);
	}
}
