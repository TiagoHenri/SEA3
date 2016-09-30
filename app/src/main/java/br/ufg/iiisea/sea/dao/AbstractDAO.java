package br.ufg.iiisea.sea.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import br.ufg.iiisea.sea.utils.MutableBean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by fellipe on 22/09/16.
 */
public abstract class AbstractDAO<T extends MutableBean> {
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

    public long save(T entity) throws SQLiteException {
        try {
            ContentValues values = toContentValues(entity);
            long result = writableDatabase.insert(getTableName(), null, values);
            return result;
        } catch (SQLiteException ex) {
            throw ex;
        }
    }

    public void delete(T entity) {

    }

    public void deleteAll() throws SQLiteException {
        try {
            writableDatabase.execSQL("DELETE FROM " + getTableName());
            Log.i("AbstractDAO:deleteAll", "Deletou todas as colunas de "+getTableName()+".");
        } catch (SQLiteException ex) {
            throw ex;
        }
    }

    public long edit(T entity) throws SQLiteException {
        try {
            ContentValues valores = toContentValues(entity);

            String[] valoresParaSubstituir = {
                    String.valueOf(entity.getId())
            };

            long result = writableDatabase.update(getTableName(), valores, getKeyPrimaryColumnName() + " = ?", valoresParaSubstituir);
            return result;
        } catch (SQLiteException ex) {
            throw ex;
        }
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
