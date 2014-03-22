import javax.swing.*;

public class entry {
    private static void createAndShowGUI() {	
        //Create and set up the window.

    	
        JFrame frame = new MainFrame("Coordinate System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);	//make UI to pack the component and fit
    }

    public static void main(String[] args) {
    	
    	javax.swing.SwingUtilities.invokeLater(new Runnable(){
    		public void run(){
    			createAndShowGUI();
    		}
    	});

     }
}
