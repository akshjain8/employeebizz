package com.webapp.spirngbootthymeleafcrudapp.controller;

import com.webapp.spirngbootthymeleafcrudapp.model.Employee;
import com.webapp.spirngbootthymeleafcrudapp.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model){
       return findPaginated(1,"lastName","asc", "" ,model);
    }
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id")long id , Model model){
Employee employee = employeeService.getEmployeebyId(id);
model.addAttribute("employee",employee);
return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id){
       employeeService.deleteEmployeebyId(id);
       return ("redirect:/");

    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam(value = "sortField") String sortField ,
                                @RequestParam(value = "sortDirection") String sortDirection ,
                                @RequestParam(value = "Keyword")String Keyword,
                                Model model){
       int pageSize = 3;
       // 3 records on one page other records go to next page.
       //page size = number of records displayed on one page.
        Page<Employee> page = employeeService.findPaginated(pageNo,pageSize,sortField,sortDirection,Keyword);
        List<Employee>  employeeList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("Keyword",Keyword);

        model.addAttribute("listEmployees", employeeList);
        return "index";
    }


}
