package br.ufg.iiisea.sea.dao;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Programacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by fellipe on 28/09/16.
 */
public class ProgramacaoDAO extends AbstractDAO<Programacao> {
    public ProgramacaoDAO(Context context) {
        super(context);
    }

    public List<Programacao> getProgramacaoByEvento(Evento evento) {
        return query("SELECT * FROM " + getTableName() + " WHERE "
                + DBEntries.ProgramacaoEntry.COLUMN_EVEN_ID+" = "+evento.getId());
    }

    @Override
    public String getTableName() {
        return DBEntries.ProgramacaoEntry.TABLE_NAME;
    }

    @Override
    public String getKeyPrimaryColumnName() {
        return DBEntries.ProgramacaoEntry.COLUMN_ID;
    }

    @Override
    public Programacao toEntity(ContentValues contentValues) {
        Programacao entity = new Programacao();
        entity.setId(contentValues.getAsInteger(DBEntries.ProgramacaoEntry.COLUMN_ID));
        entity.setDescricao(contentValues.getAsString(DBEntries.ProgramacaoEntry.COLUMN_NAME_DESCRICAO));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        try {
            entity.setData(dateFormat.parse(contentValues.getAsString(DBEntries.ProgramacaoEntry.COLUMN_NAME_DATA)));
        } catch (NullPointerException|ParseException ex) {
            Log.e("NoticaDAO:", "dateFormat");
            entity.setData(new Date(0,0,0));
        }
        entity.setEvento(new Evento(contentValues.getAsInteger(DBEntries.ProgramacaoEntry.COLUMN_EVEN_ID)));

        return entity;
    }

    @Override
    public ContentValues toContentValues(Programacao entity) {

        ContentValues values = new ContentValues();
        values.put(DBEntries.ProgramacaoEntry.COLUMN_ID, entity.getId());
        values.put(DBEntries.ProgramacaoEntry.COLUMN_NAME_DESCRICAO, entity.getDescricao());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            values.put(DBEntries.ProgramacaoEntry.COLUMN_NAME_DATA, dateFormat.format(entity.getData()));
        } catch (NullPointerException e) {
            Log.e("ProgramacaoDAO", "toContentValues:dia vazio" + e.toString());
            values.put(DBEntries.ProgramacaoEntry.COLUMN_NAME_DATA, dateFormat.format(new Date(0,0,0)));
        }
        values.put(DBEntries.ProgramacaoEntry.COLUMN_EVEN_ID, entity.getEvento().getId());
        return values;
    }
}
