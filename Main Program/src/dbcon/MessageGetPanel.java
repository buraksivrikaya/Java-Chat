package dbcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class MessageGetPanel extends JPanel implements Runnable{
	JTextArea t1=new JTextArea("mesajlar");
	JButton ghost=new JButton("Meþgul");
	JLabel l1=new JLabel ("-ONLINES-");
	JTextArea onlines=new JTextArea();
	JScrollPane jp=new JScrollPane();
	Connection conn;
	public MessageGetPanel(final Connection conn){
		DefaultCaret caret = (DefaultCaret)t1.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		
		this.conn=conn;
		this.setSize(400,400);
		//this.t1.setSize(310,350);
		this.t1.setEditable(false);
		this.t1.setText(getMessages(conn));
		this.t1.setLineWrap(true);
		this.jp.setSize(310,390);
		this.jp.setLocation(5,5);
		this.jp.setViewportView(t1);
		this.add(jp);
		this.setLayout(null);
		this.l1.setLocation(320,5);
		this.l1.setSize(70,15);
		
		this.add(l1);
		this.onlines.setSize(70,300);
		this.onlines.setLocation(320,30);
		this.onlines.setEditable(false);
		this.onlines.setOpaque(false);
		this.onlines.setFocusable(false);
		this.onlines.setText("onlines \n");
		this.add(onlines);
		
		this.add(ghost);
		this.ghost.setSize(70,30);
		this.ghost.setLocation(320,360);
		
		this.ghost.addActionListener(new ActionListener() {
			int state=2;
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					PreparedStatement smt;
					if(state==0){
						smt= conn.prepareStatement("update user set online=0 where nick=\""+firstOpenFrame.nickname+"\";"); //offline
						state++;System.out.println("offline");ghost.setText("Açýk");
						}
					else if(state == 1){
						smt= conn.prepareStatement("update user set online=1 where nick=\""+firstOpenFrame.nickname+"\";"); //online
						state++;System.out.println("online");ghost.setText("Meþgul");
					}
					
					else{
						smt= conn.prepareStatement("update user set online=2 where nick=\""+firstOpenFrame.nickname+"\";"); //busy
						state=0;System.out.println("busy");ghost.setText("Kapalý");
					}
					smt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	

	public String getMessages(Connection conn){
		String finalstring="";
		try{	
			
		      PreparedStatement stmt = conn.prepareStatement("select nick,content from message;");
		      ResultSet r1=stmt.executeQuery();
		      
		      while(r1.next()) {
		    	  
		    	  finalstring+=r1.getString("nick")+"   : " + r1.getString("content")+"\n";
		      }
		}catch(Exception e){System.out.println(e+ "hata burda!");}
		 
		return finalstring;
		
	}
	
	String getState(int n){
		if(n==1){return "On";}
		else if(n==2){return "Busy";}
		return null;
	}
	
	public String getOnlines(){
		String finalstring="";
		try{	
			
		      PreparedStatement stmt = conn.prepareStatement("select nick,online from user where online=1 or online=2 ;");
		      ResultSet r1=stmt.executeQuery();
		      
		      while(r1.next()) {
		    	  
		    	  finalstring+="("+getState(r1.getInt("online"))+") "+r1.getString("nick")+"\n";
		      }
		}catch(Exception e){System.out.println(e);}
		 
		return finalstring;
	}
	public void refreshData(){
		t1.setText(getMessages(conn));
		onlines.setText(getOnlines());
	}

	public void run() {
		while (true){
			refreshData();
		
			try{Thread.sleep(1500);}catch(Exception e){System.out.println("hata : "+e);}
		}
	}
	
}
