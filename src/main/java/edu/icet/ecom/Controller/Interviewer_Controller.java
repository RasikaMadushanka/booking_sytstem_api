package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.interviewSlot_dto;
import edu.icet.ecom.Repository.interviewSlot_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("slot")
public class Interviewer_Controller {
    @Autowired
    interviewSlot_repository interviewSlotRepository;
    @GetMapping("/all")
    public List<interviewSlot_dto>getAllslots(){
        return interviewSlotRepository.getAllSlots();
    }
    @PostMapping("/add")
    public void addSlots(@RequestBody interviewSlot_dto slotDto){
        interviewSlotRepository.addSlots();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<interviewSlot_dto>updateSlot(@PathVariable Long id,@RequestBody interviewSlot_dto slotDto){
        interviewSlot_dto UpdateSlot =interviewSlotRepository.updateSlot(id,slotDto);
        return ResponseEntity.ok(UpdateSlot);

    }
    @DeleteMapping("/{id}")
    public void deleteslot(@PathVariable Long id){
        interviewSlotRepository.deleteslot(id);
    }

}
