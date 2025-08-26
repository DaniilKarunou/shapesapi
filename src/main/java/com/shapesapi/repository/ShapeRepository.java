package com.shapesapi.repository;

import com.shapesapi.entity.ShapeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShapeRepository extends JpaRepository<ShapeEntity, Long> {
    List<ShapeEntity> findAllByType(String type);
}
