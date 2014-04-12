package ie.mtt.tl.repository;

import ie.mtt.tl.model.entities.SessionLog;
import ie.mtt.tl.model.entities.ToDo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

public class ToDoRepositoryImpl implements ToDoRepositoryCustom {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public ToDo saveAndGenerateLogs(ToDo todo, SessionLog sessionLog) {
		em.persist(sessionLog);
		em.persist(todo);
		
		return todo;
	}

}
