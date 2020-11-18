package com.mucahit.issuemanagement.service;

import com.mucahit.issuemanagement.dto.IssueDetailDto;
import com.mucahit.issuemanagement.dto.IssueDto;
import com.mucahit.issuemanagement.dto.IssueUpdateDto;
import com.mucahit.issuemanagement.dto.ProjectDto;
import com.mucahit.issuemanagement.entity.Issue;
import com.mucahit.issuemanagement.entity.IssueHistory;
import com.mucahit.issuemanagement.util.TPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;


public interface IssueService {
    IssueDto save(IssueDto issue);

    IssueDto getById(Long id);

    TPage<IssueDto> getAllPageable(Pageable pageable);

    Boolean delete(Long id);

    IssueDetailDto update(Long id, IssueUpdateDto issue);

    List<IssueDto> getAll();
}
