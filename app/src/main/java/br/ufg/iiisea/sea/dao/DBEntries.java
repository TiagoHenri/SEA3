package br.ufg.iiisea.sea.dao;

import android.provider.BaseColumns;
import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Palestra;

/**
 * Created by fellipe on 22/09/16.
 */
public final class DBEntries {

    public static final String BD_NOME = "SEA3UFG";
    public static final int    BD_VERSION = 9;

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
                        + COLUMN_NAME_DATA_FIM + " DATE);" +
                "" +
                "INSERT INTO "+TABLE_NAME+" ("+COLUMN_ID+", "+COLUMN_NAME_NOME+", "+COLUMN_NAME_DESCRICAO+", "+COLUMN_NAME_DATA_INICIO+", "+COLUMN_NAME_DATA_FIM+") " +
                "VALUES (1, 'Simpósio de Empreendedorismo Ambiental', '', '2016-10-06', '2016-10-07');";
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

    public final class ProgramacaoEntry {
        public static final String TABLE_NAME            = "tb_programacao";
        public static final String COLUMN_ID             = "prog_id";
        public static final String COLUMN_EVEN_ID        = "prog_even_id";
        public static final String COLUMN_NAME_DATA       = "prog_dia";
        public static final String COLUMN_NAME_DESCRICAO = "prog_descricao";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_EVEN_ID + " INTEGER, "
                        + COLUMN_NAME_DATA + " DATE, "
                        + COLUMN_NAME_DESCRICAO + " TEXT, "
                        + "FOREIGN KEY("+COLUMN_EVEN_ID+") REFERENCES "
                        + EventoEntry.TABLE_NAME+"("+ EventoEntry.COLUMN_ID +")"
                        +")";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public final class PalestraEntry {
        public static final String TABLE_NAME              = "tb_palestra";
        public static final String COLUMN_ID               = "para_id";
        public static final String COLUMN_NAME_NOME        = "para_nome";
        public static final String COLUMN_NAME_DESCRICAO   = "para_descricao";
        public static final String COLUMN_NAME_LUGAR       = "para_lugar";
        public static final String COLUMN_PROG_ID          = "para_prog_id";
        public static final String COLUMN_NAME_HORA_INICIO = "para_hora_inicio";
        public static final String COLUMN_NAME_HORA_FIM    = "para_hora_fim";
        public static final String COLUMN_NAME_TIPO        = "para_tipo";
        public static final String COLUMN_NAME_PALESTRANTE = "para_palestrante";
        public static final String COLUMN_NAME_CODIGOQRCODE= "para_codigo_qrcode";
        public static final String SQL_CREATE_ENTRIES      =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_NAME_NOME + " TEXT, "
                        + COLUMN_NAME_DESCRICAO + " TEXT, "
                        + COLUMN_NAME_LUGAR + " TEXT, "
                        + COLUMN_PROG_ID + " INTEGER, "
                        + COLUMN_NAME_HORA_INICIO + " DATETIME, "
                        + COLUMN_NAME_HORA_FIM + " DATETIME, "
                        + COLUMN_NAME_TIPO + " TEXT, "
                        + COLUMN_NAME_CODIGOQRCODE + " TEXT, "
                        + COLUMN_NAME_PALESTRANTE + " TEXT, "
                        + "FOREIGN KEY("+COLUMN_PROG_ID+") REFERENCES "
                        + ProgramacaoEntry.TABLE_NAME+"("+ ProgramacaoEntry.COLUMN_ID +")"
                        +")";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    @Deprecated
    public final class PalestranteEntry {
        public static final String TABLE_NAME            = "tb_palestrante";
        public static final String COLUMN_ID             = "pate_id";
        public static final String COLUMN_NAME_NOME      = "pate_nome";
        public static final String COLUMN_NAME_BIOGRAFIA = "pate_biografia";
        public static final String COLUMN_PARA_ID        = "pate_para_id";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_NAME_NOME + " TEXT NOT NULL, "
                        + COLUMN_NAME_BIOGRAFIA +" TEXT, "
                        + COLUMN_PARA_ID + " INTEGER)";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

    @Deprecated
    public final class PalestraPalestranteEntry {
        public static final String TABLE_NAME          = "tb_para_pate";
        public static final String COLUMN_ID          = "para_pate_id";
        public static final String COLUMN_PARA_ID      = "para_pate_para_id";
        public static final String COLUMN_PATE_ID      = "para_pate_pate_id";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_PARA_ID + " INTEGER, "
                        + COLUMN_PATE_ID + " INTEGER, "
                        + "FOREIGN KEY("+COLUMN_PATE_ID+") REFERENCES "
                        + PalestranteEntry.TABLE_NAME+"("+ PalestranteEntry.COLUMN_ID +"), "
                        + "FOREIGN KEY("+COLUMN_PARA_ID+") REFERENCES "
                        + PalestraEntry.TABLE_NAME+"("+ PalestraEntry.COLUMN_ID +")"
                        +")";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
 }
