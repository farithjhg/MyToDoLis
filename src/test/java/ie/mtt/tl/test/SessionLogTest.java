package ie.mtt.tl.test;

import static org.junit.Assert.assertNotNull;
import ie.mtt.tl.model.entities.SessionLog;
import ie.mtt.tl.service.SessionLogService;
import java.sql.SQLException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SessionLogTest {
	private SessionLog sessionLog;
	private SessionLogService sessionLogService;

	@Before
	public void setUp() throws Exception {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		System.out.println("App Context initialized successfully");

		sessionLogService = (SessionLogService) ctx
				.getBean("sessionLogService");
	}

	@Test
	public void testAddsessionLog() throws SQLException {
		sessionLog = new SessionLog();
		sessionLog.setAction("[NEW] Session Log");
		sessionLog.setUserName("USER");
		sessionLog.setSessionId(1000);
		sessionLog
				.setDateAdd(new java.sql.Date(new java.util.Date().getTime()));

		sessionLogService.save(sessionLog);

		assertNotNull(sessionLog.getId());
		System.out.println(sessionLog.getId());
	}

	@Test
	public void testFindByUserName() throws SQLException {
		// Find all contacts
		List<SessionLog> sessionLogs = sessionLogService
				.findByUserNameAndSessionId("USER", 1000);
		System.out.println(sessionLogs);
	}

	@Test
	public void testGetSessionIdByUserName() throws SQLException {
		// Find all contacts
		Integer sessionId = sessionLogService.getSessionIdByUserName("USER");
		assertNotNull(sessionId);
		System.out.println(sessionId);			
	}

}
