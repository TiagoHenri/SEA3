package br.ufg.iiisea.sea.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by fellipe on 22/09/16.
 */
public abstract class AbstractDAO<T> {
    public abstract String getTableName();
    public abstract String getKeyPrimaryColumnName();
//    public abstract String getSQLCreateEntries();
//    public abstract String getSQLDeleteEntries();
    public abstract T toEntity(ContentValues contentValues);
    public abstract ContentValues toContentValues(T entity);

    protected SQLiteDatabase writableDatabase = null;
    protected DataBaseHelper helper;

    public AbstractDAO(Context context) {
        this.helper = DataBaseHelper.getInstance(context);
        writableDatabase = helper.getWritableDatabase();
    }

    public long save(T entity) {
        ContentValues values = toContentValues(entity);
        return writableDatabase.insert(getTableName(), null, values);
    }

    public void delete(T entity) {

    }

    public void edit(T entity) {

    }

    public T getByKeyPrimary(int key) {
        String queryOne = "SELECT * FROM " +
                getTableName() + " WHERE " +
                getKeyPrimaryColumnName() + " = " + key;
        List<T> results = query(queryOne);
        if(results.isEmpty())
            return null;
        return results.get(0);

    }

    public List<T> query(String querySql) {
        //fragmento totalmente retirado do código do Matheus!!!
        Cursor cursor = writableDatabase.rawQuery(querySql, null);
        List<T> results = new LinkedList<>();
        if(cursor.moveToFirst()) {
            do {
                ContentValues contentValues = new ContentValues();
                DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                T data = toEntity(contentValues);
                results.add(data);
            } while (cursor.moveToNext());
        }
        return results;
    }
}
