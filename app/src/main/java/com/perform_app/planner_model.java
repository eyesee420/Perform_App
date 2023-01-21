package com.perform_app;

public class planner_model {

  public   String doc_id , tittle , notes , time , calendar ;

    public planner_model() {
    }

    public planner_model(String doc_id, String tittle, String notes, String time, String calendar) {
        this.doc_id = doc_id;
        this.tittle = tittle;
        this.notes = notes;
        this.time = time;
        this.calendar = calendar;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }
}
