package com.orazov.msbtrust.service;

import com.orazov.msbtrust.entity.Project;
import com.orazov.msbtrust.repository.EmployeeRepository;
import com.orazov.msbtrust.repository.ProjectRepository;
import com.orazov.msbtrust.repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final MyUserRepository userRepository;

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }
    public Optional<Project> findProjectByName(String name) {
        return projectRepository.findByName(name);
    }
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }
    public Project updateProject(Project project){
        return projectRepository.save(project);
    }
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

//    public void addUser(MyUser user){
//        userRepository.save(user);
//    }

}
