package br.ufg.iiisea.sea.dao;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.bean.Programacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by fellipe on 30/09/16.
 */
public class PalestraDAO extends AbstractDAO<Palestra> {

    public PalestraDAO(Context context) {
        super(context);
    }

    public List<Palestra> getPalestrasPorProgramacao(Programacao programacao) {
        if(programacao == null || programacao.getId() == 0)
            return null;
        return query("SELECT * FROM "+ getTableName() + " WHERE "
                +DBEntries.PalestraEntry.COLUMN_PROG_ID
                +" = " + programacao.getId());
    }

    public boolean existePalestra(long id) {
        if(id <= 0)
            return false;
        List<Palestra> results = query("SELECT * FROM "+getTableName()+" WHERE "
                + DBEntries.PalestraEntry.COLUMN_ID + " = "
                + id);
        if(!results.isEmpty() && results.get(0).getId() == id)
            return true;
        return false;
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
        Palestra entity = new Palestra();

        entity.setId(contentValues.getAsInteger(DBEntries.PalestraEntry.COLUMN_ID));
        entity.setDescricao(contentValues.getAsString(DBEntries.PalestraEntry.COLUMN_NAME_DESCRICAO));
        entity.setLugar(contentValues.getAsString(DBEntries.PalestraEntry.COLUMN_NAME_LUGAR));
        entity.setNome(contentValues.getAsString(DBEntries.PalestraEntry.COLUMN_NAME_NOME));

        entity.setPalestrante(contentValues.getAsString(DBEntries.PalestraEntry.COLUMN_NAME_PALESTRANTE));
        entity.setCodigoQrCode(contentValues.getAsString(DBEntries.PalestraEntry.COLUMN_NAME_CODIGOQRCODE));

        Programacao programacao = new Programacao();
        programacao.setId(contentValues.getAsInteger(DBEntries.PalestraEntry.COLUMN_PROG_ID));
        entity.setProgramacao(programacao);

        try {
            entity.setTipo(Palestra.Tipo.valueOf(contentValues.getAsString(DBEntries.PalestraEntry.COLUMN_NAME_TIPO)));
        } catch (IllegalArgumentException ex) {
            Log.e("PalestraDAO", "toEntity: o enum que veio do sql é nao tem um tipo definido. Verificar Backendless tabelas.");
            entity.setTipo(Palestra.Tipo.UNDEFINED);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        try {
            Date horaFim = dateFormat.parse(contentValues.getAsString(DBEntries.PalestraEntry.COLUMN_NAME_HORA_FIM));
            Date horaInicio = dateFormat.parse(contentValues.getAsString(DBEntries.PalestraEntry.COLUMN_NAME_HORA_INICIO));

            entity.setHoraFim(horaFim);
            entity.setHoraInicio(horaInicio);
        } catch (NullPointerException|ParseException ex) {
            Log.e("Palestra:toEntity", ex.toString());
            entity.setHoraInicio(null);
            entity.setHoraFim(null);
        }

        return entity;
    }

    @Override
    public ContentValues toContentValues(Palestra entity) {
        ContentValues values = new ContentValues();
        values.put(DBEntries.PalestraEntry.COLUMN_ID, entity.getId());
        values.put(DBEntries.PalestraEntry.COLUMN_NAME_NOME, entity.getNome());
        values.put(DBEntries.PalestraEntry.COLUMN_NAME_DESCRICAO, entity.getDescricao());
        values.put(DBEntries.PalestraEntry.COLUMN_NAME_LUGAR, entity.getLugar());
        try {
            values.put(DBEntries.PalestraEntry.COLUMN_PROG_ID, entity.getProgramacao().getId());
        } catch (NullPointerException ex) {
            Log.e("PalestraDAO", "toContentValues: não foi passada uma programação para entity que é uma chave estrangeira. "+ex);
        }
        values.put(DBEntries.PalestraEntry.COLUMN_NAME_TIPO, entity.getTipo().toString());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dateFormat.setLenient(false);
        if(entity.getHoraFim() == null) {
            entity.setHoraFim(new Date(0,0,0));
        }
        if(entity.getHoraInicio() == null) {
            entity.setHoraInicio(new Date(0,0,0));
        }
        String horaFim = dateFormat.format(entity.getHoraFim());
        String horaInicio = dateFormat.format(entity.getHoraInicio());
        values.put(DBEntries.PalestraEntry.COLUMN_NAME_HORA_FIM, horaFim);
        values.put(DBEntries.PalestraEntry.COLUMN_NAME_HORA_INICIO, horaInicio);
        values.put(DBEntries.PalestraEntry.COLUMN_NAME_CODIGOQRCODE, entity.getCodigoQrCode());
        values.put(DBEntries.PalestraEntry.COLUMN_NAME_PALESTRANTE, entity.getPalestrante());

        return values;
    }
}
