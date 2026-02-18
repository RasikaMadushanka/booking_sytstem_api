package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.candidater_dto;
import edu.icet.ecom.Model.Dto.interviewSlot_dto;
import edu.icet.ecom.Service.candidate_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("candidate")
public class candidate_Controller {
    @Autowired
    candidate_service candidateService;
    @GetMapping("/all")
    public List<candidater_dto> getAllcandidate(){
        return candidateService.getAllCandidate();
    }
    @PostMapping("/add")
    public void addCandidate(@RequestBody candidater_dto candidatedto){
        candidateService.addCandidate(candidatedto);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<candidater_dto> updateCandidate(@PathVariable Long id, @RequestBody candidater_dto candidatedto){
        candidater_dto UpdateSlot =candidateService.updateCandidate(id,candidatedto);
        return ResponseEntity.ok(UpdateSlot);

    }
    @DeleteMapping("/{id}")
    public void deletecandidate(@PathVariable Long id){
        candidateService.deleteCandidate(id);
    }

}
