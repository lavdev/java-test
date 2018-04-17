package br.com.lav.java.test.backend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.lav.java.test.backend.model.Employee;
import br.com.lav.java.test.backend.model.Project;
import br.com.lav.java.test.backend.model.Skill;
import br.com.lav.java.test.util.Util;

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
	
	// the access must be by get instance method.	
	public StaticData() {}

	/***
	 * Populate static data based on available JSON file, it will be trigger
	 * by Spring on starting service.
	 * 
	 * @return boolean
	 */
	@Autowired
	public void populate() {
		JsonNode jsonNode = Util.getInstance().createJsonObjectFromFile("employees.json");
		// collect individual employee's certification
        ArrayList<String> cert;
        // collect individual employee's skill
        ArrayList<Skill> skillArrayList;
        // collect individual employee's project
        ArrayList<Project> projectArrayList;
		if(jsonNode != null) {
            for (JsonNode node : jsonNode) {
                // added employee's project
                projectArrayList = new ArrayList<>();
                if (node.has("projects")) {
                    JsonNode p = node.get("projects");
                    for (JsonNode d : p) {
                        Project project = new Project(d.get("name").asText(), d.get("customer").asText(),
                                d.get("valueOfProject").asText(), d.get("dtBegin").asText(), d.get("dtEnd").asText());
                        // just added to global project collection ( to filter feature )
                        if(projects.isEmpty()) {
                        	LOGGER.info("Creting project database ... ");
                        }
                        if(!projects.contains(project)) {
                            projects.add(project);
                        }
                        projectArrayList.add(project);
                    }
                }
                // added employee's skill
                skillArrayList = new ArrayList<>();
                if (node.has("skills")) {
                    for (JsonNode s : node.get("skills")) {
                        Skill x = new Skill(s.textValue());
                        // just added to global skill collection (to filter feature)
                        if(skills.isEmpty()) {
                        	LOGGER.info("Creting skill database ... ");
                        }
                        if(!skills.contains(x)){
                            skills.add(x);
                        }
                        skillArrayList.add(x);
                    }
                }

                // added employee's certification
                cert = new ArrayList<>();
                if (node.has("certification")) {
                    JsonNode p = node.get("certification");
                    for (JsonNode d : p) {
                        cert.add(d.asText());
                    }
                }

                // added employee
                if(employees.isEmpty()){
                	LOGGER.info("Creting employee database ... ");
                }
                
                employees.add(new Employee(node.get("name").asText(),
                        node.get("role").asText(),
                        node.get("salary").asText(),
                        node.get("manager").asText(),
                        node.get("gcm").asText(),
                        projectArrayList,
                        skillArrayList,
                        cert));
            }
        }
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
