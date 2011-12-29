package no.opentech.chronojohn.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by: Christian Lønaas
 * Date: 09.04.11
 * Time: 00:19
 * modified for ChronoJohn 29.11.12
 */

/* helper functions for SQLite */
public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "chronojohn.db";
    private final Context myContext;
    private static Logger log = Logger.getLogger(DBHelper.class);


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        log.debug("creating database");
        try {
            InputStream is = myContext.getAssets().open("create_database.sql");
            String[] statements = parseSqlFile(is);
            for(String statement : statements)
                db.execSQL(statement);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS item;");
        //TODO: handle updates
    }


    public static String[] parseSqlFile(InputStream sqlFile) throws IOException {
        return parseSqlFile(new BufferedReader(new InputStreamReader(sqlFile)));
    }

    public static String[] parseSqlFile(BufferedReader sqlFile) throws IOException {
        String line;
        StringBuilder sql = new StringBuilder();
        String multiLineComment = null;

        while ((line = sqlFile.readLine()) != null) {
            line = line.trim();

            // Check for start of multi-line comment
            if (multiLineComment == null) {
                // Check for first multi-line comment type
                if (line.startsWith("/*")) {
                    if (!line.endsWith("}")) {
                        multiLineComment = "/*";
                    }
                    // Check for second multi-line comment type
                } else if (line.startsWith("{")) {
                    if (!line.endsWith("}")) {
                        multiLineComment = "{";
                    }
                    // Append line if line is not empty or a single line comment
                } else if (!line.startsWith("--") && !line.equals("")) {
                    sql.append(line);
                } // Check for matching end comment
            } else if (multiLineComment.equals("/*")) {
                if (line.endsWith("*/")) {
                    multiLineComment = null;
                }
                // Check for matching end comment
            } else if (multiLineComment.equals("{")) {
                if (line.endsWith("}")) {
                    multiLineComment = null;
                }
            }

        }

        sqlFile.close();

        return sql.toString().split(";");
    }
}