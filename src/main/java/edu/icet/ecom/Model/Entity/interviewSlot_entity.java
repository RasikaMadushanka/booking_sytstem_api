package edu.icet.ecom.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "interview_slot")
public class interviewSlot_entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private boolean status = false;

    private String description;

    @ManyToOne
    @JoinColumn(name = "interviewer_id", nullable = false)
    private interviewer_entity interviewer;

    @OneToOne(mappedBy = "interviewSlot", cascade = CascadeType.ALL)
    private booking_entity booking;
}