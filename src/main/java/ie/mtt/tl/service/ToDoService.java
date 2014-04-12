package ie.mtt.tl.service;

import ie.mtt.tl.model.entities.SessionLog;
import ie.mtt.tl.model.entities.ToDo;

import java.util.List;

public interface ToDoService {
	 /**
     * Return ToDo's entity by PK 
     */
    public ToDo findByPK(Integer id) ;

    /**
     * Return ToDo rows
     */
    List<ToDo> findAll() ;;

      
    /**
     * Add a row in the ToDo Table
     * @return ToDo 
     */
    public ToDo save(ToDo entity) ;

    /**
     * Add a row in the ToDo Table and in the SessionLog Table
     * at same time.
     * @return ToDo 
     */
    public ToDo save(ToDo todo, SessionLog sessionLog);
    
    /**
    * Delete a row in the TodoList Table by PK
    */
    public void delete(ToDo entity) ;
    
    /**
     * Return TodoList rows by User Name
     * @param userName
     * @return List<ToDo>
     */
    public List<ToDo> findByUserName(String userName);
    
    public ToDo saveAndGenerateLogs(ToDo todo, SessionLog sessionLog);
}

