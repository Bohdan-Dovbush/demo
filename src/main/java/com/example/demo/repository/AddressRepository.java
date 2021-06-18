package com.example.demo.repository;

import com.example.demo.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT * FROM address as a, city as c, country as c2", nativeQuery = true)
    List<Address> findAllAddress();
}
