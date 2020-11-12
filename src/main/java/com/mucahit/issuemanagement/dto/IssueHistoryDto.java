package com.mucahit.issuemanagement.dto;

import com.mucahit.issuemanagement.dto.IssueDto;
import com.mucahit.issuemanagement.dto.UserDto;
import com.mucahit.issuemanagement.entity.IssueStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class IssueHistoryDto {
    private Long id;
    @ApiModelProperty(value = "Issue of IssueHistory",required = true)
    private IssueDto issue;
    @ApiModelProperty(value = "Description of IssueHistory",required = true)
    private String description;
    @ApiModelProperty(value = "Date of IssueHistory",required = true)
    private Date date;
    @ApiModelProperty(value = "Status of IssueHistory",required = true)
    private IssueStatus issueStatus;
    @ApiModelProperty(value = "Details of IssueHistory",required = true)
    private String details;
    @ApiModelProperty(value = "User of IssueHistory",required = true)
    private UserDto assignee;

}
