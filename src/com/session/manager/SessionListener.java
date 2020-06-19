package com.session.manager;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener("This is a Listener.")
public class SessionListener implements HttpSessionListener {
	private MySessionContext myc = MySessionContext.getInstance();  
    
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {  
        HttpSession session = httpSessionEvent.getSession();
        System.out.println("session created!");
        
        myc.addSession(session);  
    }  
  
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {  
        HttpSession session = httpSessionEvent.getSession();  
        System.out.println("session destroyed!");
        
        myc.delSession(session);  
    }
}
