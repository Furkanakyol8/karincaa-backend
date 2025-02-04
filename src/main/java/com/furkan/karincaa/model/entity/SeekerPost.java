package com.furkan.karincaa.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {}, callSuper = true)
@Entity
@Table(name = "seeker_posts")
@PrimaryKeyJoinColumn(name = "id")
public class SeekerPost extends JobPost {
}
