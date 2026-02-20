package edu.icet.ecom;

import edu.icet.ecom.Model.Entity.*;
import edu.icet.ecom.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner initData(
            interviwer_repository interviewerRepo,
            candidate_repository candidateRepo,
            interviewSlot_repository slotRepo,
            booking_repository bookingRepo) {
        return args -> {

            // 1️⃣ Add sample Interviewers
            if (interviewerRepo.count() == 0) {
                interviewer_entity i1 = new interviewer_entity(null, "Alice Johnson", "alice@company.com", "Engineering");
                interviewer_entity i2 = new interviewer_entity(null, "Bob Smith", "bob@company.com", "HR");
                interviewerRepo.saveAll(List.of(i1, i2));
            }

            // 2️⃣ Add sample Candidates
            if (candidateRepo.count() == 0) {
                candidater_entity c1 = new candidater_entity(null, "Charlie", "Lee", "charlie@example.com");
                candidater_entity c2 = new candidater_entity(null, "Dana", "White", "dana@example.com");
                candidateRepo.saveAll(List.of(c1, c2));
            }

            // 3️⃣ Add sample Slots
            if (slotRepo.count() == 0) {
                List<interviewer_entity> interviewers = interviewerRepo.findAll();
                LocalDateTime now = LocalDateTime.now();

                // Create 4 slots for first interviewer
                for (int h = 9; h <= 12; h++) {
                    interviewSlot_entity slot = new interviewSlot_entity();
                    slot.setStartTime(now.withHour(h).withMinute(0).withSecond(0).withNano(0));
                    slot.setEndTime(now.withHour(h + 1).withMinute(0).withSecond(0).withNano(0));
                    slot.setInterviewer(interviewers.get(0));
                    slotRepo.save(slot);
                }
            }

            // 4️⃣ Optionally create one booking for testing
            List<interviewSlot_entity> slots = slotRepo.findAll();
            List<candidater_entity> candidates = candidateRepo.findAll();
            List<interviewer_entity> interviewers = interviewerRepo.findAll();

            for (interviewSlot_entity slot : slots) {
                if (slot.getBooking() == null && slot.getEndTime().isAfter(LocalDateTime.now())) {
                    booking_entity booking = new booking_entity();
                    booking.setInterviewSlot(slot);
                    booking.setInterviewer(slot.getInterviewer());
                    booking.setCandidate(candidates.get(0)); // assign first candidate
                    booking.setBookingTime(LocalDateTime.now());

                    slot.setBooking(booking);
                    slot.setStatus(true);

                    bookingRepo.save(booking);
                    slotRepo.save(slot);
                    break; // create only 1 initial booking
                }
            }

            System.out.println("✅ Sample data loaded!");
        };
    }
}