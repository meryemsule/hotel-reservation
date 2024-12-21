package com.hotelprject.hotelproject.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
    @GetMapping({"/", "/home"})
    public String homePage(HttpSession session, Model model) {
        return "home";
    }

    @GetMapping("/personality-test")
    public String showPersonalityTest() {
        return "personality-test";
    }

    @PostMapping("/find-room")
    public String findRoom(@RequestParam(required = false) String vacationStyle,
                           @RequestParam(required = false) String colorPreference,
                           @RequestParam(required = false) String atmosphere,
                           RedirectAttributes redirectAttributes) {

        System.out.println("Received POST request to /find-room");
        System.out.println("vacationStyle: " + vacationStyle);

        String recommendedRoom;

        // Tatil tarzına göre oda seç
        if (vacationStyle != null) {
            switch (vacationStyle) {
                case "luxury":
                    recommendedRoom = "luxury";
                    break;
                case "vintage":
                    recommendedRoom = "vintage";
                    break;
                case "tropical":
                    recommendedRoom = "tropical";
                    break;
                case "sky":
                    recommendedRoom = "sky";
                    break;
                case "overthink":
                    recommendedRoom = "overthink";
                    break;
                case "aquarium":
                    recommendedRoom = "aquarium";
                    break;
                case "winter":
                    recommendedRoom = "winter";
                    break;
                default:
                    recommendedRoom = "luxury";
            }
        }
        // Renk tercihine göre oda seç
        else if (colorPreference != null) {
            switch (colorPreference) {
                case "warm":
                    recommendedRoom = "vintage";
                    break;
                case "cool":
                    recommendedRoom = "sky";
                    break;
                case "neutral":
                    recommendedRoom = "luxury";
                    break;
                default:
                    recommendedRoom = "luxury";
            }
        }
        // Atmosfere göre oda seç
        else if (atmosphere != null) {
            switch (atmosphere) {
                case "modern":
                    recommendedRoom = "luxury";
                    break;
                case "cozy":
                    recommendedRoom = "vintage";
                    break;
                case "natural":
                    recommendedRoom = "tropical";
                    break;
                default:
                    recommendedRoom = "luxury";
            }
        }
        else {
            recommendedRoom = "luxury";
        }

        System.out.println("Redirecting to /rooms with recommended=" + recommendedRoom);
        return "redirect:/rooms?recommended=" + recommendedRoom;
    }
}