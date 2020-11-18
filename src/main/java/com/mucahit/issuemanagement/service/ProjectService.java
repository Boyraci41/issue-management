package com.mucahit.issuemanagement.service;

import com.mucahit.issuemanagement.dto.ProjectDto;
import com.mucahit.issuemanagement.entity.Issue;
import com.mucahit.issuemanagement.entity.Project;
import com.mucahit.issuemanagement.util.TPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    ProjectDto save(ProjectDto project);

    ProjectDto getById(Long id);

    ProjectDto getByProjectCode(String projectCode);

    ProjectDto getByProjectCodeContains(String projectCode);

    TPage<ProjectDto> getAllPageable(Pageable pageable);

    Boolean delete(ProjectDto project);
    ProjectDto update(Long id,ProjectDto project);


    List<ProjectDto> getAll();
}
