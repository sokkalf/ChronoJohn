/*
 * ChronoJohn, timer for Android
 * (C)2011, 2012 by Christian Lønaas
 *    <christian dot lonaas at discombobulator dot org>
 *
 * This file is part of ChronoJohn.
 *
 * ChronoJohn is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ChronoJohn is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ChronoJohn.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

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
