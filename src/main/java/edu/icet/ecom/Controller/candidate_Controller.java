package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.candidater_dto;
import edu.icet.ecom.Service.candidate_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
@CrossOrigin(origins = "http://localhost:4200")
public class candidate_Controller {

    @Autowired
    private candidate_service candidateService;

    @GetMapping("/all")
    public List<candidater_dto> getAllCandidate() {
        return candidateService.getAllCandidate();
    }

    @PostMapping("/add")
    public void addCandidate(@RequestBody candidater_dto dto) {
        candidateService.addCandidate(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<candidater_dto> updateCandidate(@PathVariable Long id, @RequestBody candidater_dto dto) {
        return ResponseEntity.ok(candidateService.updateCandidate(id, dto));
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
    }
}