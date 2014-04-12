package ie.mtt.tl.view.mb;

import ie.mtt.tl.factory.ServiceFactory;
import ie.mtt.tl.model.entities.SessionLog;
import ie.mtt.tl.model.entities.ToDo;
import ie.mtt.tl.service.SessionLogService;
import ie.mtt.tl.service.ToDoService;
import ie.mtt.tl.view.mb.util.Utility;

import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.SortOrder;
import org.richfaces.component.UIDataTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Title: ManagedBean
 * </p>
 * 
 * <p>
 * Description: Managed Bean
 * 
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * 
 * @author Farith José Heras García
 * @version 1.0
 */

public class TodoListMB {
	
	private static final Logger logger = LoggerFactory.getLogger(TodoListMB.class);
	private List<ToDo> dataList;
	private List<SessionLog> logList;
	private ToDo todo = new ToDo();
	private boolean showSessionLogs = true;
	private int rowSelected = 0;
	private int page = 1;
	private UIDataTable dtItems=new UIDataTable();
	private SortOrder descOrder = SortOrder.unsorted;
	private SortOrder dateOrder = SortOrder.unsorted;
	private UserBean user;
	private SessionLogService sessionLogService=ServiceFactory.getSessionLogService();
	private ToDoService toDoService=ServiceFactory.getToDoService();
	/**
	 * Constructor
	 */
	public TodoListMB() {
		try {
			user = (UserBean)Utility.getFromSession("userBean");
			dataList = toDoService.findByUserName(user.getUserName());
			logList = sessionLogService.findByUserNameAndSessionId(user.getUserName(),user.getSessionId()-1); 
		} catch (Exception e) {
			Utility.setErrorMessage(Utility.MESSAGE_ERROR_MANAGED_BEAN
					+ this.getClass().getName() + ", Error: " + e.getMessage());
		}
	}

	/**
	 * Hide the Log table 
	 */
	private void hideLogs(){
		showSessionLogs = false;
		logList = null;
	}
	
	/**
	 * Sort by Description Task
	 */
	public void sortByDesc() {
		dateOrder = SortOrder.unsorted;
		if (descOrder.equals(SortOrder.ascending)) {
			setDescOrder(SortOrder.descending);
		} else {
			setDescOrder(SortOrder.ascending);
		}
	}

	/**
	 * Sort by Creation Date
	 */
	public void sortByDate() {
		descOrder = SortOrder.unsorted;
		if (dateOrder.equals(SortOrder.ascending)) {
			setDateOrder(SortOrder.descending);
		} else {
			setDateOrder(SortOrder.ascending);
		}
	}
	
	/**
	 * update ToDo check / unCheck
	 * @param event
	 */
	public void updateTodo(ValueChangeEvent event){
        try {
    		hideLogs();
    		Boolean value = (Boolean)event.getNewValue();
    		rowSelected=dtItems.getRowIndex();
            todo = dataList.get(rowSelected);

            SessionLog log=new SessionLog();
        	todo.setDone((value?1:0));

        	log.setDateAdd(todo.getDateAdd());
			log.setUserName(todo.getUserName());
			log.setAction("[Update] TODO: "+todo.getDescription()+" "+(todo.getDone().equals(1)?"check":"uncheck")+" on "+Utility.dateFormat(new java.util.Date()));
			log.setSessionId(user.getSessionId());			

			sessionLogService.save(log);
        	
        	toDoService.save(todo);
        	
			dataList.set(rowSelected, todo);
			logger.info("Update ToDo Ok");
        } catch (Exception e) {
			Utility.setErrorMessage(Utility.MESSAGE_ERROR_SAVING_DATA + e);
		}
        todo = new ToDo();
	}

	/**
	 * Delete a ToDo record by Id 
	 */
	public void delete() {
		try {
			hideLogs();

			SessionLog log=new SessionLog();
			rowSelected=dtItems.getRowIndex();
            todo = dataList.get(rowSelected);
            
			log.setDateAdd(todo.getDateAdd());
			log.setUserName(todo.getUserName());
			log.setAction("[Delete] TODO: "+todo.getDescription()+" Deleted on "+Utility.dateFormat(new java.util.Date()));
			log.setSessionId(user.getSessionId());			

			sessionLogService.save(log);
            toDoService.delete(todo);

            dataList.remove(rowSelected);
			todo = new ToDo();
			logger.info("Delete ToDo Ok");
			Utility.setInfoMessage("Successfully Removed!");
		} catch (Exception e) {
			Utility.setErrorMessage(Utility.MESSAGE_ERROR_DELETING_DATA + e);
		}
	}
    
	/**
	 * Save ToDo record
	 * @param ActionEvent
	 */
	public void save(ActionEvent ae) {
		try {
			hideLogs();
			todo.setUserName(user.getUserName());
			SessionLog log=new SessionLog();
			
			log.setDateAdd(todo.getDateAdd());
			log.setUserName(todo.getUserName());
			log.setAction("[New] TODO: "+todo.getDescription()+" created on "+Utility.dateFormat(new java.util.Date()));
			log.setSessionId(user.getSessionId());			
			
			toDoService.save(todo,log);
			
			dataList.add(todo);
			logger.info("save ToDo Ok");
		} catch (Exception e) {
			Utility.setErrorMessage(Utility.MESSAGE_ERROR_SAVING_DATA + e);
		}
		todo = new ToDo();
	}

	/**
	 * @return the dataList
	 */
	public List<ToDo> getDataList() {
		return dataList;
	}

	/**
	 * @param dataList the dataList to set
	 */
	public void setDataList(List<ToDo> dataList) {
		this.dataList = dataList;
	}

	/**
	 * @return the logList
	 */
	public List<SessionLog> getLogList() {
		return logList;
	}

	/**
	 * @param logList the logList to set
	 */
	public void setLogList(List<SessionLog> logList) {
		this.logList = logList;
	}

	/**
	 * @return the todo
	 */
	public ToDo getTodo() {
		return todo;
	}

	/**
	 * @param todo the todo to set
	 */
	public void setTodo(ToDo todo) {
		this.todo = todo;
	}


	/**
	 * @return the rowSelected
	 */
	public int getRowSelected() {
		return rowSelected;
	}

	/**
	 * @param rowSelected the rowSelected to set
	 */
	public void setRowSelected(int rowSelected) {
		this.rowSelected = rowSelected;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the dtItems
	 */
	public UIDataTable getDtItems() {
		return dtItems;
	}

	/**
	 * @param dtItems the dtItems to set
	 */
	public void setDtItems(UIDataTable dtItems) {
		this.dtItems = dtItems;
	}

	/**
	 * @return the descOrder
	 */
	public SortOrder getDescOrder() {
		return descOrder;
	}

	/**
	 * @param descOrder the descOrder to set
	 */
	public void setDescOrder(SortOrder descOrder) {
		this.descOrder = descOrder;
	}

	/**
	 * @return the dateOrder
	 */
	public SortOrder getDateOrder() {
		return dateOrder;
	}

	/**
	 * @param dateOrder the dateOrder to set
	 */
	public void setDateOrder(SortOrder dateOrder) {
		this.dateOrder = dateOrder;
	}

	/**
	 * @return the user
	 */
	public UserBean getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserBean user) {
		this.user = user;
	}

	/**
	 * @return the showSessionLogs
	 */
	public boolean isShowSessionLogs() {
		return showSessionLogs;
	}

	/**
	 * @param showSessionLogs the showSessionLogs to set
	 */
	public void setShowSessionLogs(boolean showSessionLogs) {
		this.showSessionLogs = showSessionLogs;
	}



}
