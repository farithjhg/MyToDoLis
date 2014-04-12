package ie.mtt.tl.test;

import static org.junit.Assert.assertNotNull;
import ie.mtt.tl.model.entities.SessionLog;
import ie.mtt.tl.model.entities.ToDo;
import ie.mtt.tl.service.ToDoService;
import ie.mtt.tl.view.mb.util.Utility;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TodoTest {
	private ToDo todo;
	private ToDoService toDoService;

	@Before
	public void setUp() throws Exception {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		System.out.println("App Context initialized successfully");
		
		toDoService = (ToDoService) ctx.getBean("todoService");
	}

	@Test
	public void testAddToDo() throws SQLException {
		todo = new ToDo();
		todo.setDescription("Task 2");
		todo.setUserName("USER");
		todo.setDateAdd(new java.sql.Date(new java.util.Date().getTime()));

		toDoService.save(todo);

		assertNotNull(todo.getId());
	}

	@Test
	public void testAddToDoAndGenerateSessionLog() throws SQLException {
		todo = new ToDo();
		todo.setDescription("Task 2");
		todo.setUserName("USER");
		todo.setDateAdd(new java.sql.Date(new java.util.Date().getTime()));

		SessionLog sessionLog=new SessionLog();
		sessionLog.setUserName(todo.getUserName());
		sessionLog.setAction("[New] TODO: "+todo.getDescription()+" created on "+Utility.dateFormat(new java.util.Date()));
		sessionLog.setDateAdd(todo.getDateAdd());
		sessionLog.setSessionId(1000);
		
		toDoService.saveAndGenerateLogs(todo,sessionLog);

		assertNotNull(todo.getId());
		assertNotNull(sessionLog.getId());
		
		System.out.println(todo);
		System.out.println(sessionLog);
	}
	
	
	@Test
	public void testFindByUserName() throws SQLException {
		List<ToDo> toDos = toDoService.findByUserName("USER");		
		assertNotNull(toDos);
		System.out.println(toDos);			
	}

	@Test
	public void testFindByPK() throws SQLException {
		todo = toDoService.findByPK(1);
		assertNotNull(todo);
		System.out.println(todo);
	}

	
	@Test
	public void testFindAll() throws SQLException {
		List<ToDo> list=toDoService.findAll();
		assertNotNull(list);
		System.out.println(list);
	}
}
