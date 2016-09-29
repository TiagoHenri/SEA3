package br.ufg.iiisea.sea.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by fellipe on 22/09/16.
 */
public class DataBaseHelper extends SQLiteOpenHelper {


    private static DataBaseHelper instance;

    private DataBaseHelper(Context context) {
        super(context, DBEntries.BD_NOME, null, DBEntries.BD_VERSION);
    }

    public static DataBaseHelper getInstance(Context context) {
        if(instance == null)
            return new DataBaseHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        Log.i("SQL", "entrou no onCreate");
        db.execSQL(DBEntries.UsuarioEntry.SQL_CREATE_ENTRIES);
        db.execSQL(DBEntries.EventoEntry.SQL_CREATE_ENTRIES);
        db.execSQL(DBEntries.NoticiaEntry.SQL_CREATE_ENTRIES);
        db.execSQL(DBEntries.ProgramacaoEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DBEntries.UsuarioEntry.SQL_DELETE_ENTRIES);
        db.execSQL(DBEntries.EventoEntry.SQL_DELETE_ENTRIES);
        db.execSQL(DBEntries.NoticiaEntry.SQL_DELETE_ENTRIES);
        db.execSQL(DBEntries.ProgramacaoEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
