    package com.orazov.msbtrust.entity;

    import jakarta.persistence.*;
    import lombok.*;

    import java.util.HashSet;
    import java.util.Set;

    @Data
    @Builder
    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    public class Project {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String description;
        private String status;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                name = "employee_project",
                joinColumns = @JoinColumn(name = "project_id"),
                inverseJoinColumns = @JoinColumn(name = "employee_id")
        )
        private Set<Employee> employees = new HashSet<>();
    }
