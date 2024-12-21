package com.hotelprject.hotelproject.controller;
import com.hotelprject.hotelproject.model.Room;
import com.hotelprject.hotelproject.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RoomsController {
    private final RoomService roomService;

    @GetMapping("/rooms")
    public String showRooms(@RequestParam(required = false) String recommended, Model model) {
        List<Room> allRooms = roomService.getAllRooms();

        if (recommended != null) {
            String personalityMessage;
            String recommendedRoomName;

            switch (recommended) {
                case "luxury":
                    personalityMessage = "Siz lüks ve konfora önem veren bir kişiliğe sahipsiniz. Size özel önerimiz:";
                    recommendedRoomName = "Lüks Süit Oda";
                    break;
                case "vintage":
                    personalityMessage = "Siz nostaljik ve romantik bir ruha sahipsiniz. Size özel önerimiz:";
                    recommendedRoomName = "Vintage Odası";
                    break;
                case "tropical":
                    personalityMessage = "Siz macera dolu ve egzotik deneyimleri seven birisiniz. Size özel önerimiz:";
                    recommendedRoomName = "Tropik Oda";
                    break;
                case "sky":
                    personalityMessage = "Siz huzur ve sakinliğe önem veren birisiniz. Size özel önerimiz:";
                    recommendedRoomName = "Gökyüzü Odası";
                    break;
                case "overthink":
                    personalityMessage = "Siz detaylara önem veren düşünceli birisiniz. Size özel önerimiz:";
                    recommendedRoomName = "Overthink Odası";
                    break;
                case "aquarium":
                    personalityMessage = "Siz su ve doğa ile iç içe olmayı seven birisiniz. Size özel önerimiz:";
                    recommendedRoomName = "Akvaryum Odası";
                    break;
                case "winter":
                    personalityMessage = "Siz soğuk ve huzurlu ortamları seven birisiniz. Size özel önerimiz:";
                    recommendedRoomName = "Kış Odası";
                    break;
                default:
                    personalityMessage = "Size özel önerimiz:";
                    recommendedRoomName = "Lüks Süit Oda";
                    break;
            }

            System.out.println("Looking for room: " + recommendedRoomName);

            // Önerilen odayı bul
            for (Room room : allRooms) {
                System.out.println("Checking room: " + room.getName());
                if (room.getName().equals(recommendedRoomName)) {
                    System.out.println("Found recommended room!");
                    model.addAttribute("recommendedRoom", room);
                    model.addAttribute("personalityMessage", personalityMessage);
                    break;
                }
            }
        }

        model.addAttribute("rooms", allRooms);
        return "rooms";
    }
}