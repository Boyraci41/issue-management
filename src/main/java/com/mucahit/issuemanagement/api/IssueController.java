package com.mucahit.issuemanagement.api;


import com.mucahit.issuemanagement.dto.IssueDetailDto;
import com.mucahit.issuemanagement.dto.IssueDto;
import com.mucahit.issuemanagement.dto.IssueUpdateDto;
import com.mucahit.issuemanagement.dto.ProjectDto;
import com.mucahit.issuemanagement.entity.IssueStatus;
import com.mucahit.issuemanagement.service.impl.IssueServiceImpl;

import com.mucahit.issuemanagement.util.ApiPaths;
import com.mucahit.issuemanagement.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Api(value = ApiPaths.IssueCtrl.CTRL,description = "Issue APIs")
@CrossOrigin
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
    @GetMapping("/pagination")
    @ApiOperation(value = "Get By Pagination Operation", response = ProjectDto.class)
    public ResponseEntity<TPage<IssueDto>> getAllByPagination(Pageable pageable) {
        TPage<IssueDto> data = issueServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<IssueDetailDto> getByWithDetails(@PathVariable(value = "id",required = true) Long id){
        IssueDetailDto detailDto = issueServiceImpl.getByWithDetails(id);
        return ResponseEntity.ok(detailDto);
    }


    @PostMapping
    @ApiOperation(value = "Create Operation",response = IssueDto.class)
    public ResponseEntity<IssueDto> createIssue(@Valid @RequestBody IssueDto issue){
        return ResponseEntity.ok(issueServiceImpl.save(issue));
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation",response = IssueDto.class)
    public ResponseEntity<IssueDetailDto> updateIssue(@PathVariable("id") Long id, @Valid @RequestBody IssueUpdateDto issue){

        return  ResponseEntity.ok(issueServiceImpl.update(id,issue));
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation",response = IssueDto.class)
    public ResponseEntity<Boolean> deleteIssue(@PathVariable(value = "id", required = true) Long id){
        return ResponseEntity.ok(issueServiceImpl.delete(id));
    }
    /**
    @GetMapping
    @ApiOperation(value = "Get All Operation", response = IssueDto.class)
    public ResponseEntity<List<IssueDto>> getAll() {
        List<IssueDto> data = issueServiceImpl.getAll();
        return ResponseEntity.ok(data);
    }
    */
    @GetMapping("/statuses")
    @ApiOperation(value = "Get All Issues StatusOperation", response = String.class)
    public ResponseEntity<List<IssueStatus>> getAllIssueStatus() {
        return ResponseEntity.ok(Arrays.asList(IssueStatus.values()));
    }
}
