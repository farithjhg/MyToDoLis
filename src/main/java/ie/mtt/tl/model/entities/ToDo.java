package ie.mtt.tl.model.entities;

import ie.mtt.tl.view.mb.UserBean;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@NamedQuery(name = "ToDo.findAll", query = "select o from ToDo o") 
@Table(name = "ToDo")
public class ToDo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id", nullable = false)
    private Integer id;
    @Column(name="Description")
    private String description;
    @Column(name="User_Name")
    private String userName;
    @Column(name="Date_Add")
    private Date dateAdd;
    @Column(name="Date_Update")
    private Date dateUpdate;
    @Column(name="Done")
    private Integer done;
    @Transient
    private boolean finish;
    @Transient
    private UserBean user;

    public ToDo() {
    	this.dateAdd=new Date(new java.util.Date().getTime());
    	this.done=0;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDateAdd(){
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd){
        this.dateAdd = dateAdd;
    }

    public Date getDateUpdate(){
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate){
        this.dateUpdate = dateUpdate;
    }

    public Integer getDone() {
		return done;
	}

	public void setDone(Integer done) {
		this.done = done;
	}

	public boolean isFinish() {
		return done.equals(1);
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public String toString(){
    	return "ToDo - Id: "+id+", Description: "+description+", UserName: "+userName+", Creation Date: "+dateAdd;
    }
	

}
