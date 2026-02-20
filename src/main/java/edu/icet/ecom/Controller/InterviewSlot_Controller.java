package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.interviewSlot_dto;
import edu.icet.ecom.Service.interviewSlot_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slot")
@CrossOrigin(origins = "http://localhost:4200")
public class InterviewSlot_Controller {

    @Autowired
    private interviewSlot_service slotService;

    @GetMapping("/all")
    public List<interviewSlot_dto> getAllSlots() {
        return slotService.getAllSlots();
    }

    @PostMapping("/add/{interviewerId}")
    public void addSlot(@PathVariable Long interviewerId, @RequestBody interviewSlot_dto dto) {
        slotService.addSlots(dto, interviewerId);
    }

    @PutMapping("/update/{id}")
    public interviewSlot_dto updateSlot(@PathVariable Long id, @RequestBody interviewSlot_dto dto) {
        return slotService.updateSlot(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteSlot(@PathVariable Long id) {
        slotService.deleteSlot(id);
    }
}