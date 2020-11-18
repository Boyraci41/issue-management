package com.mucahit.issuemanagement.service.impl;

import com.mucahit.issuemanagement.dto.*;
import com.mucahit.issuemanagement.entity.*;
import com.mucahit.issuemanagement.repository.IssueRepository;
import com.mucahit.issuemanagement.repository.ProjectRepository;
import com.mucahit.issuemanagement.repository.UserRepository;
import com.mucahit.issuemanagement.service.IssueService;
import com.mucahit.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Arrays;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final IssueHistoryServiceImpl issueHistoryService;

    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper, ProjectRepository projectRepository, UserRepository userRepository, IssueHistoryServiceImpl issueHistoryService) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.issueHistoryService = issueHistoryService;
        this.projectRepository = projectRepository;
    }

    @Override
    public IssueDto save(IssueDto issue) {
        issue.setDate(new Date(System.currentTimeMillis()));
        issue.setIssueStatus(IssueStatus.OPEN);

        Issue issueDb = modelMapper.map(issue, Issue.class);
        issueDb.setProject(projectRepository.getOne(issue.getProject_id()));
        issueDb = issueRepository.save(issueDb);
        return modelMapper.map(issueDb, IssueDto.class);
    }

    @Override
    @Transactional
    public IssueDetailDto update(Long id, IssueUpdateDto issue) {

        Issue issueDb = issueRepository.getOne(id);
        User user = userRepository.getOne(issue.getAssignee_id());
        issueHistoryService.addHistory(id, issueDb);
        issueDb.setAssignee(user);
        issueDb.setDate(issue.getDate());
        issueDb.setDescription(issue.getDescription());
        issueDb.setDetails(issue.getDetails());
        issueDb.setIssueStatus(issue.getIssueStatus());
        issueDb.setProject(projectRepository.getOne(issue.getProject_id()));
        issueRepository.save(issueDb);
        return getByWithDetails(id);
    }

    @Override
    public IssueDto getById(Long id) {
        return modelMapper.map(issueRepository.getOne(id), IssueDto.class);
    }

    public IssueDetailDto getByWithDetails(Long id) {
        Issue issue = issueRepository.getOne(id);
        IssueDetailDto detailDto = modelMapper.map(issue, IssueDetailDto.class);
        List<IssueHistoryDto> issueHistoryDtos = issueHistoryService.getByIssueId(issue.getId());
        detailDto.setIssueHistories(issueHistoryDtos);
        return detailDto;
    }


    @Override
    public TPage<IssueDto> getAllPageable(Pageable pageable) {
        Page<Issue> data = issueRepository.findAll(pageable);
        TPage<IssueDto> page = new TPage<IssueDto>();
        page.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), IssueDto[].class)));

        return page;
    }

    @Override
    public Boolean delete(Long id) {
        issueRepository.deleteById(id);
        return Boolean.TRUE;
    }


    @Override
    public List<IssueDto> getAll() {
        List<Issue> issue = issueRepository.findAll();
        return Arrays.asList(modelMapper.map(issue, IssueDto[].class));
    }
}
