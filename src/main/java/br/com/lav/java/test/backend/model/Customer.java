package br.com.lav.java.test.backend.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * Customer class
 * @author laverson
 */
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//  it means that once count variable initialized it cannot be changed
	private static final AtomicInteger count = new AtomicInteger(0); 
	private final int id;
	
	private String name;

    /***
     * Customer constructor
     * @param name
     */
	public Customer(String name) {
		this.id = count.getAndIncrement();
		this.name = name;
	}

    /***
     * Get Id
     * @return id
     */
	public int getId() {
		return this.id;
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
		return "Customer [name=" + name + "]";
	}

}
