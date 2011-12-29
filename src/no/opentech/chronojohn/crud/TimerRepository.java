package no.opentech.chronojohn.crud;

import no.opentech.chronojohn.utils.DBHelper;
import no.opentech.chronojohn.utils.Logger;

/**
 * Created by: Christian Lønaas
 * Date: 29.12.11
 * Time: 16:46
 */
public class TimerRepository {
    private static Logger log = Logger.getLogger(TimerRepository.class);
    DBHelper dbHelper;

    public TimerRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
}
