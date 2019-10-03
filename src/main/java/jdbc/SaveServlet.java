package jdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet

{
	
	private static final long serialVersionUID = 1L;
	private ApplicationContext ctx;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Hi Kajan");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("projectName");
		String desc = req.getParameter("projectDescription");
		
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Project p = (Project)ctx.getBean("proj");
		p.setProjectName(name);
		p.setProjectDescription(desc);
		
		ProjectDao dao = (ProjectDao)ctx.getBean("dao");
			
		int status = dao.saveProject(p);
		if(status>0) {
			out.print("<p>Record saved successfully!</p>");
			req.getRequestDispatcher("view.jsp").include(req, resp);
		}
		else{
			out.print("Sorry! unable to save record!");
		}
		
		out.close();
	}
}

	