package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.booking_dto;
import edu.icet.ecom.Model.Dto.interviewSlot_dto;
import edu.icet.ecom.Service.booking_service;
import edu.icet.ecom.Service.interviewSlot_service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:4200")
public class Booking_Controller {

    private final booking_service bookingService;
    private final interviewSlot_service slotService;

    public Booking_Controller(booking_service bookingService,
                              interviewSlot_service slotService) {
        this.bookingService = bookingService;
        this.slotService = slotService;
    }

    @PostMapping("/add/{slotId}")
    public interviewSlot_dto addBooking(@PathVariable Long slotId, @RequestBody booking_dto dto) {
        bookingService.createBooking(slotId, dto);
        return slotService.getSlotById(slotId);
    }
}