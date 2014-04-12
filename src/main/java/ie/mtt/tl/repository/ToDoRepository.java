package ie.mtt.tl.repository;

import ie.mtt.tl.model.entities.ToDo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ToDoRepository extends CrudRepository<ToDo, Integer>, ToDoRepositoryCustom {

	@Transactional(readOnly=true)
	public List<ToDo> findByUserName(String userName);

}
