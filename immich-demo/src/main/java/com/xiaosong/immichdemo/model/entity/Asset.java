package com.xiaosong.immichdemo.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assets")
public class Asset {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "ownerId", nullable = false, unique = true)
    private UUID  ownerId;

    @Column(name = "originalPath", nullable = false)
    private String originalPath;

}
