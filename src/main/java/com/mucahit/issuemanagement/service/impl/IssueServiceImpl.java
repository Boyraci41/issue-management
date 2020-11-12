package com.mucahit.issuemanagement.service.impl;

import com.mucahit.issuemanagement.dto.IssueDto;
import com.mucahit.issuemanagement.entity.Issue;
import com.mucahit.issuemanagement.repository.IssueRepository;
import com.mucahit.issuemanagement.service.IssueService;
import com.mucahit.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;

    public IssueServiceImpl(IssueRepository issueRepository,ModelMapper modelMapper) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public IssueDto save(IssueDto issue) {
        if(issue.getDate() == null){
            throw new IllegalArgumentException("Issue Date cannot be null");
        }
        Issue issueDb =  modelMapper.map(issue,Issue.class);
        issueDb = issueRepository.save(issueDb);
        return modelMapper.map(issueDb,IssueDto.class);
    }

    @Override
    public IssueDto getById(Long id) {
        return modelMapper.map(issueRepository.getOne(id),IssueDto.class);
    }

    @Override
    public TPage<IssueDto> getAllPageable(Pageable pageable) {
        Page<Issue> data = issueRepository.findAll(pageable);
        TPage page = new TPage<IssueDto>();
        IssueDto[]  dtos = modelMapper.map(data.getContent(),IssueDto[].class);
         page.setStat(data, Arrays.asList(dtos));

        return page;
    }

    @Override
    public Boolean delete(Long id) {
        issueRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public IssueDto update(Long id, IssueDto issue) {
        return null;
    }
}
