package com.shapesapi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shapes")
public class ShapeEntity extends BaseEntity {

    private String type;

    @ElementCollection
    @Column(name = "parameter")
    @CollectionTable(name = "shape_parameters", joinColumns = @JoinColumn(name = "shape_id"))
    private List<Double> parameters;
}
