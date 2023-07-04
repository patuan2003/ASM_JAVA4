package com.example.tuanpaph26902_assignment.service;

import java.util.List;
import java.util.UUID;

public interface ServiceRequest<T, U> {

    List<T> getAll();

    T getOne(UUID id);

    Boolean save(U request);

    Boolean update(U request);

    Boolean delete(T t);

    T getByMa(String ma);

    String validate(U u);
}
