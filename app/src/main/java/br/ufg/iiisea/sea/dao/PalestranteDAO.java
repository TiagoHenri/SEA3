package br.ufg.iiisea.sea.dao;

import android.content.ContentValues;
import android.content.Context;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.bean.Palestrante;
import br.ufg.iiisea.sea.bean.Programacao;

import java.util.List;

/**
 * Created by fellipe on 30/09/16.
 */
public class PalestranteDAO extends AbstractDAO<Palestrante> {

    public PalestranteDAO(Context context) {
        super(context);
    }

    @Deprecated
    public List<Palestrante> getPalestrasPorProgramacao(Programacao programacao) {
        if(programacao == null || programacao.getId() == 0)
            return null;
        return query("SELECT * FROM "+getTableName()+" WHERE "+DBEntries.PalestranteEntry.COLUMN_ID
                         +" IN (SELECT "+DBEntries.PalestraPalestranteEntry.COLUMN_PATE_ID+" FROM " + DBEntries.PalestraPalestranteEntry.TABLE_NAME + " WHERE "
                                + DBEntries.PalestraPalestranteEntry.COLUMN_PARA_ID
                                + " IN ( SELECT "+ DBEntries.PalestraEntry.COLUMN_ID+" FROM "+ DBEntries.PalestraEntry.TABLE_NAME+" WHERE "
                                                 + DBEntries.PalestraEntry.COLUMN_PROG_ID +" = "+ programacao.getId()+"))");
    }

    public List<Palestrante> getPalestrantePorPalestra(Palestra palestra) {
        return query("SELECT * FROM "+getTableName()+" WHERE "
                        + DBEntries.PalestranteEntry.COLUMN_PARA_ID + " = "+palestra.getId());
    }
    public boolean existePalestrante(long id) {
        if(id <= 0)
            return false;
        List<Palestrante> results = query("SELECT * FROM "+getTableName()+" WHERE "
                + DBEntries.PalestranteEntry.COLUMN_ID + " = "
                + id);
        if(!results.isEmpty() && results.get(0).getId() == id)
            return true;
        return false;
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
