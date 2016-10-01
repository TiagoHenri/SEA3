package br.ufg.iiisea.sea.dao;

import android.content.ContentValues;
import android.content.Context;
import br.ufg.iiisea.sea.bean.Palestrante;

/**
 * Created by fellipe on 30/09/16.
 */
public class PalestranteDAO extends AbstractDAO<Palestrante> {

    public PalestranteDAO(Context context) {
        super(context);
    }

    @Override
    public String getTableName() {
        return DBEntries.PalestranteEntry.TABLE_NAME;
    }

    @Override
    public String getKeyPrimaryColumnName() {
        return DBEntries.PalestranteEntry.COLUMN_ID;
    }

    @Override
    public Palestrante toEntity(ContentValues contentValues) {
        Palestrante entity = new Palestrante();
        entity.setId(contentValues.getAsInteger(DBEntries.PalestranteEntry.COLUMN_ID));
        entity.setNome(contentValues.getAsString(DBEntries.PalestranteEntry.COLUMN_NAME_NOME));
        entity.setBiografia(contentValues.getAsString(DBEntries.PalestranteEntry.COLUMN_NAME_BIOGRAFIA));
        return entity;
    }

    @Override
    public ContentValues toContentValues(Palestrante entity) {
        ContentValues values = new ContentValues();
        values.put(DBEntries.PalestranteEntry.COLUMN_ID, entity.getId());
        values.put(DBEntries.PalestranteEntry.COLUMN_NAME_NOME, entity.getNome());
        values.put(DBEntries.PalestranteEntry.COLUMN_NAME_BIOGRAFIA, entity.getBiografia());

        return values;
    }
}
