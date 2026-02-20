package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.interviewer_dto;
import edu.icet.ecom.Service.interviewer_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviewer")
@CrossOrigin(origins = "http://localhost:4200")
public class Interviewer_Controller {

    @Autowired
    private interviewer_service interviewerService;

    @PostMapping("/add")
    public void addInterviewr(@RequestBody interviewer_dto dto) {
        interviewerService.addinterViewer(dto);
    }

    @GetMapping("/all")
    public List<interviewer_dto> AllInterviewer() {
        return interviewerService.ViewAll();
    }
}