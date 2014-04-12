package ie.mtt.tl.view.mb;

import ie.mtt.tl.factory.ServiceFactory;
import ie.mtt.tl.model.entities.SessionLog;
import ie.mtt.tl.service.SessionLogService;
import ie.mtt.tl.view.mb.util.Utility;

import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginMB {

	private static final Logger logger = LoggerFactory.getLogger(LoginMB.class);
	private String userName;
	private String password;
	private Hashtable<String,String> users;
	private SessionLogService sessionLogService=ServiceFactory.getSessionLogService();

	/**
	 * 
	 */
	public LoginMB() {
		users=new Hashtable<String, String>();
		users.put("test1", "abc1231");
		users.put("test2", "abc1232");
		users.put("test3", "abc1233");
		users.put("test4", "abc1234");
	}
	
	/**
	 * Logging Method
	 * @return actionFlow
	 */
	public String login(){
		String actionFlow=null;
		String retrievePass=null;
		if(users.containsKey(userName)){
			retrievePass = users.get(userName);
			logger.info("User Ok");
			if(password.equals(retrievePass)){
				logger.info("Password Ok");
				UserBean user=new UserBean(userName.toUpperCase());
				Integer sessionId;
				try {
					sessionId = sessionLogService.getSessionIdByUserName(user.getUserName());
					
					if(sessionId!=null){
						logger.info("Session Id Ok");
						SessionLog log=new SessionLog();

						log.setDateAdd(new java.sql.Date(new java.util.Date().getTime()));
						log.setUserName(user.getUserName());
						log.setAction("[Login] Last Login on "+Utility.dateFormat(new java.util.Date()));
						log.setSessionId(sessionId);
						sessionLogService.save(log);
						
						user.setSessionId(sessionId);
					}
				} catch (Exception e) {
					Utility.setErrorMessage("Error: Getting SessionId");
				}
				
				Utility.subirASession("userBean", user);
				actionFlow="TODOLIST";
			}else
				Utility.setErrorMessage("Error: Invalid Password");
		}else{
			Utility.setErrorMessage("Error: Invalid Username");
		}
		return actionFlow;
	}

	
	/**
	 * 
	 * @return
	 */
	public String logout(){
		return Utility.logout("todoBean");
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
