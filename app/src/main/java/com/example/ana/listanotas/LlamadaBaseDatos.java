package com.example.ana.listanotas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Ana on 22/03/2015.
 */

public class LlamadaBaseDatos extends SQLiteOpenHelper {

    public static final  int DB_VERSION = 1;
    public static final String DB_NAME = "com.example.ana.ListaNotasActivity.Notas.db";
    public static final String DB_TNAME = "com.example.ana.ListaNotasActivity.Notas";
    public static final String DB_ID = BaseColumns._ID;
    public static final String DB_TITLE = "com.example.ana.ListaNotasActivity.Titulo";
    public static final String DB_NOTE = "com.example.ana.ListaNotasActivity.Nota";

    //Las siguientes variables no es necesario tenerlas aquí, no es info de la base de datos pero tienen inforamción de ella.
    public static final String DB_LIST_ID = "com.example.ana.ListaNotasActivity.id";
    public static final String DB_LIST_TITLE = "com.example.ana.ListaNotasActivity.titulo";
    public static final String DB_LIST_NOTE = "com.example.ana.ListaNotasActivity.nota";

    public LlamadaBaseDatos(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ DB_TNAME + "("+ DB_ID + " INTEGER PRIMARY KEY, " + DB_TITLE + " TEXT, " + DB_NOTE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addNote(String nota, String texto) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.putNull(DB_ID);
        values.put(DB_TITLE, nota);
        values.put(DB_NOTE, texto);

        db.insert(DB_TNAME, null, values);
    }

    public Cursor readNotes(){
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {DB_ID, DB_TITLE, DB_NOTE};

        return db.query(DB_TNAME, columns, null, null, null, null, null);
    }

    public void updateNote(long id, String title, String body){
        SQLiteDatabase db = getWritableDatabase();
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(DB_LIST_TITLE, title);
        values.put(DB_LIST_NOTE, body);
        // Which row to update, based on the ID
        String selection = DB_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };
        int count = db.update(
                DB_TNAME,
                values,
                selection,
                selectionArgs);
    }
}
