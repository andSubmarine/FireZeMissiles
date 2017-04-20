package one.blanke.firezemissiles.model;

import android.widget.Toast;

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
    private boolean alive;

    public Target(int id, String name, String short_name, String leader, String description, int strength) {
        this.id = id;
        if (strength < 0) alive = false;
        else alive = true;
        this.name = name;
        this.short_name = short_name;
        this.leader = leader;
        this.description = description;
        this.strength = strength;
        this.maxStrength = strength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength += strength;
    }

    public int getProgress() {
        double percentage =  1 - ((maxStrength - strength) / maxStrength);
        return (int) Math.floor(percentage*100);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void toggleAlive() {
        this.alive = !this.alive;
    }

    public int getID() {
        return id;
    }

    public int getMaxStrength() {
        return maxStrength;
    }
}
