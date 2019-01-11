package com.enr_operational.utilities;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Credentials {
	private static String username;
	private static String password;
	
	
	
    private static void requestCredentials() {
		final JFrame frame = new JFrame("JDialog Demo");
		 UserCredentialsUtil loginDlg = new UserCredentialsUtil(frame);
	        loginDlg.setVisible(true);
	        // if logon successfully
	        if(loginDlg.isSucceeded()){
	        	username = loginDlg.getUsername() ;
	        	password = loginDlg.getPassword();
	        } 
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());
        frame.setVisible(false);
    }
    
    public static String[] getCredentials() {
    	
    	if(username == null || password == null) {
    		requestCredentials();
    	}
    	return new String[] {username,password};
    }
   
    
}
