package br.com.lav.java.test.backend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lav.java.test.backend.model.Customer;
import br.com.lav.java.test.backend.model.Employee;
import br.com.lav.java.test.backend.model.Project;
import br.com.lav.java.test.backend.model.Role;
import br.com.lav.java.test.backend.model.Skill;

/***
 * Singleton StaticData class, is an in-memory mock DAO that mimics
 * a real-world datasource.
 * 
 * @author Laverson
 *
 */

@Service
public class StaticData {

	private final Logger LOGGER = LoggerFactory.getLogger(StaticData.class);
	
	// skill collection data
	public static final ArrayList<Skill> skills = new ArrayList<>();
	// employee collection data
	public static final ArrayList<Employee> employees = new ArrayList<>();
	// project collection data
	public static final ArrayList<Project> projects = new ArrayList<>();
	// customer collection data
	public static final ArrayList<Customer> customers = new ArrayList<>();
	
    // StaticData instance... 
	private static StaticData instance;

    /***
     * Get StaticData instance class
     * @return instance
     */
	public static StaticData getInstance() {
		if(instance == null) {
			instance = new StaticData();
		}
		return instance;
	}
	
	// the access must be by get instance method.
	protected StaticData() {}

	/***
	 * Populate static data based on available JSON file, it will be trigger
	 * by Spring on starting service.
	 * 
	 * @return boolean
	 */
	@Autowired
	public void populate() {
		
		// populate customer list
		LOGGER.info("Creating Customer...");
		customers.add(new Customer("NET"));
		customers.add(new Customer("Multiplus Fidelidade"));
		
		// populate project list
		LOGGER.info("Creating Project...");
		projects.add(new Project("Portal cadastro NET",
			                       findCustomerByName("NET"),
			                       "1.000.000,00",
			                       "2016-11-05T08:15:30-05:00",
			                       "2017-11-05T08:15:30-05:00"));
		projects.add(new Project("Fulfillment",
								findCustomerByName("Multiplus Fidelidade"),
				                "1.000.000,00",
				                "2015-11-05T08:15:30-05:00",
				                "2018-11-05T08:15:30-05:00"));

		// populate skills list
		LOGGER.info("Creating Skill...");
		skills.add(new Skill("java"));
		skills.add(new Skill("javaee"));
		skills.add(new Skill("graphql"));
		skills.add(new Skill("microservice"));
		skills.add(new Skill("soa"));
		skills.add(new Skill("oracle soa suite"));
		skills.add(new Skill("DevOps"));
		skills.add(new Skill("gestao de pessoas"));
		skills.add(new Skill("PMI"));
		skills.add(new Skill("Scrum"));
		skills.add(new Skill("Agile"));	
		skills.add(new Skill("just test"));

		// populate employee list
		LOGGER.info("Creating Employee...");
		
		// Employee - Start
		List<Project> projectList = new ArrayList<>();
		projectList.add(findProjectByName("Portal cadastro NET"));
		
		List<Skill> skillset= new ArrayList<>();
		skillset.add(findSkillByName("java"));
		skillset.add(new Skill("javaee"));
		skillset.add(new Skill("graphql"));
		skillset.add(new Skill("microservice"));
		skillset.add(new Skill("soa"));

		List<String> certset = new ArrayList<>();
		certset.add("Oracle SOA Suite 11g Essentials - Jun 2015");
		certset.add("Oracle Certified Professional, Java SE 7 Programmer - Aug 2014");
		certset.add("ITILÂ® Foundation Certificate Jun 2013");
		certset.add("Sun Certified Enterprise Architect for the Java Platform Oct 2010");
		certset.add("Sun Certified Associate for Java Platform - Oct 2008");
		certset.add("Sun Certified Business Component Developer for the Java Platform, EE 5 - Nov 2007");
		certset.add("Sun Certified Business Component Developer for the Java Platform, EE 1.3 - Feb 2007");
		certset.add("Sun Certified Web Component Developer for the Java Platform - Oct 2006");
		certset.add("Sun Certified Programmer for the Java Platform - Jun 2006");
		
		employees.add(new Employee("Renato Garcia", 
				new Role("TI Architect"), 
				"2000,00", 
				"Marcelo Ricciardi", 
				"05",
				projectList, skillset, certset));
		
		// Employee - End
		
		// Employee - Start
		projectList = new ArrayList<>();
		projectList.add(findProjectByName("Fulfillment"));
		
		skillset= new ArrayList<>();
		skillset.add(findSkillByName("java"));
		skillset.add(new Skill("javaee"));
		skillset.add(new Skill("DevOps"));
	
		certset = new ArrayList<>();
			certset.add("Sun Certified Programmer for the Java Platform");
			
			employees.add(new Employee("Jose Carlos", 
					new Role("Software Enginner"), 
					"1700,00", 
					"Marcelo Ricciardi", 
					"04",
					projectList, skillset, certset));
			
	    // Employee - End	
			
			
		// Employee - Start
		projectList = new ArrayList<>();
		projectList.add(findProjectByName("Fulfillment"));
		
		skillset= new ArrayList<>();
		skillset.add(findSkillByName("java"));
		skillset.add(new Skill("javaee"));
		
	
		certset = new ArrayList<>();
			certset.add( "Sun Certified Programmer for the Java Platform");
			
			employees.add(new Employee("Gabriel Luz", 
					new Role("Software Enginner"), 
					"9000,00", 
					"Marcelo Ricciardi", 
					"04",
					projectList, skillset, certset));
				
		// Employee - End

		// Employee - Start
		skillset= new ArrayList<>();
		skillset.add(new Skill("gestao de pessoas"));
		skillset.add(new Skill("PMI"));
		skillset.add(new Skill("Scrum"));
		skillset.add(new Skill("Agile"));
		skillset.add(new Skill("DevOps"));
	
		certset = new ArrayList<>();
			certset.add( "Sun Certified Programmer for the Java Platform");
			
			employees.add(new Employee("Marcelo Ricciari", 
					new Role("Manager"), 
					"14000,00", 
					"Douglas", 
					"06",
					null, skillset, certset));
		// Employee - End				
			
	}
	
