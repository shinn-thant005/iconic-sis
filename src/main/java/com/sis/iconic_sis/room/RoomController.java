package com.sis.iconic_sis.room;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/room")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getRooms() {
        return roomService.getRooms();
    }

    @PostMapping("/add")
    public ResponseEntity<Objects> addRoom(@RequestBody Room room) {
        roomService.addRoom(room);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<Room> deleteRoom(@PathVariable("roomId") Integer roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update/{roomId}")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room, @PathVariable("roomId") Integer roomId) {
        roomService.updateRoom(room, roomId);
        return ResponseEntity.noContent().build();
    }
}
