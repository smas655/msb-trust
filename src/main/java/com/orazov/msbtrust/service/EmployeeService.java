package com.orazov.msbtrust.service;

import com.orazov.msbtrust.entity.Employee;
import com.orazov.msbtrust.entity.Project;
import com.orazov.msbtrust.repository.EmployeeRepository;
import com.orazov.msbtrust.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long id){
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User by id: " + id + "not found"));
    }

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteEmployeeById(id);
    }

    public void assignEmployeeToProject(Long employeeId, Long projectId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found with id: " + employeeId));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));

        project.getEmployees().add(employee);
        projectRepository.save(project);
    }
}
