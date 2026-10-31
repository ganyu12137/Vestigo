package org.example.vestigo.demo;
// 数据库实体类


import jakarta.persistence.*;

@Entity
@Table(name = "test_user")
public class TestUser {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;
}
