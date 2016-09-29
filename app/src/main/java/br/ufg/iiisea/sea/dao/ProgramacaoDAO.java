package br.ufg.iiisea.sea.dao;

import android.content.ContentValues;
import android.content.Context;
import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Programacao;

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
        //entity.setDia();
        entity.setEvento(new Evento(contentValues.getAsInteger(DBEntries.ProgramacaoEntry.COLUMN_EVEN_ID)));

        return entity;
    }

    @Override
    public ContentValues toContentValues(Programacao entity) {

        ContentValues values = new ContentValues();
        values.put(DBEntries.ProgramacaoEntry.COLUMN_ID, entity.getId());
        values.put(DBEntries.ProgramacaoEntry.COLUMN_NAME_DESCRICAO, entity.getDescricao());
//        values.put(DBEntries.ProgramacaoEntry.COLUMN_NAME_DIA);
        values.put(DBEntries.ProgramacaoEntry.COLUMN_EVEN_ID, entity.getEvento().getId());
        return values;
    }
}
