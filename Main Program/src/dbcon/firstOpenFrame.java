package dbcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class firstOpenFrame extends JFrame{
	JButton signin=new JButton("giris");
	JTextField username= new JTextField("");
	JPasswordField password= new JPasswordField();
	JPanel jp=new JPanel();
	public static String nickname;
	firstOpenFrame(final Connection con){
		this.setTitle("Giris");
		this.setSize(340,70);
		this.setLocation(500,500);
		this.setResizable(false);
		this.setVisible(true);
		
		this.jp.add(username);
		this.jp.add(password);
		this.jp.add(signin);
		this.add(jp);
		
		this.jp.setLayout(null);
		
		this.username.setLocation(5,5);
		this.password.setLocation(110,5);
		this.signin.setLocation(220,5);
		

		this.username.setSize(100,20);
		this.password.setSize(100,20);
		this.signin.setSize(100,20);
		
		signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkuser(con)){System.out.println("giris basarili");
					GUI a=new GUI(con);
					setVisible(false);
				
				}
				else{System.out.println("giris basarisiz");}
				}
			}
		);
		
	}
	
	public boolean checkuser(Connection conn){
		 boolean trueuser=false;
		try{
	      PreparedStatement stmt = conn.prepareStatement("select nick,password from user;");
	      ResultSet r1=stmt.executeQuery();
	      PreparedStatement stmt2;
	      while(r1.next()) {
	    	  
	    	  if(username.getText().equals(r1.getString("nick")) && password.getText().equals(r1.getString("password"))){
	    		  nickname=username.getText();
	    		  trueuser=true;
	    		  stmt2=conn.prepareStatement("update user set online=1 where nick=\""+nickname+"\";");
	    		  stmt2.executeUpdate();
	    		  break;}
	    	  else {trueuser=false;}
	      }
	}catch(Exception e){System.out.println(e);}
		return trueuser;
	 }
}
