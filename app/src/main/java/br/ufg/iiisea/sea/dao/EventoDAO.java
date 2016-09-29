package br.ufg.iiisea.sea.dao;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import br.ufg.iiisea.sea.bean.Evento;

import java.util.Date;
import java.util.List;

/**
 * Created by fellipe on 23/09/16.
 */
public class EventoDAO extends AbstractDAO<Evento> {

    public EventoDAO(Context context) {
        super(context);
    }

    public Evento getEventoSalvo() {
        List<Evento> results = query("SELECT * FROM "+ getTableName() + " WHERE "+ DBEntries.EventoEntry.COLUMN_ID+" = 1");
        Log.i("EventoDao", Integer.toString(results.size()));
        if(!results.isEmpty())
            return results.get(0);
        else
            return null;
    }

    @Override
    public String getTableName() {
        return DBEntries.EventoEntry.TABLE_NAME;
    }

    @Override
    public String getKeyPrimaryColumnName() {
        return DBEntries.EventoEntry.COLUMN_ID;
    }

//    @Override
//    public String getSQLCreateEntries() {
//        return DBEntries.EventoEntry.SQL_CREATE_ENTRIES;
//    }
//
//    @Override
//    public String getSQLDeleteEntries() {
//        return DBEntries.EventoEntry.SQL_DELETE_ENTRIES;
//    }

    @Override
    public Evento toEntity(ContentValues contentValues) {
        Evento entity = new Evento();
        entity.setId(contentValues.getAsInteger(DBEntries.EventoEntry.COLUMN_ID));
        entity.setNome(contentValues.getAsString(DBEntries.EventoEntry.COLUMN_NAME_NOME));
        entity.setDescricao(contentValues.getAsString(DBEntries.EventoEntry.COLUMN_NAME_DESCRICAO));
//        entity.setDataInicio(contentValues.getAsString(DBEntries.EventoEntry.COLUMN_NAME_DATA_INICIO));
//        entity.setDataFim(contentValues.getAsString(DBEntries.EventoEntry.COLUMN_NAME_DATA_FIM));
        return entity;
    }

    @Override
    public ContentValues toContentValues(Evento entity) {
        ContentValues values = new ContentValues();
        values.put(DBEntries.EventoEntry.COLUMN_ID, entity.getId());
        values.put(DBEntries.EventoEntry.COLUMN_NAME_NOME, entity.getNome());
        values.put(DBEntries.EventoEntry.COLUMN_NAME_DESCRICAO, entity.getDescricao());
//        values.put(DBEntries.EventoEntry.COLUMN_NAME_DATA_FIM, entity.getDataFim().toString());
//        values.put(DBEntries.EventoEntry.COLUMN_NAME_DATA_INICIO, entity.getDataInicio().toString());
        return values;
    }
}
