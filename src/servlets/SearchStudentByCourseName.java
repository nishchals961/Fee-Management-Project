package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.StudentBean;
import dao.StudentDao;

/**
 * Servlet implementation class SearchStudentByCourseName
 */
@WebServlet("/SearchStudentByCourseName")
public class SearchStudentByCourseName extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String course = request.getParameter("course");
		List<StudentBean> list = StudentDao.getRecordsByCourseName(course);
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Search Student</title>");
		out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='style.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navaccountant.html").include(request, response);
		out.println("<div class='container'>");
		out.println("<h1>Search Student By Course Name</h1>");
		if(list == null) {
			out.println("<p>Sorry, No Record found for "+course+"</p>");
		}else {
		for(int i = 0 ; i < list.size() ; i++) {
			StudentBean bean = list.get(i);
			out.println("<table class='table table-bordered table-striped'>");
			out.print("<tr><td>Rollno:</td><td>"+bean.getRollno()+"</td></tr>");
			out.print("<tr><td>Name:</td><td>"+bean.getName()+"</td></tr>");
			out.print("<tr><td>Email:</td><td>"+bean.getEmail()+"</td></tr>");
			out.print("<tr><td>Sex:</td><td>"+bean.getSex()+"</td></tr>");
			out.print("<tr><td>Course:</td><td>"+course+"</td></tr>");
			out.print("<tr><td>Fee:</td><td>"+bean.getFee()+"</td></tr>");
			out.print("</table>");
		}
		}
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
