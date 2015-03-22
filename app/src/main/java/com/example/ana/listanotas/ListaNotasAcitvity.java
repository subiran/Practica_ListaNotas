package com.example.ana.listanotas;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class ListaNotasAcitvity extends Activity implements AdapterView.OnItemClickListener{

    public static LlamadaBaseDatos db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas_acitvity);

        db = new LlamadaBaseDatos(this);

        Cursor c = db.readNotes();//cursor --> controlador que permite navegar por los resultados

        String[] fromColumns = {db.DB_ID, db.DB_TITLE, db.DB_NOTE};
        int[] toViews = {R.id.getTitulo, R.id.getTexto};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.lista_guardada_base_datos,
                c,
                fromColumns,
                toViews,
                0);

        ListView l = (ListView) findViewById(R.id.lista);
        l.setAdapter(adapter);
        l.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_notas_acitvity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void AddNota(View view){
        Intent in = new Intent(this, AddEditNotas.class);
        startActivity(in);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in = new Intent(this, AddEditNotas.class);

        TextView CogeTituloBS = (TextView) findViewById(R.id.getTitulo);
        TextView CogeTextoBS = (TextView) findViewById(R.id.getTexto);

        String titulo = CogeTituloBS.getText().toString();
        String texto = CogeTextoBS.getText().toString();

        in.putExtra(db.DB_LIST_ID, id);//id vendría a ser uuna característica de DB_LIST_ID
        in.putExtra(db.DB_LIST_TITLE, titulo);//es la variable de arriba
        in.putExtra(db.DB_LIST_NOTE, texto);

        startActivity(in);
    }
}
