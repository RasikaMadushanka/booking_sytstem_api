package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.interviewSlot_dto;
import edu.icet.ecom.Model.Entity.interviewSlot_entity;
import edu.icet.ecom.Repository.interviewSlot_repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class interviewSlot_service {
    @Autowired
    interviewSlot_repository interviewSlotRepository;
    ModelMapper modelMapper =new ModelMapper();

    public List<interviewSlot_dto> getAllSlots() {
        List<interviewSlot_entity>slotList = interviewSlotRepository.findAll();
        return slotList.stream().map((element)->
                modelMapper.map(element, interviewSlot_dto.class))
                .toList();
    }

    public void addSlots(interviewSlot_dto slotDto) {
        interviewSlot_entity addSlot = modelMapper.map(slotDto, interviewSlot_entity.class);
        interviewSlotRepository.save(addSlot);
    }

    public interviewSlot_dto updateSlot(Long id, interviewSlot_dto slotDto) {
        interviewSlot_entity existingSlot= interviewSlotRepository.findById(id).orElseThrow(()-> new ResponseStatusException(
                HttpStatus.NOT_FOUND,"category Not Found with id"+id));
        existingSlot.setStartTime(slotDto.getStartTime());
        interviewSlot_entity updateslot = interviewSlotRepository.save(existingSlot);
        return modelMapper.map(updateslot, interviewSlot_dto.class);
    }

    public void deleteslot(Long id) {
        if(interviewSlotRepository.existsById(id)){
            interviewSlotRepository.deleteById(id);

        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with ID: " + id);
        }
    }
}
