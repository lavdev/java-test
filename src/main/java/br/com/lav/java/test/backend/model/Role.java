package br.com.lav.java.test.backend.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * Role class
 * @author laverson
 *
 */
public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//  it means that once count variable initialized it cannot be changed
	private static final AtomicInteger count = new AtomicInteger(0); 
	private final int id;
	
	private String name;

    /***
     * Class constructor
     * @param name
     */
	public Role(String name) {
		this.id = count.getAndIncrement();
		this.name = name;
	}

    /***
     * Get id
     * @return id
     */
	public long getId() {
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

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
