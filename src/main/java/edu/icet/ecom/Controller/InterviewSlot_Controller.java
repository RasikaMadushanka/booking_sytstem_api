package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.interviewSlot_dto;
import edu.icet.ecom.Service.interviewSlot_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("slot")
@CrossOrigin(origins ="http://localhost:4200")

public class InterviewSlot_Controller {
    @Autowired
    interviewSlot_service interviewSlotServicel;
    @GetMapping("/all")
    public List<interviewSlot_dto> getAllslots(){
        return interviewSlotServicel.getAllSlots();
    }
    @PostMapping("/add")
    public void addSlots(@RequestBody interviewSlot_dto slotDto){
        interviewSlotServicel.addSlots(slotDto);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<interviewSlot_dto> updateSlot(@PathVariable Long id, @RequestBody interviewSlot_dto slotDto){
        interviewSlot_dto UpdateSlot =interviewSlotServicel.updateSlot(id,slotDto);
        return ResponseEntity.ok(UpdateSlot);

    }
    @DeleteMapping("/{id}")
    public void deleteslot(@PathVariable Long id){
        interviewSlotServicel.deleteslot(id);
    }
}
