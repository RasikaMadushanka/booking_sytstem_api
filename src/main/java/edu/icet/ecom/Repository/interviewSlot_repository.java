package edu.icet.ecom.Repository;

import edu.icet.ecom.Model.Dto.interviewSlot_dto;
import edu.icet.ecom.Model.Entity.interviewSlot_entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface interviewSlot_repository extends JpaRepository<interviewSlot_entity,Long> {
    Optional<interviewSlot_entity> findById(Long id);

}
