package edu.icet.ecom.Repository;

import edu.icet.ecom.Model.Entity.candidater_entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface candidate_repository extends JpaRepository<candidater_entity, Long> {
    Optional<candidater_entity> findByEmail(String email);
}