package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo 
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
		    int studentId = 1;
			// find out the student's id:primarykey
			System.out.println("saved student. Generated id: "+ studentId);
			
			
			// now get a new session
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve the student based on the id: primary key
			
			System.out.println("\n Getting student with id: "+ studentId);
			
			Student myStudent = session.get(Student.class,studentId);
			
			System.out.println("Get complete: "+ myStudent);
			
			System.out.println("Updating Student....");
			myStudent.setFirstName("Scooby");
			
			// commit the transaction
			
			session.getTransaction().commit();
			
			// NEW CODE
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// update email for all students
			System.out.println("Update email for all students");
			
			session.createQuery("update Student set email='foo@gmail.com'")
			       .executeUpdate();
			
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}finally
		{
			factory.close();
		}
	}
}

