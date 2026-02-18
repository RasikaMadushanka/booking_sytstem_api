package edu.icet.ecom.Repository;

import edu.icet.ecom.Model.Entity.candidater_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface candidate_repository extends JpaRepository<candidater_entity,Long> {
}
