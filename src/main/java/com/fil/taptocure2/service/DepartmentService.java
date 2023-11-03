package com.fil.taptocure2.service;

import com.fil.taptocure2.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    public List<Department> getAllDepartments() ;

    public Optional<Department> getDepartmentById(long departmentId);
    
    public Department saveDepartment(Department department);

    public void deleteDepartment(long departmentId) ;
}
