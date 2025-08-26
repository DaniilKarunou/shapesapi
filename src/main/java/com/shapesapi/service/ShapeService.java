package com.shapesapi.service;

import com.shapesapi.dto.ShapeRequest;
import com.shapesapi.dto.ShapeResponse;
import com.shapesapi.entity.ShapeEntity;
import com.shapesapi.exception.ShapeNotFoundException;
import com.shapesapi.handler.ShapeHandler;
import com.shapesapi.repository.ShapeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShapeService {

    private final ShapeRepository repository;
    private final Map<String, ShapeHandler> handlers;

    public ShapeService(ShapeRepository repository, List<ShapeHandler> handlerList) {
        this.repository = repository;
        this.handlers = handlerList.stream()
                .collect(Collectors.toMap(h -> h.getShapeType().toUpperCase(), h -> h));
    }

    public ShapeResponse addShape(ShapeRequest request) {
        ShapeHandler handler = handlers.get(request.type().toUpperCase());
        if (handler == null) {
            throw new ShapeNotFoundException("Unknown shape type: " + request.type());
        }
        ShapeEntity entity = handler.handle(request);
        ShapeEntity saved = repository.save(entity);
        return new ShapeResponse(saved.getId(), saved.getType(), saved.getParameters());
    }

    public List<ShapeResponse> getShapesByType(String type) {
        return repository.findAllByType(type.toUpperCase()).stream()
                .map(e -> new ShapeResponse(e.getId(), e.getType(), e.getParameters()))
                .toList();
    }
}
