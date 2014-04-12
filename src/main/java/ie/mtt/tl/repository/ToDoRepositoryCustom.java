package ie.mtt.tl.repository;

import ie.mtt.tl.model.entities.SessionLog;
import ie.mtt.tl.model.entities.ToDo;

import org.springframework.transaction.annotation.Transactional;

public interface ToDoRepositoryCustom {

	
	@Transactional
	public ToDo saveAndGenerateLogs(ToDo todo, SessionLog sessionLog);

}
