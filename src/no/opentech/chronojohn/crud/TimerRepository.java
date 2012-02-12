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

package no.opentech.chronojohn.crud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import no.opentech.chronojohn.entities.Timer;
import no.opentech.chronojohn.utils.DBHelper;
import no.opentech.chronojohn.utils.Logger;
import no.opentech.chronojohn.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Christian Lønaas
 * Date: 29.12.11
 * Time: 16:46
 */
public class TimerRepository {
    private static Logger log;
    DBHelper dbHelper;

    public TimerRepository(DBHelper dbHelper) {
        log = Logger.getLogger(TimerRepository.class);
        this.dbHelper = dbHelper;
    }

    public ArrayList<Timer> getTimers() {
        return getTimers(null);
    }

    public ArrayList<Timer> getTimers(String orderBy) {
        ArrayList<Timer> timerList = new ArrayList<Timer>();
        Cursor c = dbHelper.getReadableDatabase().query("timer", new String[]{"id", "name", "description", "seconds", "created"}
                , null, null, null, null, orderBy);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            timerList.add(Utils.createTimerFromValues(c.getLong(0), c.getString(1), c.getString(2), c.getInt(3), c.getLong(4)));
            c.moveToNext();
        }
        c.close();
        log.debug("Returned " + timerList.size() + " items");
        dbHelper.close();
        return timerList;

    }

    public long insert(Timer timer) {
        String name = timer.getName();
        String desc = timer.getDescription();
        int seconds = timer.getSeconds();

        long created = Utils.getTimeStamp(timer.getCreated());

        String sql = "INSERT INTO timer (name, description, seconds, created) " +
                "VALUES ('" + name + "','" + desc + "'," + seconds + "," + created + ");";
        SQLiteDatabase db;
        db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
        Cursor c = db.rawQuery("SELECT last_insert_rowid();", null);
        c.moveToFirst();
        long id = c.getLong(0);
        log.debug("Inserted timer " + timer.getName() + " with ID " + id);
        dbHelper.close();
        return id;
    }

    public void delete(Timer timer) {
        Long id = timer.getId();
        if (null == id) throw new IllegalArgumentException("ID can't be null");

        String sql = "DELETE FROM timer WHERE id = " + id + ";";
        dbHelper.getWritableDatabase().execSQL(sql);
        log.debug("Deleted timer " + timer.getName() + " with ID " + id);
        dbHelper.close();
    }
}
