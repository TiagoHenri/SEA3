package br.ufg.iiisea.sea.dao;

import android.content.ContentValues;
import android.content.Context;
import br.ufg.iiisea.sea.bean.Palestra;

/**
 * Created by fellipe on 30/09/16.
 */
public class PalestraDAO extends AbstractDAO<Palestra> {

    public PalestraDAO(Context context) {
        super(context);
    }

    @Override
    public String getTableName() {
        return DBEntries.PalestraEntry.TABLE_NAME;
    }

    @Override
    public String getKeyPrimaryColumnName() {
        return DBEntries.PalestraEntry.COLUMN_ID;
    }

    @Override
    public Palestra toEntity(ContentValues contentValues) {
        return null;
    }

    @Override
    public ContentValues toContentValues(Palestra entity) {
        return null;
    }
}
