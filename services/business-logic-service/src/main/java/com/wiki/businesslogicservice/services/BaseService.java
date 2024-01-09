package com.wiki.businesslogicservice.services;


import org.springframework.data.domain.Page;


public interface BaseService<T> {

    public Page<T> findAll(int page, int size);

    public T create(T objeto);

    public T findById(Number id);

    public T update(T objeto);

    public void delete(T objeto);
}
