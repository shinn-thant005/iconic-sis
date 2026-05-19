package com.sis.iconic_sis.room;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepo;

    public RoomService(RoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }

    public List<Room> getRooms() {
        return roomRepo.findAll();
    }

    public void addRoom(Room room) {
        roomRepo.save(room);
    }

    public void deleteRoom(Integer roomId) {
        roomRepo.deleteById(roomId);
    }

    public void updateRoom(Room room, Integer roomId) {
        Room currentRoom = roomRepo.findById(roomId).get();
        currentRoom.setName(room.getName());
        currentRoom.setFloor(room.getFloor());
        roomRepo.save(currentRoom);
    }

    public Room getRoomByName(String name) {
        return roomRepo.findByName(name).get();
    }
}
