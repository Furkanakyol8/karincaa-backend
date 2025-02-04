package com.furkan.karincaa.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {}, callSuper = true)
@Entity
@Table(name = "provider_posts")
@PrimaryKeyJoinColumn(name = "id")
public class ProviderPost extends JobPost {

    @Column(name = "average_rating")
    @Min(value = 0, message = "rating point shouldn't be less than 0")
    @Max(value = 5, message = "rating point shouldn't be more than 5")
    private double avreageRating;

//    @OneToMany(mappedBy = "job_post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<Comment> comment;

}
