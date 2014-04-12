package ie.mtt.tl.service;

import ie.mtt.tl.model.entities.SessionLog;

import java.util.List;

public interface SessionLogService {
	 /**
     * Return SessionLog's entity by PK 
     */
    public SessionLog findByPK(Integer id) ;

    /**
     * Return SessionLog rows
     */
    List<SessionLog> findAll() ;;

      
    /**
     * Add a row in the SessionLog Table
     * @return 
     */
    public SessionLog save(SessionLog entity) ;

 
    /**
    * Delete a row in the SessionLogList Table by PK
    */
    public void delete(SessionLog entity) ;
    
    /**
     * Return SessionLog rows by User Name
     * @param userName
     * @return List<SessionLog>
     */
    public List<SessionLog> findByUserNameAndSessionId(String userName,Integer sessionId);
    
    /**
     * Desc: Return a SessionId
     * @param userName
     * @return Integer SessionId
     */
    public Integer getSessionIdByUserName(String userName);    

    
}

