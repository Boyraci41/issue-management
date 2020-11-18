package com.mucahit.issuemanagement.service;

import com.mucahit.issuemanagement.dto.UserDto;
import com.mucahit.issuemanagement.entity.Issue;
import com.mucahit.issuemanagement.entity.User;
import com.mucahit.issuemanagement.util.TPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto save(UserDto user);

    UserDto getById(Long id);

    TPage<UserDto> getAllPageable(Pageable pageable);

    UserDto getByUsername(String username);



}
