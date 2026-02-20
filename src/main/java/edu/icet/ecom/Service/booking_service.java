package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.booking_dto;
import edu.icet.ecom.Model.Entity.booking_entity;
import edu.icet.ecom.Model.Entity.candidater_entity;
import edu.icet.ecom.Model.Entity.interviewSlot_entity;
import edu.icet.ecom.Model.Entity.interviewer_entity;
import edu.icet.ecom.Repository.booking_repository;
import edu.icet.ecom.Repository.candidate_repository;
import edu.icet.ecom.Repository.interviewSlot_repository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class booking_service {

    private final booking_repository bookingRepository;
    private final interviewSlot_repository slotRepository;
    private final candidate_repository candidateRepository;

    public booking_service(booking_repository bookingRepository,
                           interviewSlot_repository slotRepository,
                           candidate_repository candidateRepository) {
        this.bookingRepository = bookingRepository;
        this.slotRepository = slotRepository;
        this.candidateRepository = candidateRepository;
    }

    public booking_dto createBooking(Long slotId, booking_dto dto) {

        interviewSlot_entity slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slot not found"));

        if (slot.getBooking() != null || slot.getEndTime().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Slot not available!");
        }

        candidater_entity candidate = candidateRepository.findByEmail(dto.getCandidateEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found"));

        interviewer_entity interviewer = slot.getInterviewer();

        slot.setStatus(true);

        booking_entity booking = new booking_entity();
        booking.setInterviewSlot(slot);
        booking.setCandidate(candidate);
        booking.setInterviewer(interviewer);
        booking.setBookingTime(LocalDateTime.now());

        slot.setBooking(booking);

        bookingRepository.save(booking);
        slotRepository.save(slot);

        booking_dto response = new booking_dto();
        response.setInterviewSlotId(slot.getId());
        response.setStartTime(slot.getStartTime());
        response.setEndTime(slot.getEndTime());
        response.setStatus(slot.isStatus());
        response.setCandidateName(candidate.getFirstname() + " " + candidate.getLastname());
        response.setCandidateEmail(candidate.getEmail());
        response.setInterviewerName(interviewer.getName());
        response.setInterviewerDepartment(interviewer.getDepartment());

        return response;
    }
}