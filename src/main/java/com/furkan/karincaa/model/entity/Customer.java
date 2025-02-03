package com.furkan.karincaa.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.io.File;

@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {}, callSuper = true)
@SQLRestriction("deleted_at is null")
@SQLDelete(sql = "UPDATE users SET deleted_at = now() WHERE id = ?")

public class Customer extends User {

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "city", nullable = false)
    private String city;
}
