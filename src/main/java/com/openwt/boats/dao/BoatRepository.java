package com.openwt.boats.dao;

import com.openwt.boats.entity.Boat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoatRepository extends JpaRepository<Boat, Long> {
    List<Boat> findBoatsById(Long id);
}