	/***
	 * Find project by name
	 * @param name
	 * @return Project or null
	 */
	public Project findProjectByName(String name){
		List<Project> filtered =
			    projects
			        .stream()
			        .filter(c -> c.getName().equals(name))
			        .collect(Collectors.toList());
		if(filtered.size() > 0) {
			return filtered.get(0);
		}			
		return null;
	}

	/***
	 * Find skill by name
	 * @param name
	 * @return
	 */
	public Skill findSkillByName(String name){
		List<Skill> filtered =
			    skills
			        .stream()
			        .filter(c -> c.getName().equals(name))
			        .collect(Collectors.toList());
		if(filtered.size() > 0) {
			return filtered.get(0);
		}			
		return null;
	}

	/***
	 * Find customer by name
	 * @param name
	 * @return Customer or null
	 */
	public Customer findCustomerByName(String name){
		List<Customer> filtered =
			    customers
			        .stream()
			        .filter(c -> c.getName().equals(name))
			        .collect(Collectors.toList());
		
		if(filtered.size() > 0) {
			return filtered.get(0);
		}			
		return null;
	}

	/***
	 * Get the Employee list
	 * @return List<Employee>
	 */
	public List<Employee> getEmployeeList(){
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employees) {
            list.add(employee);
        }
        return list;
	}
	
	/***
	 * Get the Skill list
	 * @return
	 */
	public List<Skill> getSkillList(){
        List<Skill> list = new ArrayList<>();
        for (Skill skill : skills) {
            list.add(skill);
        }
        return list;
	}
	
	/***
	 * Get the Customer list
	 * @return List<Customer>
	 */
	public List<Customer> getCustomerlList(){
        List<Customer> list = new ArrayList<>();
        for (Customer customer : customers) {
            list.add(customer);
        }
        return list;
	}
	
	/***
	 * Get the Project list
	 * @return List<Project>
	 */
	public List<Project> getProjectslList(){
        List<Project> list = new ArrayList<>();
        for (Project project : projects) {
            list.add(project);
        }
        return list;
	}

	/**
	 * Get Employee by Skill
	 * @param filter
	 * @return
	 */
	public List<Employee> getEmployeeBySkill(String filter) {
		List<Employee> ret = new ArrayList<>();
		String[] f = filter.split(",");
		// if contains some of skill passed by, it means
		// that employee match the filter
        for (Employee e : employees) {
            for (Skill s : e.getSkill()) {
                if (Arrays.stream(f)
                        .anyMatch(str -> s.getName()
                                .contains(str))) {
                    Employee employee = e;
                    if(!ret.contains(e)) {
                        ret.add(employee);
                    }
                }
            }
        }
        return ret;
	}
}
