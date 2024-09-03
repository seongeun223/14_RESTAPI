package com.ohgiraffers.restapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "test_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;
}
