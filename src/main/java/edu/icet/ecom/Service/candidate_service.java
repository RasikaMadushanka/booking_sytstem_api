package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.candidater_dto;
import edu.icet.ecom.Model.Entity.candidater_entity;
import edu.icet.ecom.Repository.candidate_repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class candidate_service {

    @Autowired
    private candidate_repository candidateRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<candidater_dto> getAllCandidate() {
        return candidateRepository.findAll().stream()
                .map(c -> modelMapper.map(c, candidater_dto.class))
                .toList();
    }

    public candidater_dto addCandidate(candidater_dto dto) {
        candidater_entity candidate = modelMapper.map(dto, candidater_entity.class);
        candidater_entity saved = candidateRepository.save(candidate);
        return modelMapper.map(saved, candidater_dto.class);
    }

    public candidater_dto updateCandidate(Long id, candidater_dto dto) {
        candidater_entity existing = candidateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found with ID: " + id));

        existing.setFirstname(dto.getFirstname());
        existing.setLastname(dto.getLastname());
        existing.setEmail(dto.getEmail());

        return modelMapper.map(candidateRepository.save(existing), candidater_dto.class);
    }

    public void deleteCandidate(Long id) {
        if (!candidateRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found with ID: " + id);

        candidateRepository.deleteById(id);
    }
}