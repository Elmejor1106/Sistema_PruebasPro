package com.clubes.clubes.repository;

import com.clubes.clubes.entidades.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}