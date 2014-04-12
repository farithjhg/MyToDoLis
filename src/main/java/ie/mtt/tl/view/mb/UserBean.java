/**
 * 
 */
package ie.mtt.tl.view.mb;

/**
 * @author Farith
 *
 */
public class UserBean {
	private String userName;
	private Integer sessionId;
	
	public UserBean() {
	}

	public UserBean(String user) {
		this.userName=user;
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
	 * @return the sessionId
	 */
	public Integer getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	
}
