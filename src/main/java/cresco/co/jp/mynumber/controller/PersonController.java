package cresco.co.jp.mynumber.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cresco.co.jp.mynumber.model.MyNumberRegistration;
import cresco.co.jp.mynumber.model.Person;
import cresco.co.jp.mynumber.service.PersonService;

@Controller
public class PersonController {

	private PersonService personService;

	private MyNumberRegistration myNumberRegistration;

	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(PersonService ps){
		this.personService = ps;
	}

	@RequestMapping(value = "/s1", method = RequestMethod.GET)
	public String showS1(Model model) {
			model.addAttribute("myNumberRegistration", new MyNumberRegistration());
		return "01_my_number_registration";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchEmployeeS1(@Valid MyNumberRegistration emp, BindingResult bindingResult, Model model) {
		if (emp.getEmployeeId() == 123456) {
			myNumberRegistration = new MyNumberRegistration();
			this.myNumberRegistration.setEmployeeName("お名前：Hidashima Eren(First Name Kana Name Kana)");
			this.myNumberRegistration.setEmployeeId(123456);
			model.addAttribute("myNumberRegistration", myNumberRegistration);
		} else {
			myNumberRegistration = new MyNumberRegistration();
		}
		return "01_my_number_registration";
	}

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("listPersons", this.personService.listPersons());
		return "person";
	}

	//For add and update person both
	@RequestMapping(value= "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person p){

		if(p.getId() == 0){
			//new person, add it
			this.personService.addPerson(p);
		}else{
			//existing person, call update
			this.personService.updatePerson(p);
		}

		return "redirect:/persons";

	}

	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){

        this.personService.removePerson(id);
        return "redirect:/persons";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }

}
