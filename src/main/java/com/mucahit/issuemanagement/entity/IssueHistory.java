package com.mucahit.issuemanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "issue_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class IssueHistory extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name ="issue_id" ,unique = true)
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    private Issue issue;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @JoinColumn(name ="assignee_user_id" ,unique = true)
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    private User assignee;

}
