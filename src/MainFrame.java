import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class MainFrame extends JFrame {
	double inputValue1;
	double inputValue2;
	double inputValue3;
	private JTextField inp1;
	private JTextField inp2;
	private JTextField inp3;
	private JLabel labelText1;
	private JLabel labelText2;
	private JLabel labelText3;
	private JLabel answerA1;
	private JLabel answerA2;
	private JLabel answerB1;
	private JLabel answerB2;
	private JLabel answerC1;
	private JLabel answerC2;
	private JButton computeButton;
	public MainFrame(String title){
		//setting up frame
		super(title);
		setLayout(new FlowLayout());
		
		//Labels For Input
		labelText1=new JLabel("Enter Value for 1(x,r,rou):");
		labelText2=new JLabel("Enter Value For 2(y,theta,phi):");
		labelText3=new JLabel("Enter Value for 3(z,z,,theta):");
		computeButton=new JButton("Click To Compute");
		//Label For Output
		answerA1=new JLabel();
		answerA2=new JLabel();
		answerB1=new JLabel();
		answerB2=new JLabel();
		answerC1=new JLabel();
		answerC2=new JLabel();
		//setting input fields
		inp1=new JTextField(5);
		inp1.setToolTipText("Keep value ranging from");
		inp2=new JTextField(5);
		inp2.setToolTipText("Keep value ranging from\nhappening");
		inp3=new JTextField(5);
		inp3.setToolTipText("Keep value ranging from");
		//Event Handlers
		TextFieldHandler textHandler=new TextFieldHandler();
		ButtonHandler buttonHandler=new ButtonHandler();
		add(labelText1);
		add(inp1);
		add(labelText2);
		add(inp2);
		add(labelText3);
		add(inp3);
		add(computeButton);
		add(answerA1);
		add(answerA2);
		add(answerB1);
		add(answerB2);
		add(answerC1);
		add(answerC2);
		inp1.addActionListener(textHandler);
		inp2.addActionListener(textHandler);
		inp3.addActionListener(textHandler);
		computeButton.addActionListener(buttonHandler);
	}
	
	//Event Handler For Button
	private class ButtonHandler implements ActionListener{
		Double value1;
		Double value2;
		Double value3;
		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource()==computeButton){
				value1=Double.parseDouble(inp1.getText());
				value2=Double.parseDouble(inp2.getText());
				value3=Double.parseDouble(inp3.getText());		
				//Cartesian (x,y,z)||Cylindrical (r,theta,Z)||Spherical (rou,phi,theta)
				
				//A - Compute when Input is Cartesian Coordinate
				
				//A-1 Cartesian To Cylindrical
				Double R=Math.sqrt(value1*value1+value2*value2);
				Double phi=Math.toDegrees(Math.atan(value1/value2));
				String ansTextA1=new String();
				ansTextA1=String.format("When Provided Values are Cartesian Then Cylindrical" +
						"Coordinates Have : "+"The Value of r : %f The Value For Pi : %f " +
						"The Value For Z : %f",R,phi,value3 );
				answerA1.setText(ansTextA1);
				//A-2 Cartesian To Spherical
				Double rou=Math.sqrt(value1*value1+value2*value2+value3*value3);
				Double theta=Math.toDegrees(Math.atan(value2/value1));
				phi=Math.toDegrees(Math.acos(value3/(Math.sqrt(value1*value1+value2*value2+value3*value3))));
				String ansTextA2=new String();
				ansTextA2=String.format("When Provided Values are Cartesian Then Spherical " +
						"Coordinates Have : "+"The Value of Rou : %f The Value For Theta : %f " +
						"The Value For Phi : %f",rou,theta,phi );
				answerA2.setText(ansTextA2);
				
				
				//B - Compute When Input is Cylindrical(r,theta,z)
				//B-1 Cylindric	al to Spherical, r=X, theta=Y, z=Z
				rou=Math.sqrt(value1*value1+value3*value3);
				phi=Math.toDegrees(Math.atan(value1/value3));
				String ansTextB1=new String();
				ansTextB1=String.format("When Provided Values are Cylindrical Then Spherical" +
						"Coordinates Have : "+"The Value of Rou : %f The Value For Theta : %f " +
						"The Value For Phi : %f",rou,value2,phi );
				answerB1.setText(ansTextB1);
				//B-2 Cylindrical To Cartesian (X=r,Y=theta,Z=z) to (X,Y,Z)
				Double x=value1*Math.cos(Math.toRadians(value2));
				Double y=value1*Math.sin(Math.toRadians(value2));
				Double z=value3;
				String ansTextB2=new String();
				ansTextB2=String.format("When Provided Values are Cylindrical Then Cartesian" +
						"Coordinates Have : "+"The Value of x : %f The Value For y : %f " +
						"The Value For z : %f",x,y,z );
				answerB2.setText(ansTextB2);
				
				
				//C - Compute When Input is Spherical(rou,phi,theta)
				//C-1 Spherical(rou,phi,theta) To Cartesian(x,y,z)
				x=value1*Math.cos(Math.toRadians(value3))*Math.sin(Math.toRadians(value2));
				y=value1*Math.sin(Math.toRadians(value3))*Math.sin(Math.toRadians(value2));
				z=value1*Math.cos(Math.toRadians(value2));
				String ansTextC1=new String();
				ansTextC1=String.format("When Provided Values are Spherical Then Cartesian" +
						"Coordinates Have : "+"The Value of x : %f The Value For y : %f " +
						"The Value For z : %f",x,y,z );
				answerC1.setText(ansTextC1);
				//C-2 Spherical(rou,phi,theta) To Cylindrical(r,theta,z)
				R=value1*Math.sin(Math.toRadians(value2));
				z=value1*Math.cos(Math.toRadians(value2));
				theta=value3;
				String ansTextC2=new String();
				ansTextC2=String.format("When Provided Values are Spherical Then Cylindrical" +
						"Coordinates Have : "+"The Value of r : %f The Value For theta : %f " +
						"The Value For z : %f",R,theta,z );
				answerC2.setText(ansTextC2);
			
				System.out.print("working");	
			
			}
	
		}
		
}
	

	//Event Handler For Text Field
	private class TextFieldHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {

		if(event.getSource()==inp1){
			String mystring =new String();
			try{
				inputValue1=Double.parseDouble(inp1.getText());
				JOptionPane.showMessageDialog(null,"You entered FIRST value," +
						" make sure you enter all the value, then Click" +
						" 'CLICK TO COMPUTE' button to compute");
			}catch(Exception err){}
		}
		if(event.getSource()==inp2){
			try{
				inputValue2=Double.parseDouble(inp2.getText());
				JOptionPane.showMessageDialog(null,"You entered SECOND value," +
						" make sure you enter all the value, then Click" +
						" 'CLICK TO COMPUTE' button to compute");
			}catch(Exception err){}
		}
		if(event.getSource()==inp3){
			try{
				inputValue3=Double.parseDouble(inp3.getText());
				JOptionPane.showMessageDialog(null,"You entered THIRD value," +
						" make sure you enter all the value, then Click" +
						" 'CLICK TO COMPUTE' button to compute");
			}catch(Exception err){}
		}
		System.out.print(inputValue1);
		System.out.print(inputValue2);
		System.out.print(inputValue3);
		
		}
	}
}

