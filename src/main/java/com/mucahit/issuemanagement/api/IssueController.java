package com.mucahit.issuemanagement.api;


import com.mucahit.issuemanagement.dto.IssueDto;
import com.mucahit.issuemanagement.dto.ProjectDto;
import com.mucahit.issuemanagement.service.impl.IssueServiceImpl;

import com.mucahit.issuemanagement.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Api(value = ApiPaths.IssueCtrl.CTRL,description = "Issue APIs")
public class IssueController {

    private final IssueServiceImpl issueServiceImpl;

    public IssueController(IssueServiceImpl issueServiceImpl) {
        this.issueServiceImpl = issueServiceImpl;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation",response = ProjectDto.class)
    public ResponseEntity<IssueDto> getById(@PathVariable("id") Long id){

        IssueDto issueDto =issueServiceImpl.getById(id);
        return ResponseEntity.ok(issueDto);
    }

    @PostMapping
    @ApiOperation(value = "Create Operation",response = ProjectDto.class)
    public ResponseEntity<IssueDto> createIssue(@Valid @RequestBody IssueDto issue){
        return ResponseEntity.ok(issueServiceImpl.save(issue));
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation",response = ProjectDto.class)
    public ResponseEntity<IssueDto> updateIssue(@PathVariable("id") Long id, @Valid @RequestBody  IssueDto issue){
        return  ResponseEntity.ok(issueServiceImpl.update(id,issue));
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation",response = ProjectDto.class)
    public ResponseEntity<Boolean> deleteIssue(@PathVariable(value = "id", required = true) Long id){
        return ResponseEntity.ok(issueServiceImpl.delete(id));
    }
}
