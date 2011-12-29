package no.opentech.chronojohn.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by: Christian Lønaas
 * Date: 29.12.11
 * Time: 16:23
 */
public class Timer implements Serializable {
    private int seconds;
    private String name;
    private String description;
    private Date created;
    private boolean defaultTimer;

    public Timer(String name, int seconds) {
        this.name = name;
        this.seconds = seconds;
    }
    
    public static Timer getDefaultTimer() {
        Timer t = new Timer("Default", 0);
        t.setDefaultTimer(true);
        return t;
    }
    
    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isDefaultTimer() {
        return defaultTimer;
    }

    public void setDefaultTimer(boolean defaultTimer) {
        this.defaultTimer = defaultTimer;
    }
}
