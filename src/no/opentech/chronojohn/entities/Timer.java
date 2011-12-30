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
    private boolean quickTimer;

    public boolean isQuickTimer() {
        return quickTimer;
    }

    public void setQuickTimer(boolean quickTimer) {
        this.quickTimer = quickTimer;
    }

    public Timer(String name, int seconds) {
        this.name = name;
        this.seconds = seconds;
    }
    
    public static Timer getDefaultTimer() {
        Timer t = new Timer("New timer...", 0);
        t.setDefaultTimer(true);
        t.setDescription("Create a new timer...");
        return t;
    }
    
    public static Timer getQuickTimer() {
        Timer t = new Timer("Quick...", 0);
        t.setDescription("Just time something");
        t.setQuickTimer(true);
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
