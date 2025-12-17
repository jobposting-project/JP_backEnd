package com.jobposting.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_bookmark",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "job_posting_id"})
        })
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobBookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmarkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id", nullable = false)
    private JobPosting jobPosting;
}
