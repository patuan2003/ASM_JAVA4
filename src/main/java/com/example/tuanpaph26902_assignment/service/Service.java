package com.example.tuanpaph26902_assignment.service;

import java.util.List;
import java.util.UUID;

public interface Service<T> {

    List<T> getAll();

    T getOne(UUID id);

    Boolean add(T t);

    Boolean update(T t);

    Boolean delete(T t);

    T getByMa(String ma);

    String validate(T t);

}
