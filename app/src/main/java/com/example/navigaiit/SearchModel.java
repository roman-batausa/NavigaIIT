package com.example.navigaiit;

import java.io.Serializable;

public class SearchModel implements Serializable {
    String room, floor, building, room_descriptions;

    public String getRoom_descriptions() {
        return room_descriptions;
    }

    public void setRoom_descriptions(String room_descriptions) {
        this.room_descriptions = room_descriptions;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
