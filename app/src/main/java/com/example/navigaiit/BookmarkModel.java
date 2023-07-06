package com.example.navigaiit;

public class BookmarkModel {
    private String building, floor, room, notes;
    int id;

    public BookmarkModel(int id, String building, String floor, String room, String notes) {
        this.id = id;
        this.building = building;
        this.floor = floor;
        this.room = room;
        this.notes = notes;
    }
//    public BookmarkModel(String building, String floor, String room, String notes) {
//        id++;
//        this.building = building;
//        this.floor = floor;
//        this.room = room;
//        this.notes = notes;
//    }
    public BookmarkModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
