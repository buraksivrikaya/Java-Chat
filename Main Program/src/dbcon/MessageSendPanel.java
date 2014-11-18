package dbcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MessageSendPanel extends JPanel {
	JTextField t1=new JTextField("");
	JButton b1=new JButton("gönder");
	public MessageSendPanel(final Connection conn){
		this.setSize(400,100);
		this.setLayout(null);
		this.add(t1);
		this.add(b1);
		t1.setLocation(5,5);
		t1.setSize(280,80);
		b1.setLocation(290,5);
		b1.setSize(100,80);
		b1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				sendMessage(conn);
				System.out.println(t1.getText());
				System.out.println("insert into message values (null,"+"\""+firstOpenFrame.nickname+"\""+",\""+t1.getText()+"\""+");");
				t1.setText("");
			}
		});
	}
	

	public String sendMessage(Connection conn){
		String finalstring="";
		try{	
		      PreparedStatement stmt = conn.prepareStatement("insert into message values (null,"+"\""+firstOpenFrame.nickname+"\""+",\""+t1.getText()+"\""+");");
		      stmt.executeUpdate();
		    
		}catch(Exception e){System.out.println(e);}
		 
		return finalstring;
	}
	
}