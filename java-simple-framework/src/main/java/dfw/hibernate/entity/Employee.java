package dfw.hibernate.entity;

import java.io.Serializable;


/**
 * @moudle: Employee 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年12月21日 下午8:24:46
 *
 */
public class Employee implements Serializable{
	private int id;
	private String firstName;
	private String lastName;
	private int salary;
	
	public Employee() {}
	public Employee(String fname, String lname, int salary) {
		this.firstName = fname;
		this.lastName = lname;
		this.salary = salary;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=").append(id).append(", ");
		if (firstName != null)
			builder.append("firstName=").append(firstName).append(", ");
		if (lastName != null)
			builder.append("lastName=").append(lastName).append(", ");
		builder.append("salary=").append(salary).append("]");
		return builder.toString();
	}
	
	
}
