package edu.icet.ecom.Repository;

import edu.icet.ecom.Model.Entity.booking_entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface booking_repository extends JpaRepository<booking_entity, Long> {

    @Query("SELECT b FROM booking_entity b " +
            "JOIN FETCH b.interviewSlot " +
            "JOIN FETCH b.interviewer " +
            "JOIN FETCH b.candidate")
    List<booking_entity> findAllWithDetails();

    boolean existsByInterviewSlotId(Long slotId);
}