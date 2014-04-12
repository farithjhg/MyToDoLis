package ie.mtt.tl.repository;

import ie.mtt.tl.model.entities.SessionLog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionLogRepository extends JpaRepository<SessionLog, Integer> {
    /**
     * Return Log rows by User Name
     */
	public List<SessionLog> findByUserNameAndSessionId(String userName,Integer sessionId);
	
	public Integer getSessionIdByUserName(String userName);
	
}
