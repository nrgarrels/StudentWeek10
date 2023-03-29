package dmacc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.StudentData;
import dmacc.repository.StudentDataRepository;

@Controller
public class WebController {

	@Autowired
	StudentDataRepository repo;
	
	@GetMapping({"/", "viewAll"})
	public String viewAllStudentData(Model model) {
		if(repo.findAll().isEmpty()) {
			
			return addNewStudentData(model);
		}
		model.addAttribute("students", repo.findAll());
		return "results";
	}
	@GetMapping("/inputStudentData")
	public String addNewStudentData(Model model) {
		StudentData c = new StudentData();
				model.addAttribute("newStudentData", c);
				return "input";
		
	}
	@GetMapping("/edit/{id}")
	public String showUpdateStudentData(@PathVariable("id") long id, Model model) {
		StudentData c = repo.findById(id).orElse(null);
		model.addAttribute("newStudentData", c);
		return "input";
		
	}
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		StudentData c = repo.findById(id).orElse(null);
		repo.delete(c);
		return viewAllStudentData(model);
	}

	@PostMapping("/inputStudentData")
	public String addNewStudentData(@ModelAttribute StudentData c, Model model) {
		repo.save(c);
		return viewAllStudentData(model);
	}
	
	@PostMapping("/update/{id}")
	public String reviseStudentData(StudentData c, Model model) {
		repo.save(c);
		return viewAllStudentData(model);
	}
}
