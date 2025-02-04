package com.furkan.karincaa.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
@Entity
@EqualsAndHashCode(of = {}, callSuper = true)
public class Comment extends Base{

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "rating")
    @Min(value = 0, message = "rating point shouldn't be less than 0")
    @Max(value = 5, message = "rating point shouldn't be more than 5")
    private double rating;

    @ManyToOne
    @JoinColumn(name = "provider_post_id")
    private ProviderPost providerPost;

}
