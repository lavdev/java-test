package br.com.lav.java.test.backend.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Employee class
 * @author laverson
 *
 */
public class Employee implements Serializable{

	//  it means that once count variable initialized it cannot be changed
	private static final AtomicInteger count = new AtomicInteger(0); 
	private final int id;
	
	private static final long serialVersionUID = 1L;
	
	// employee's name
	private String name;
	
	// employee's salary
	private String salary;
	
	// employee's manager
	private String manager;
	
	// employee's gcm
	private String gcm;
	
	// employee's project
	private List<Project> project;
	
	// employee's role
	private String role;
	
	// employee's skill
	private List<Skill> skill;
	
	// employee's certification
	private List<String> certification;

	/**
	 * Default constructor
	 */
	public Employee(){
        this.id = count.getAndIncrement();
	}
	/***
	 * Class constructor
	 * @param name
	 * @param role
	 * @param salary
	 * @param manager
	 * @param gcm
	 * @param project
	 * @param skill
	 * @param certification
	 */
	public Employee(String name, String role, String salary, 
			String manager, String gcm, List<Project> project, List<Skill> skill, 
						List<String > certification ) {
		this.id = count.getAndIncrement();
		this.name = name;
		this.role = role;
		this.salary = salary;
		this.manager = manager;
		this.gcm = gcm;
		this.project = project;
		this.skill = skill;
		this.certification = certification;
	}

	/***
	 * Get id
	 * @return Long
 	 */
	public int getId() {
		return id;
	}

	/***
	 * Get name
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/***
	 * Set name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get salary
	 * @return Long
	 */
	public String getSalary() {
		return salary;
	}
	
	/***
	 * Set salary
	 * @param salary
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	/***
	 * Get employee's manager
	 * @return manager
	 */
	public String getManager() {
		return manager;
	}

	/***
	 * Set employee's manager
	 * @param manager
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}

	/***
	 * Get gcm
	 * @return String
	 */
	public String getGcm() {
		return gcm;
	}
	
	/***
	 * Set gcm
	 * @param gcm
	 */
	public void setGcm(String gcm) {
		this.gcm = gcm;
	}
	
	/***
	 * Get project list
	 * @return List<Project>
	 */
	public List<Project> getProject() {
		return project;
	}
	
	/***
	 * Set project list
	 * @param project
	 */
	public void setProject(List<Project> project) {
		this.project = project;
	}
	
	/***
	 * Get role list
	 * @return List<Role>
	 */
	public String getRole() {
		return role;
	}
	
	/***
	 * Set role list
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	/***
	 * Get skill list
	 * @return List<Skill>
	 */
	public List<Skill> getSkill() {
		return skill;
	}
	
	/***
	 * Set skill list
	 * @param skill
	 */
	public void setSkill(List<Skill> skill) {
		this.skill = skill;
	}
	
	/***
	 * Get certification list
	 * @return
	 */
	public List<String> getCertification() {
		return certification;
	}
	
	/**
	 * Set certification list
	 * @param certification
	 */
	public void setCertification(List<String> certification) {
		this.certification = certification;
	}

    @Override
	public String toString() {
		return "Employee [name=" + name + "]";
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
