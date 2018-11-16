package com.codegym.hotel.controller;

import com.codegym.hotel.model.Employee;
import com.codegym.hotel.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/")
    public String index(Model model, @ModelAttribute("query") Optional<String> query) {
        if (query.isPresent()) {
            System.out.println("Hello");
            String queryText = query.get();
            Iterable<Employee> employees = employeeService.customFind(queryText);
            model.addAttribute("employees", employees);
            return "index";
        }
        Iterable<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "create";
    }

    @PostMapping("/create")
    public String save(Model model, @Valid @ModelAttribute Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        employeeService.save(employee);
        model.addAttribute("message", "New employee created successfully");
        return "create";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        model.addAttribute("employee", employeeService.findById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String saveEdited(Model model, @Valid @ModelAttribute Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Hello");
            return "edit";
        }
        employeeService.save(employee);
        model.addAttribute("message", "Edited Successfully");
        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") String id, RedirectAttributes redirect) {
        employeeService.deleteById(id);
        redirect.addAttribute("message", "Deleted Successfully");
        return "redirect:/";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("searchText") String query, RedirectAttributes redirect) {
        redirect.addAttribute("query", query);
        return "redirect:/";
    }
}
