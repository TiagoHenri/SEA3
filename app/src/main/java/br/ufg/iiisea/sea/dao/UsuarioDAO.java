package br.ufg.iiisea.sea.dao;

import android.content.ContentValues;
import android.content.Context;
import br.ufg.iiisea.sea.bean.Usuario;

/**
 * Created by fellipe on 22/09/16.
 */
public class UsuarioDAO extends AbstractDAO<Usuario> {

    public UsuarioDAO(Context context) {
        super(context);
    }

    @Override
    public String getTableName() {
        return DBEntries.UsuarioEntry.TABLE_NAME;
    }

    @Override
    public String getKeyPrimaryColumnName() {
        return DBEntries.UsuarioEntry.COLUMN_ID;
    }

//    @Override
//    public String getSQLCreateEntries() {
//        return DBEntries.UsuarioEntry.SQL_CREATE_ENTRIES;
//    }
//
//    @Override
//    public String getSQLDeleteEntries() {
//        return DBEntries.UsuarioEntry.SQL_DELETE_ENTRIES;
//    }

    @Override
    public Usuario toEntity(ContentValues contentValues) {
        Usuario entity = new Usuario();
        entity.setId(contentValues.getAsInteger(DBEntries.UsuarioEntry.COLUMN_ID));
        entity.setEmail(contentValues.getAsString(DBEntries.UsuarioEntry.COLUMN_NAME_EMAIL));
        entity.setNome(contentValues.getAsString(DBEntries.UsuarioEntry.COLUMN_NAME_NOME));
        entity.setSenha(contentValues.getAsString(DBEntries.UsuarioEntry.COLUMN_NAME_SENHA));
        return entity;
    }

    @Override
    public ContentValues toContentValues(Usuario entity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBEntries.UsuarioEntry.COLUMN_ID, entity.getId());
        contentValues.put(DBEntries.UsuarioEntry.COLUMN_NAME_EMAIL, entity.getEmail());
        contentValues.put(DBEntries.UsuarioEntry.COLUMN_NAME_NOME, entity.getNome());
        contentValues.put(DBEntries.UsuarioEntry.COLUMN_NAME_SENHA, entity.getSenha());
        return contentValues;
    }
}
