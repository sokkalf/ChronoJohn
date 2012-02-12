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

package no.opentech.chronojohn.models;

import no.opentech.chronojohn.crud.TimerRepository;
import no.opentech.chronojohn.entities.Timer;
import no.opentech.chronojohn.utils.Utils;

import java.util.ArrayList;

/**
 * Created by: Christian Lønaas
 * Date: 28.12.11
 * Time: 23:20
 */
public class ViewTimersModel {
    private ArrayList<Timer> timers;
    private TimerRepository timerRepository = Utils.getTimerRepository();
    
    public ViewTimersModel() {
        timers = new ArrayList<Timer>();
        refresh();
    }
    
    public void refresh() {
        timers.clear();
        timers.add(Timer.getQuickTimer());
        timers.add(Timer.getDefaultTimer());
        timers.addAll(timerRepository.getTimers());
    }

    public ArrayList<Timer> getTimers() {
        return timers;
    }
    
    public void addTimer(Timer timer) {
        timers.add(timer);
    }
    
    public Timer getTimer(int pos) {
        return timers.get(pos);
    }
    
    public void delete(Timer timer) {
        getTimers().remove(timer);
        timerRepository.delete(timer);
    }
}
