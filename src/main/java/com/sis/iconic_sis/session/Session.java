package com.sis.iconic_sis.session;

import com.sis.iconic_sis.grade.Grade;
import com.sis.iconic_sis.room.Room;
import jakarta.persistence.*;


@Entity
public class Session {
    @Id
    private String id;

    private String name;

    // Many sessions (e.g. Section A, Section B) can belong to a single Grade
    @ManyToOne(fetch = FetchType.LAZY)
    private Grade grade;

    // Many sessions (at different times/days) can share a single physical Room
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    private Integer capacity;

    public Session() {}

    public Session(String id, String name, Grade grade, Room room, Integer capacity) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.room = room;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
