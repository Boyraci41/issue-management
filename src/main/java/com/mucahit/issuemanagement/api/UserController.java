package com.mucahit.issuemanagement.api;

import com.mucahit.issuemanagement.dto.UserDto;
import com.mucahit.issuemanagement.service.impl.UserServiceImpl;
import com.mucahit.issuemanagement.util.ApiPaths;
import com.mucahit.issuemanagement.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@Api(value = ApiPaths.UserCtrl.CTRL,description = "User APIs")
@CrossOrigin
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get By Pagination Operation", response = UserDto.class)
    public ResponseEntity<TPage<UserDto>> getAllByPagination(Pageable pageable) {
        TPage<UserDto> data = userServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping()
    @ApiOperation(value = "Get All By Operation", response = UserDto.class)
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> data = userServiceImpl.getAll();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation", response = UserDto.class)
    public ResponseEntity<UserDto> getById(@PathVariable(value = "id", required = true) Long id) {
        UserDto user = userServiceImpl.getById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @ApiOperation(value = "Create Operation", response = UserDto.class)
    public ResponseEntity<UserDto> createProject(@Valid @RequestBody UserDto user) {
        return ResponseEntity.ok(userServiceImpl.save(user));
    }
}
