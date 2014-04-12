package ie.mtt.tl.service.jpa;

import ie.mtt.tl.model.entities.SessionLog;
import ie.mtt.tl.repository.SessionLogRepository;
import ie.mtt.tl.service.SessionLogService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("sessionLogService")
@Repository
@Transactional
public class SessionLogServiceImpl implements SessionLogService {
	
	@Autowired
	private SessionLogRepository sessionLogRepository;

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly=true)
	public SessionLog findByPK(Integer id) {
		return (SessionLog) sessionLogRepository.findOne(id);
	}

	@Transactional(readOnly=true)
	public List<SessionLog> findAll() {
		return (List<SessionLog>) sessionLogRepository.findAll();
	}

	
	public SessionLog save(SessionLog entity) {
		if(entity.getId()==null){  //Insert SessionLog
			sessionLogRepository.save(entity);
		}else{  					  //Update SessionLog
			em.merge(entity);
		}
		return entity;
	}



	@Override
	public void delete(SessionLog entity) {
		em.remove(findByPK(entity.getId()));
	}

	@Override
	public List<SessionLog> findByUserNameAndSessionId(String userName, Integer sessionId) {
		return sessionLogRepository.findByUserNameAndSessionId(userName, sessionId);
	}

	@Override
	public Integer getSessionIdByUserName(String userName) {
		String txtQuery = "SELECT max(p.sessionId)+1 FROM SessionLog p where p.userName = :user";
		Query query = null;
        query = em.createQuery(txtQuery);
        query.setParameter("user", userName);
        
        Integer sessionId = (Integer) query.getSingleResult();
        
        if(sessionId==null)
        	sessionId=0;

        return sessionId;
	}


	
}
