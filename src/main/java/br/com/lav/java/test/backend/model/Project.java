package br.com.lav.java.test.backend.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * Project class
 * @author laverson
 *
 */
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	//  it means that once count variable initialized it cannot be changed
	private static final AtomicInteger count = new AtomicInteger(0); 
	private final int id;
	
	private String name;
	private String valueOfProject;
	private String dateBegin;
	private String dateEnd;
	
	private String customer;

    /***
     * Project constructor
     * @param name
     * @param customer
     * @param valueOfProject
     * @param dateBegin
     * @param dateEnd
     */
	public Project(String name, 
			String customer, String valueOfProject, String dateBegin, String dateEnd) {
		this.id = count.getAndIncrement();
		this.name = name;
		this.customer = customer;
		this.valueOfProject = valueOfProject;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
	}

    /***
     * Get Id
     * @return id
     */
	public int getId() {
		return id;
	}

    /***
     * Get name
     * @return name
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

    /***
     * Get Customer
     * @return customer
     */
	public String getCustomer() {
		return customer;
	}

    /***
     * Set Customer
     * @param customer
     */
	public void setCustomer(String customer) {
		this.customer = customer;
	}

    /***
     * Get begin date
     * @return dateBegin
     */
	public String getDateBegin() {
		return dateBegin;
	}

    /***
     * Set begin date
     * @param dateBegin
     */
	public void setDateBegin(String dateBegin) {
		this.dateBegin = dateBegin;
	}

    /***
     * Get end date
     * @return
     */
	public String getDateEnd() {
		return dateEnd;
	}

    /***
     * Set date end
     * @param dateEnd
     */
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

    /***
     * Get project's value
     * @return valueOfProject
     */
	public String getValueOfProject() {
		return valueOfProject;
	}

    /***
     * Set project's value
     * @param valueOfProject
     */
	public void setValueOfProject(String valueOfProject) {
		this.valueOfProject = valueOfProject;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                '}';
    }
}
