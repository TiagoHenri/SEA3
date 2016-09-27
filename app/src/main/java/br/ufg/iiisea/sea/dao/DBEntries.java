package br.ufg.iiisea.sea.dao;

import android.provider.BaseColumns;
import br.ufg.iiisea.sea.bean.Evento;

/**
 * Created by fellipe on 22/09/16.
 */
public final class DBEntries {

    public static final String BD_NOME = "SEA3UFG.DB";
    public static final int    BD_VERSION = 4;

    private DBEntries() {
    }

    public final class UsuarioEntry {
        public static final String TABLE_NAME        = "tb_usuario";
        public static final String COLUMN_ID         = "usua_id";
        public static final String COLUMN_NAME_NOME  = "usua_nome";
        public static final String COLUMN_NAME_EMAIL = "usua_email";
        public static final String COLUMN_NAME_SENHA = "usua_senha";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_NAME_NOME + " TEXT NOT NULL, "
                        + COLUMN_NAME_EMAIL + " TEXT UNIQUE, "
                        + COLUMN_NAME_SENHA + " TEXT NOT NULL)";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }


    public final class EventoEntry {
        public static final String TABLE_NAME              = "tb_evento";
        public static final String COLUMN_ID               = "even_id";
        public static final String COLUMN_NAME_NOME        = "even_nome";
        public static final String COLUMN_NAME_DESCRICAO   = "even_descricao";
        public static final String COLUMN_NAME_DATA_INICIO = "even_data_inicio";
        public static final String COLUMN_NAME_DATA_FIM    = "even_data_fim";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_NAME_NOME + " TEXT NOT NULL, "
                        + COLUMN_NAME_DESCRICAO + " TEXT, "
                        + COLUMN_NAME_DATA_INICIO + " DATE, "
                        + COLUMN_NAME_DATA_FIM + " DATE)";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public final class NoticiaEntry {
        public static final String TABLE_NAME           = "tb_noticia";
        public static final String COLUMN_ID            = "noti_id";
        public static final String COLUMN_EVEN_ID       = "noti_even_id";
        public static final String COLUMN_NAME_TITULO   = "noti_titulo";
        public static final String COLUMN_NAME_CONTEUDO = "noti_conteudo";
        public static final String COLUMN_NAME_DATA     = "noti_data";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_EVEN_ID + " INTEGER, "
                        + COLUMN_NAME_TITULO + " TEXT NOT NULL, "
                        + COLUMN_NAME_CONTEUDO + " TEXT NOT NULL, "
                        + COLUMN_NAME_DATA + " DATETIME, "
                        + "FOREIGN KEY("+COLUMN_EVEN_ID+") REFERENCES "
                                + EventoEntry.TABLE_NAME+"("+ EventoEntry.COLUMN_ID +")"
                        +")";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

    public final class PalestraEntry {
        public static final String TABLE_NAME              = "tb_palestra";
        public static final String COLUMN_NAME_ID          = "para_id";
        public static final String COLUMN_NAME_NOME        = "para_nome";
        public static final String COLUMN_NAME_DESCRICAO   = "para_descricao";
        public static final String COLUMN_NAME_SALA_ID     = "para_sala_id";
        public static final String COLUMN_NAME_PROG_ID     = "para_prog_id";
        public static final String COLUMN_NAME_HORA_INICIO = "para_hora_inicio";
        public static final String COLUMN_NAME_HORA_FIM    = "para_hora_fim";
        public static final String COLUMN_NAME_TIPO        = "para_tipo";
    }

    public final class PalestranteEntry {
        public static final String TABLE_NAME            = "tb_palestrante";
        public static final String COLUMN_NAME_ID        = "pate_id";
        public static final String COLUMN_NAME_NOME      = "pate_nome";
        public static final String COLUMN_NAME_BIOGRAFIA = "pate_biografia";
    }

    public final class PalestraPalestranteEntry {
        public static final String TABLE_NAME          = "tb_para_pate";
        public static final String COLUMN_NAME_ID      = "para_pate_id";
        public static final String COLUMN_NAME_PARA_ID = "para_pate_para_id";
        public static final String COLUMN_NAME_PATE_ID = "para_pate_pate_id";
    }

    public final class ProgramacaoEntry {
        public static final String TABLE_NAME            = "tb_programacao";
        public static final String COLUMN_NAME_ID        = "prog_id";
        public static final String COLUMN_NAME_DIA       = "prog_dia";
        public static final String COLUMN_NAME_DESCRICAO = "prog_descricao";
        public static final String COLUMN_NAME_EVEN_ID   = "prog_even_id";
    }
 }
