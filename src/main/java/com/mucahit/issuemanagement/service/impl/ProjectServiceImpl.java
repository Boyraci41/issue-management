package com.mucahit.issuemanagement.service.impl;

import com.mucahit.issuemanagement.dto.IssueDto;
import com.mucahit.issuemanagement.dto.ProjectDto;
import com.mucahit.issuemanagement.entity.Issue;
import com.mucahit.issuemanagement.entity.Project;
import com.mucahit.issuemanagement.repository.ProjectRepository;
import com.mucahit.issuemanagement.service.ProjectService;
import com.mucahit.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository,ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.modelMapper=modelMapper;
    }


    @Override
    public ProjectDto save(ProjectDto project) {
        Project projectCheck = projectRepository.getByProjectCode(project.getProjectCode());

        if(projectCheck!=null){
            throw new IllegalArgumentException("ProjectCode already exist");
        }
        Project p = modelMapper.map(project,Project.class);
        p = projectRepository.save(p);
        project.setId(p.getId());
        return project;
    }

    @Override
    public ProjectDto getById(Long id) {
        Project p = projectRepository.getOne(id);
        return modelMapper.map(p,ProjectDto.class);

    }

    @Override
    public ProjectDto getByProjectCode(String projectCode) {

           Project project = projectRepository.getByProjectCode(projectCode);

        return modelMapper.map(project,ProjectDto.class);
    }

    @Override
    public ProjectDto getByProjectCodeContains(String projectCode) {
        List<Project> project =  projectRepository.getByProjectCodeContains(projectCode);

        return  modelMapper.map(project,ProjectDto.class);
    }

    @Override
    public TPage<ProjectDto> getAllPageable(Pageable pageable) {

        Page<Project> data = projectRepository.findAll(pageable);
        TPage page = new TPage<ProjectDto>();
        ProjectDto[]  dtos = modelMapper.map(data.getContent(),ProjectDto[].class);
        page.setStat(data, Arrays.asList(dtos));

        return page;
    }

    @Override
    public Boolean delete(ProjectDto project) {
        Project p = modelMapper.map(project,Project.class);

        projectRepository.delete(p);
        return Boolean.TRUE;
    }

    public Boolean delete(Long id) {
        projectRepository.deleteById(id);
        return Boolean.TRUE;
    }


    @Override
    public ProjectDto update(Long id, ProjectDto project) {
        Project projectDb = projectRepository.getOne(id);
        if(projectDb==null){
            throw  new IllegalArgumentException("Project Does not Exist ,ID:" + id);
        }

        Project projectCheck = projectRepository.getByProjectCodeAndIdNot(project.getProjectCode(), id);

        if(projectCheck!=null){
            throw new IllegalArgumentException("ProjectCode already exist");
        }
         projectDb.setProjectCode(project.getProjectCode());
        projectDb.setProjectName(project.getProjectName());

        projectRepository.save(projectDb);

        return modelMapper.map(projectDb,ProjectDto.class);

    }
}
