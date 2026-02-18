package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.candidater_dto;
import edu.icet.ecom.Model.Entity.candidater_entity;
import edu.icet.ecom.Repository.candidate_repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class candidate_service {
    @Autowired
    candidate_repository candidate_Repository;
    ModelMapper modelMapper =new ModelMapper();

    public List<candidater_dto> getAllSlots() {
        List<candidater_entity>candiList = candidate_Repository.findAll();
        return candiList.stream().map((element)->
                        modelMapper.map(element, candidater_dto.class))
                .toList();
    }

    public void addCandidate(candidater_dto candidatedto) {
        candidater_entity addCandidater = modelMapper.map(candidatedto, candidater_entity.class);
        candidate_Repository.save(addCandidater);
    }

    public candidater_dto updateCandidate(Long id, candidater_dto candidatedto) {
        candidater_entity existingCandi= candidate_Repository.findById(id).orElseThrow(()-> new ResponseStatusException(
                HttpStatus.NOT_FOUND,"category Not Found with id"+id));
        existingCandi.setFirstname(candidatedto.getFirstname());
        candidater_entity updatecandi = candidate_Repository.save(existingCandi);
        return modelMapper.map(updatecandi, candidater_dto.class);
    }

    public void deleteCandidate(Long id) {
        if(candidate_Repository.existsById(id)){
            candidate_Repository.deleteById(id);

        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with ID: " + id);
        }
    }
}
