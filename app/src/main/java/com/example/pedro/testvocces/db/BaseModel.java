package com.example.pedro.testvocces.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Clase Base encargada de abrir y cerrar la conexin entre otras cosas de la
 * BD. Los dems model extendern de esta clase
 *
 * @author Fale
 *
 */
public class BaseModel {

    public static final String DATE_FORMAT_BD_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * Abre conexin con la BD local en el modo que se le indique(Lectura/Escritura)
     *
     * @param context
     * @param modo    0 lectura o 1 escritura
     * @return SQLiteDatabase una instancia de la BD
     */
    public static SQLiteDatabase abrirConexion(Context context, int modo) {
        SQLiteDatabase bd;
        NucleoBD ndbh = new NucleoBD(context);
        if (modo == 0) {//Lectura
            bd = ndbh.getWritableDatabase();
        } else {//Escritura
            bd = ndbh.getReadableDatabase();
        }
        return bd;
    }

    /**
     * Cierra la conexin con la BD
     *
     * @param db
     */
    public static void cerrarConexion(SQLiteDatabase db) {
        db.close();
    }
}
