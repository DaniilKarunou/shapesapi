package com.shapesapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "shapes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShapeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @ElementCollection
    private List<Double> parameters;
}
