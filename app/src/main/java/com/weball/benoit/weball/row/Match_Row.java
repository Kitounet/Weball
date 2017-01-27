package com.weball.benoit.weball.row;

/**
 * Created by benoi on 14/07/2016.
 */
public class Match_Row {
    private String  id_match;
    private String  status;
    private String  time;
    private String  title_match;
    private String  id_creator;
    private String  creator_name;
    private String  id_team1;
    private String     nb_team1;
    private String  name_team1;
    private String     nb_team2;
    private String  name_team2;
    private String  id_team2;
    private String  photo;
    private String  id_field;

    public Match_Row(String id_match, String status, String time, String title_match, String creator_name, String id_creator, String id_team1, String nb_team1, String name_team1, String nb_team2, String name_team2, String id_team2, String photo, String id_field) {
        this.id_match = id_match;
        this.status = status;
        this.time = time;
        this.title_match = title_match;
        this.creator_name = creator_name;
        this.id_creator = id_creator;
        this.id_team1 = id_team1;
        this.nb_team1 = nb_team1;
        this.name_team1 = name_team1;
        this.nb_team2 = nb_team2;
        this.name_team2 = name_team2;
        this.id_team2 = id_team2;
        this.photo = photo;
        this.id_field = id_field;
    }

    public String getName_team2() {
        return name_team2;
    }

    public void setName_team2(String name_team2) {
        this.name_team2 = name_team2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNb_team2() {
        return nb_team2;
    }

    public void setNb_team2(String nb_team2) {
        this.nb_team2 = nb_team2;
    }

    public String getId_field() {
        return id_field;
    }

    public void setId_field(String id_field) {
        this.id_field = id_field;
    }

    public String getId_match() {
        return id_match;
    }

    public void setId_match(String id_match) {
        this.id_match = id_match;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle_match() {
        return title_match;
    }

    public void setTitle_match(String title_match) {
        this.title_match = title_match;
    }

    public String getId_creator() {
        return id_creator;
    }

    public void setId_creator(String id_creator) {
        this.id_creator = id_creator;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public String getId_team1() {
        return id_team1;
    }

    public void setId_team1(String id_team1) {
        this.id_team1 = id_team1;
    }

    public String getNb_team1() {
        return nb_team1;
    }

    public void setNb_team1(String nb_team1) {
        this.nb_team1 = nb_team1;
    }

    public String getName_team1() {
        return name_team1;
    }

    public void setName_team1(String name_team1) {
        this.name_team1 = name_team1;
    }

    public String getId_team2() {
        return id_team2;
    }

    public void setId_team2(String id_team2) {
        this.id_team2 = id_team2;
    }

    public String getPhoto(){
        return this.photo;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }
}
