package com.furkan.karincaa.model.entity;


import com.furkan.karincaa.model.enums.JobType;
import com.furkan.karincaa.model.enums.PostStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {}, callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "job_posts")
public class JobPost extends Base {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "starting_price", nullable = false)
    @Min(value = 0, message = "Starting Price shouldn't be less than 0")
    private double startingPrice;

    @Column(name = "job_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @Column(name = "post_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

}
