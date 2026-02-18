package edu.icet.ecom.Model.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class interviewSlot_dto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean booked;
    private String bookedBy;
    private String description;
    private LocalDate date;

}
