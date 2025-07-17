package se.lexicon.flightbooking_api.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class VisualToolResponseTools {
    @Tool(description = "Launch a visual booking interface for the user")
    public String launchBookingInterface(String message) {
        System.out.println("Launching a visual booking interface...");
        return message + "[LAUNCH_BOOKING_INTERFACE]";
    }
    
    @Tool(description = "Launch a visual date picker for the user")
    public String launchDatePicker(String message) {
        System.out.println("Launching a visual date picker...");
        return message + "[LAUNCH_DATE_PICKER]";
    }

    @Tool(description = "Launch a visual destination picker for the user")
    public String launchDestinationsPicker(String message) {
        System.out.println("Launching a visual destination picker...");
        return message + "[LAUNCH_DESTINATION_PICKER]";
    }
}
