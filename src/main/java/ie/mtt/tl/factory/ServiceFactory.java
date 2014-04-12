package ie.mtt.tl.factory;

import ie.mtt.tl.service.SessionLogService;
import ie.mtt.tl.service.ToDoService;

import org.springframework.context.support.GenericXmlApplicationContext;


public class ServiceFactory {
	private static ToDoService toDoService;
	private static SessionLogService sessionLogService;
	private static GenericXmlApplicationContext ctx;
	
	public static SessionLogService getSessionLogService(){
		if(ctx==null)
			ctx=getCtx();
		
		if(sessionLogService==null)
			sessionLogService = (SessionLogService) ctx
				.getBean("sessionLogService");
		
		return sessionLogService;
	}
	
	public static ToDoService getToDoService(){
		if(ctx==null)
			ctx=getCtx();

		if(toDoService==null)
			toDoService = (ToDoService) ctx.getBean("todoService");

		return toDoService;
	}

	public static GenericXmlApplicationContext getCtx() {
		ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		System.out.println("App Context initialized successfully");
		
		return ctx;
	}
	
}
