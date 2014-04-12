package ie.mtt.tl.service.jpa;

import ie.mtt.tl.model.entities.SessionLog;
import ie.mtt.tl.model.entities.ToDo;
import ie.mtt.tl.repository.SessionLogRepository;
import ie.mtt.tl.repository.ToDoRepository;
import ie.mtt.tl.service.ToDoService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;




@Service("todoService")
@Repository
@Transactional
public class ToDoServiceImpl implements ToDoService {
	
	@Autowired
	private ToDoRepository toDoRepository;
	@Autowired
	private SessionLogRepository sessionLogRepository;

	
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly=true)
	public ToDo findByPK(Integer id) {
		return (ToDo) toDoRepository.findOne(id);
	}

	@Transactional(readOnly=true)
	public List<ToDo> findAll() {
		return (List<ToDo>) toDoRepository.findAll();
	}

	public ToDo save(ToDo entity) {
		if(entity.getId()==null){
			toDoRepository.save(entity);
		}else{
			em.merge(entity);
		}
		return entity;
	}



	public void delete(ToDo entity) {
		toDoRepository.delete(findByPK(entity.getId()));
	}

	@Override
	public List<ToDo> findByUserName(String userName) {
		return toDoRepository.findByUserName(userName);
	}

	public ToDo save(ToDo todo, SessionLog sessionLog) {
		sessionLogRepository.save(sessionLog);
		toDoRepository.save(todo);
		
		return todo;
	}

	@Override
	public ToDo saveAndGenerateLogs(ToDo todo, SessionLog sessionLog) {
		// TODO Auto-generated method stub
		return toDoRepository.saveAndGenerateLogs(todo, sessionLog);
	}
	

	
}
