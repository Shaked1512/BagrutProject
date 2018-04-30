package com.appschool.bagrutproject.Shaked;

import java.sql.Time;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by pc on 23/04/2018.
 */

public class Broadcast extends ArrayList<Broadcast> {
    private String name;
    private String description;
    private String time;
    private long broadcastId;

    public Broadcast(String name,String description, String time, long broadcastId) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.broadcastId = broadcastId;

    }
    public Broadcast(String name,String description, String time) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.broadcastId = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setBroadcastId(long broadcastId) {
        this.broadcastId = broadcastId;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public long getBroadcastId() {
        return broadcastId;
    }

    @Override
    public String toString() {
        return "Broadcast{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", time=" + time +
                ", broadcastId=" + broadcastId +
                '}';
    }

    @Override
    public Stream<Broadcast> stream() {
        return null;
    }
}
