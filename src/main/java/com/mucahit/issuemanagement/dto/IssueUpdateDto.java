package com.mucahit.issuemanagement.dto;

import com.mucahit.issuemanagement.entity.IssueStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class   IssueUpdateDto{
    @ApiModelProperty(value = "Issue ID")
    private Long id;
    @ApiModelProperty(value = "Description of Issue",required = true)
    private String description;
    @ApiModelProperty(value = "Details of Issue",required = true)
    private String details;
    @ApiModelProperty(value = "Date of Issue",required = true)
    private Date date;
    @ApiModelProperty(value = "Status of Issue",required = true)
    private IssueStatus issueStatus;
    @ApiModelProperty(value = "User of Issue",required = true)
    private Long assignee_id;
    @ApiModelProperty(value = "Project of Issue",required = true)
    private Long project_id;
}
