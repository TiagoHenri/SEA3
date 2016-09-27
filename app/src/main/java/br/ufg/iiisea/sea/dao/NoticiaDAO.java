package br.ufg.iiisea.sea.dao;

import android.content.ContentValues;
import android.content.Context;
import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Noticia;

import java.util.Date;
import java.util.List;

/**
 * Created by fellipe on 23/09/16.
 */
public class NoticiaDAO extends AbstractDAO<Noticia> {

    public NoticiaDAO(Context context) {
        super(context);
    }

    public List<Noticia> getAllNoticiaByEvento(Evento evento) {
        return query("SELECT * FROM " + getTableName()
                        + " WHERE "
                        + DBEntries.NoticiaEntry.COLUMN_EVEN_ID + " = "
                        + evento.getId());
    }

    @Override
    public String getTableName() {
        return DBEntries.NoticiaEntry.TABLE_NAME;
    }

    @Override
    public String getKeyPrimaryColumnName() {
        return DBEntries.NoticiaEntry.COLUMN_ID;
    }

//    @Override
//    public String getSQLCreateEntries() {
//        return DBEntries.NoticiaEntry.SQL_CREATE_ENTRIES;
//    }
//
//    @Override
//    public String getSQLDeleteEntries() {
//        return DBEntries.NoticiaEntry.SQL_DELETE_ENTRIES;
//    }

    @Override
    public Noticia toEntity(ContentValues contentValues) {
        Noticia entity = new Noticia();
        entity.setId(contentValues.getAsInteger(DBEntries.NoticiaEntry.COLUMN_ID));
        //entity.setData(contentValues.getAsString(DBEntries.NoticiaEntry.COLUMN_NAME_DATA));
        entity.setTitulo(contentValues.getAsString(DBEntries.NoticiaEntry.COLUMN_NAME_TITULO));
        entity.setConteudo(contentValues.getAsString(DBEntries.NoticiaEntry.COLUMN_NAME_CONTEUDO));

        Evento evento = new Evento(contentValues.getAsInteger(DBEntries.NoticiaEntry.COLUMN_EVEN_ID));
        entity.setEvento(evento);

        return entity;
    }

    @Override
    public ContentValues toContentValues(Noticia entity) {
        ContentValues values = new ContentValues();
        //values.put(DBEntries.NoticiaEntry.COLUMN_ID, entity.getId());
        values.put(DBEntries.NoticiaEntry.COLUMN_EVEN_ID, entity.getEvento().getId());
        //values.put(DBEntries.NoticiaEntry.COLUMN_NAME_DATA, entity.getData().toString());
        values.put(DBEntries.NoticiaEntry.COLUMN_NAME_TITULO, entity.getTitulo());
        values.put(DBEntries.NoticiaEntry.COLUMN_NAME_CONTEUDO, entity.getConteudo());
        return values;
    }
}
