package name.katlog.hibernate;

import name.katlog.hibernate.entity.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

/**
 * @moudle: ManageEmployee 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年12月21日 下午8:35:25
 *
 */
public class ManageEmployee {
	
	
	private static SessionFactory factory;
	public static void main(String[] args) {
		try {
			factory = new Configuration().configure("org/person/dfw/hibernate/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		ManageEmployee ME = new ManageEmployee();
		/* Add few employee records in database */
		Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
		Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
		Integer empID3 = ME.addEmployee("John", "Paul", 10000);
		ME.listEmployees();
		ME.updateEmployee(empID1, 5000);
		ME.deleteEmployee(empID2);
		ME.listEmployees();
	}
	
	public Integer addEmployee(String fname, String lname, int salary) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;
		try {
			tx = session.beginTransaction();
			Employee employee = new Employee(fname, lname, salary);
			employeeID = (Integer) session.save(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeID;
	}

	public void listEmployees() {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			List employees = session.createQuery("FROM Employee").list();
			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
				Employee employee = (Employee) iterator.next();
				System.out.print("First Name: " + employee.getFirstName());
				System.out.print(" Last Name: " + employee.getLastName());
				System.out.println(" Salary: " + employee.getSalary());
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void updateEmployee(Integer EmployeeID, int salary) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			Employee employee = (Employee) session.get(Employee.class, EmployeeID);
			employee.setSalary(salary);
			session.update(employee);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteEmployee(Integer EmployeeID) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			Employee employee = (Employee) session.get(Employee.class, EmployeeID);
			session.delete(employee);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
