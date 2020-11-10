package com.mucahit.issuemanagement.service;

import com.mucahit.issuemanagement.dto.IssueDto;
import com.mucahit.issuemanagement.entity.Issue;
import com.mucahit.issuemanagement.entity.IssueHistory;
import com.mucahit.issuemanagement.util.TPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface IssueService {
    IssueDto save(IssueDto issue);

    IssueDto getById(Long id);

    TPage<IssueDto> getAllPageable(Pageable pageable);

    Boolean delete(IssueDto issue);

}
