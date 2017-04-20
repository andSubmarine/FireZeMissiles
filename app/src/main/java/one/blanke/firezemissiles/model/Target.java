package one.blanke.firezemissiles.model;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.Toast;

import one.blanke.firezemissiles.R;
import one.blanke.firezemissiles.Status;

/**
 * Created by andre_000 on 20-04-2017.
 */

public class Target {

    private final int id;
    private String name;
    private String short_name;
    private String leader;
    private String description;
    private int strength;
    private final int maxStrength;
    private Status status;

    public Target(int id, String name, String short_name, String leader, String description, int strength) {
        this.id = id;
        if (strength < 0) status = Status.DESTROYED;
        else status = Status.ALIVE;
        this.name = name;
        this.short_name = short_name;
        this.leader = leader;
        this.description = description;
        this.strength = strength;
        this.maxStrength = strength;
    }

    public void setStatus(Status status) {
        if (this.status != Status.DESTROYED) {
            this.status = status;
            if (strength <= 0) {
                   this.status = Status.DESTROYED;
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStrength(int strength) {
        this.strength += strength;
    }

    public double getProgress() {
        return (double) (strength) / (double) maxStrength;
    }

    public Status getStatus() {return status;}

    public String getName() {
        return name;
    }

    public String getShortName() {
        return short_name;
    }

    public String getLeader() {
        return leader;
    }

    public String getDescription() {
        return description;
    }

    public int getStrength() {
        return strength;
    }

    public int getID() {
        return id;
    }

    public int getMaxStrength() {
        return maxStrength;
    }
}
