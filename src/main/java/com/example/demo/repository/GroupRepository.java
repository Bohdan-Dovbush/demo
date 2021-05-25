package com.example.demo.repository;

import com.example.demo.entity.user.Group;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {

    Group findByCode(final String code);
}
