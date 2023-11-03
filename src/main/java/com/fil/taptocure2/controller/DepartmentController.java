//package com.fil.taptocure.controller;

//
//import com.fil.taptocure.model.Department;
//import com.fil.taptocure.service.DepartmentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/departments")
//public class DepartmentController {
//
//    @Autowired
//    private DepartmentService departmentService;
//
//    @GetMapping("/")
//    public List<Department> getAllDepartments() {
//        return departmentService.getAllDepartments();
//    }
//
//    @GetMapping("/{id}")
//    public Department getDepartmentById(@PathVariable long id) {
//        return departmentService.getDepartmentById(id).orElse(null);
//    }
//
//    @PostMapping("/")
//    public Department saveDepartment(@RequestBody Department department) {
//        return departmentService.saveDepartment(department);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteDepartment(@PathVariable long id) {
//        departmentService.deleteDepartment(id);
//    }
//}
//
package com.fil.taptocure2.controller;

import com.fil.taptocure2.model.Department;
import com.fil.taptocure2.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public String listDepartments(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "department-list";
    }

    @GetMapping("/new")
    public String createDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "department-create";
    }

    @PostMapping("/new")
    public String createDepartment(@ModelAttribute Department department) {
        departmentService.saveDepartment(department);
        return "redirect:/departments/";
    }
    @GetMapping("/edit/{id}")
    public String editDepartmentForm(@PathVariable long id, Model model) {
        Department department = departmentService.getDepartmentById(id).orElse(null);
        if (department != null) {
            model.addAttribute("department", department);
            return "department-edit";
        }
        return "redirect:/departments/";
    }
    @PostMapping("/edit/{id}")
    public String editDepartment(@PathVariable long id, @ModelAttribute Department updatedDepartment) {
        Department department = departmentService.getDepartmentById(id).orElse(null);
        if (department != null) {
        	
            department.setDepartmentName(updatedDepartment.getDepartmentName());
            department.setDescription(updatedDepartment.getDescription());
             //model.addAttribute("department", department);
            departmentService.saveDepartment(department);
        }
        return "redirect:/departments/";
    }
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments/";
    }
}
