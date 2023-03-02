package masterous.if4b.kamus.databases;

import static android.provider.BaseColumns._ID;
import static masterous.if4b.kamus.databases.DatabaseContract.KamusColumns.KAMUS_DESCRIPTION;
import static masterous.if4b.kamus.databases.DatabaseContract.KamusColumns.KAMUS_TITLE;
import static masterous.if4b.kamus.databases.DatabaseContract.TABLE_KAMUS_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import masterous.if4b.kamus.models.Kamus;

public class KamusHelper {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public KamusHelper(Context context) {
        this.context = context;
    }

    public KamusHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public ArrayList<Kamus> getAllDataByTitle(String title) {
        Cursor cursor = database.query(TABLE_KAMUS_NAME, null,
                KAMUS_TITLE + " LIKE ?",
                new String[] {"%" + title + "%"},
                null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<Kamus> arrayList = new ArrayList<>();
        Kamus kamus;
        if (cursor.getCount() > 0) {
            do {
                kamus = new Kamus();
                kamus.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamus.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(KAMUS_TITLE)));
                kamus.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(KAMUS_DESCRIPTION)));

                arrayList.add(kamus);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<Kamus> getAllData() {
        Cursor cursor = database.query(TABLE_KAMUS_NAME, null, null, null,
                null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<Kamus> arrayList = new ArrayList<>();
        Kamus kamus;
        if (cursor.getCount() > 0) {
            do {
                kamus = new Kamus();
                kamus.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamus.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(KAMUS_TITLE)));
                kamus.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(KAMUS_DESCRIPTION)));

                arrayList.add(kamus);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertData(Kamus kamus) {
        ContentValues cv = new ContentValues();
        cv.put(KAMUS_TITLE, kamus.getTitle());
        cv.put(KAMUS_DESCRIPTION, kamus.getDescription());
        return database.insert(TABLE_KAMUS_NAME, null, cv);
    }

    public int updateData(Kamus kamus) {
        ContentValues cv = new ContentValues();
        cv.put(KAMUS_TITLE, kamus.getTitle());
        cv.put(KAMUS_DESCRIPTION, kamus.getDescription());
        return database.update(TABLE_KAMUS_NAME, cv, _ID + "='" + kamus.getId() + "'", null);
    }

    public int deleteData(int id) {
        return database.delete(TABLE_KAMUS_NAME, _ID + "='" + id + "'", null);
    }
}
