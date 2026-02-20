package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.interviewSlot_dto;
import edu.icet.ecom.Model.Entity.interviewSlot_entity;
import edu.icet.ecom.Model.Entity.interviewer_entity;
import edu.icet.ecom.Repository.interviewSlot_repository;
import edu.icet.ecom.Repository.interviwer_repository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class interviewSlot_service {

    private final interviewSlot_repository slotRepository;
    private final interviwer_repository interviewerRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public interviewSlot_service(interviewSlot_repository slotRepository,
                                 interviwer_repository interviewerRepository) {
        this.slotRepository = slotRepository;
        this.interviewerRepository = interviewerRepository;
    }

    private interviewSlot_dto mapToDto(interviewSlot_entity slot) {
        interviewSlot_dto dto = new interviewSlot_dto();
        dto.setId(slot.getId());
        dto.setStartTime(slot.getStartTime());
        dto.setEndTime(slot.getEndTime());
        dto.setStatus(slot.isStatus());
        dto.setDescription(slot.getDescription());

        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(slot.getEndTime())) {
            dto.setAction("Expired");
            dto.setStatus(false);
        } else if (slot.getBooking() != null) {
            dto.setAction("Booked");
            dto.setStatus(true);
            dto.setBookedBy(slot.getBooking().getCandidate().getFirstname());
        } else {
            dto.setAction("Available");
            dto.setStatus(false);
        }
        return dto;
    }

    public List<interviewSlot_dto> getAllSlots() {
        return slotRepository.findAll().stream().map(this::mapToDto).toList();
    }

    public interviewSlot_dto getSlotById(Long id) {
        interviewSlot_entity slot = slotRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slot not found"));
        return mapToDto(slot);
    }

    public void addSlots(interviewSlot_dto dto, Long interviewerId) {
        interviewer_entity interviewer = interviewerRepository.findById(interviewerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Interviewer not found"));

        interviewSlot_entity slot = modelMapper.map(dto, interviewSlot_entity.class);
        slot.setInterviewer(interviewer);
        slotRepository.save(slot);
    }

    public interviewSlot_dto updateSlot(Long id, interviewSlot_dto dto) {
        interviewSlot_entity slot = slotRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slot not found"));

        slot.setStartTime(dto.getStartTime());
        slot.setEndTime(dto.getEndTime());
        slot.setDescription(dto.getDescription());
        slot.setStatus(dto.isStatus());

        return mapToDto(slotRepository.save(slot));
    }

    public void deleteSlot(Long id) {
        if (!slotRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Slot not found");
        slotRepository.deleteById(id);
    }
}