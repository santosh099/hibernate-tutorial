package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo 
{
	public static void main(String[] args)
	{
		// create session factory
		SessionFactory factory = new Configuration()
						         .configure("hibernate.cfg.xml")
						         .addAnnotatedClass(Student.class)
						         .buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
				
		try
		{			
			// start the transaction
			session.beginTransaction();
					
			// query students
			List<Student> theStudents = session.createQuery("from Student").list();
			
			// display the students
			displayStudents(theStudents);
					
			// query students: lastName='Doe'
			
			theStudents = session.createQuery("from Student s where s.lastName ='Doe'").list();
			
			// display the students
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(theStudents);
			
			
			// query students: lastName ='Doe' or firstName ='Daffy'
            theStudents = 
            		session.createQuery("from Student s where"
            				    + " s.lastName='Doe' OR s.firstName='Daffy'").list();
            System.out.println("\n\nStudents who have last name of Doe OR first name Daffy");
            displayStudents(theStudents);
            
            // query students where email LIKE '%luv2code.com'
            theStudents = session.createQuery("from Student s where"
            		           + " s.email LIKE '%luv2code.com'").list();
            System.out.println("\n\nStudents who's email ends with luv2code.com");
            displayStudents(theStudents);
            
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}finally
		{
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent:theStudents)
		{
			System.out.println(tempStudent);
		}
	}
}

