package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.interviewer_dto;
import edu.icet.ecom.Model.Entity.interviewer_entity;
import edu.icet.ecom.Repository.interviwer_repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class interviewer_service {

    @Autowired
    private interviwer_repository interviewerRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public void addinterViewer(interviewer_dto dto) {
        interviewer_entity entity = modelMapper.map(dto, interviewer_entity.class);
        interviewerRepository.save(entity);
    }

    public List<interviewer_dto> ViewAll() {
        return interviewerRepository.findAll().stream()
                .map(i -> modelMapper.map(i, interviewer_dto.class))
                .toList();
    }
}