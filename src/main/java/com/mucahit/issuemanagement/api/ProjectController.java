package com.mucahit.issuemanagement.api;

import com.mucahit.issuemanagement.dto.ProjectDto;
import com.mucahit.issuemanagement.service.impl.ProjectServiceImpl;
import com.mucahit.issuemanagement.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiPaths.ProjectCtrl.CTRL)
@Api(value = ApiPaths.ProjectCtrl.CTRL,description = "Project APIs")
@Slf4j
public class ProjectController {
    private final ProjectServiceImpl projectServiceImpl;

    public ProjectController(ProjectServiceImpl projectServiceImpl) {
        this.projectServiceImpl = projectServiceImpl;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation",response = ProjectDto.class)
    public ResponseEntity<ProjectDto> getById(@PathVariable("id") Long id){
        log.info("ProjectController-> GetById "+id);
        log.debug("ProjectController-> GetById -> PARAM "+id);
        ProjectDto projectDto =projectServiceImpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    @ApiOperation(value = "Create Project Operation",response = ProjectDto.class)
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody  ProjectDto project){
       return ResponseEntity.ok(projectServiceImpl.save(project));
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Project Operation",response = ProjectDto.class)
    public ResponseEntity<ProjectDto> updateProject(@PathVariable("id") Long id, @Valid @RequestBody  ProjectDto project){
    return ResponseEntity.ok(projectServiceImpl.update(id,project));
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Project Operation",response = Boolean.class)
    public ResponseEntity<Boolean> deleteProject(@PathVariable(value = "id", required = true) Long id){
            return ResponseEntity.ok(projectServiceImpl.delete(id));
    }


}
