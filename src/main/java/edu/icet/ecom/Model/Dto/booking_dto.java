package edu.icet.ecom.Model.Dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class booking_dto {

    private Long interviewSlotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean status;

    private String interviewerName;
    private String interviewerDepartment;

    private String candidateName;
    private String candidateEmail;
}