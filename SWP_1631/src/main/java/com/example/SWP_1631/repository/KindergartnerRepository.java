package com.example.SWP_1631.repository;

import com.example.SWP_1631.entity.Kindergartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KindergartnerRepository extends JpaRepository<Kindergartner, Integer> {
}
