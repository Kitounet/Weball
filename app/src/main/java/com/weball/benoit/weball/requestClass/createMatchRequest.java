package com.weball.benoit.weball.requestClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benoi on 29/03/2016.
 */
public class createMatchRequest {
    private Match  match;

    public createMatchRequest(String name, String startDate, String endDate, int maxPlayers, String field, ArrayList<String> guests)
    {
        this.match = new Match(name, startDate, endDate,maxPlayers,field, guests);
    }

    public class Match
    {
        private String name;
        private String startDate;
        private String  endDate;
        private int     maxPlayers;
        private String  field;
        private ArrayList<String> guests = new ArrayList<String>();


        public Match(String name, String startDate, String endDate, int maxPlayers, String field, ArrayList<String> guests)
        {
            this.name = name;
            this.startDate = startDate;
            this.endDate = endDate;
            this.maxPlayers = maxPlayers;
            this.field = field;
            this.guests = guests;
        }

    }
}

