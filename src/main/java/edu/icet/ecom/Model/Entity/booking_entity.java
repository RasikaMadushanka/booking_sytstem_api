package edu.icet.ecom.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class booking_entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "interview_slot_id", nullable = false)
    private interviewSlot_entity interviewSlot;

    @ManyToOne
    @JoinColumn(name = "interviewer_id", nullable = false)
    private interviewer_entity interviewer;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private candidater_entity candidate;

    @Column(nullable = false)
    private LocalDateTime bookingTime;
}