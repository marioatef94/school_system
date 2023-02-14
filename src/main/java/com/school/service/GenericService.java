package com.school.service;

import java.util.List;

/******************
 * @param <D> Refer To DTO
 ****************/
public interface GenericService<D> {

    D save(D d);
    D findById(Long id);
    D update(D d);
    List<D> findAll();
    void delete(Long id);
}
