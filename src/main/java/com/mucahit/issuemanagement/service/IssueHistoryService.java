package com.mucahit.issuemanagement.service;

import com.mucahit.issuemanagement.dto.IssueDto;
import com.mucahit.issuemanagement.dto.IssueHistoryDto;
import com.mucahit.issuemanagement.entity.Issue;
import com.mucahit.issuemanagement.entity.IssueHistory;
import com.mucahit.issuemanagement.util.TPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IssueHistoryService {
    IssueHistoryDto save(IssueHistoryDto issue);

    IssueHistoryDto getById(Long id);

    List<IssueHistoryDto> getByIssueId(Long id);


    TPage<IssueHistoryDto> getAllPageable(Pageable pageable);

    Boolean delete(IssueHistoryDto issueHistory);

    void addHistory(Long id, Issue issue);
}
