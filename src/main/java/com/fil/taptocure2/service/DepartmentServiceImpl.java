package com.fil.taptocure2.service;

import com.fil.taptocure2.model.Department;
import com.fil.taptocure2.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    
    @Override
    public Optional<Department> getDepartmentById(long departmentId) {
        return departmentRepository.findById(departmentId);
    }
    
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }
    
    @Override
    public void deleteDepartment(long departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
