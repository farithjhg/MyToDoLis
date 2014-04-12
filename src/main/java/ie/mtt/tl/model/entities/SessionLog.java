package ie.mtt.tl.model.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
		@NamedQuery(name = "SessionLog.findAll", query = "select o from SessionLog o"),
		@NamedQuery(name = "SessionLog.findByUserName", query = "SELECT o FROM SessionLog o WHERE o.userName = :user") })
@Table(name = "SessionLog")
public class SessionLog implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id", nullable = false)
	private Integer id;
	@Column(name = "User_Name", nullable = false)
	private String userName;
	@Column(name = "Action", nullable = false)
	private String action;
    @Column(name="Date_Add")
    private Date dateAdd;
	@Column(name = "Session_Id", nullable = false)
	private Integer sessionId;

	public SessionLog() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDateAdd() {
		return dateAdd;
	}

	public void setDateAdd(Date dateAdd) {
		this.dateAdd = dateAdd;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
	
	@Override
	public String toString() {
		return "[SessionLog]: "+id+"-"+action;
	}
	
}
