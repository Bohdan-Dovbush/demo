package com.example.demo.repository.interfaces;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface Repo<T, ID> extends PagingAndSortingRepository<T, ID> {
}
