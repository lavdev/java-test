package br.com.lav.java.test.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lav.java.test.backend.model.Employee;
import br.com.lav.java.test.backend.model.Skill;
import br.com.lav.java.test.backend.service.StaticData;

/***
 * Controller class, EndPoint definition
 * @author laverson
 *
 */
@RestController
@EnableAutoConfiguration
public class JavaTestController {

    /***
     * Default endpoint
     * @return String
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<String>> root() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /***
     * Skill list endpoint
     * @return Skill[] collection
     */
    @RequestMapping(value = "/skill", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Skill>> skillList() {
        return new ResponseEntity<>(StaticData.getInstance().getSkillList(), HttpStatus.OK);
    }

    /***
     * Employee list endpoint
     * @return Employee[] collection
     */
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Employee>> employeeList() {
        return new ResponseEntity<>(StaticData.getInstance().getEmployeeList(), HttpStatus.OK);
    }

    /***
     * Employee by Skill list endpoint
     * @return Employee[] collection
     */
    @RequestMapping(value = "/employee/skill", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Employee>> employeeListBySkill(@RequestParam("filter") String filter) {
        return new ResponseEntity<>(StaticData.getInstance().getEmployeeBySkill(filter), HttpStatus.OK);
    }
}
