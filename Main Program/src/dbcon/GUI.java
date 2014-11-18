package dbcon;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class GUI extends JFrame{
	MessageGetPanel mgp;
	MessageSendPanel msp;
	Connection conn;
	
	public GUI(Connection conn){
		this.conn=conn;
		this.setTitle("Chat Window ~"+firstOpenFrame.nickname);
		this.setVisible(true);
		this.setLocation(300,300);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(410,530);
		mgp=new MessageGetPanel(conn);
		msp=new MessageSendPanel(conn);
		this.add(mgp);
		this.add(msp);
		this.setLayout(null);
		mgp.setLocation(5,5);
		msp.setLocation(5,410);
		this.setResizable(false);
		
		WindowListener exitListener = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	GoOfline();
                System.exit(1);
            }
        };
        
        this.addWindowListener(exitListener);
        
        
        Thread t1=new Thread(mgp);
        t1.start();
	}
	public void GoOfline(){
	try {
		PreparedStatement smt= conn.prepareStatement("update user set online=0 where nick=\""+firstOpenFrame.nickname+"\";");
		smt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
}
