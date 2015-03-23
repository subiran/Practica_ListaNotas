package com.example.ana.listanotas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;


public class AddEditNotas extends Activity {

    long Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_notas);

        Intent i = getIntent();

        Id = i.getLongExtra(ListaNotasAcitvity.db.DB_LIST_ID, -1);//si estás vacío ponle "-1"
        String Titulo = i.getStringExtra(ListaNotasAcitvity.db.DB_LIST_TITLE);
        String Nota = i.getStringExtra(ListaNotasAcitvity.db.DB_LIST_NOTE);

        EditText cuadro_Titulo = (EditText) findViewById(R.id.titaddedit);
        EditText cuadro_Nota = (EditText) findViewById(R.id.noteaddedit);

        cuadro_Titulo.setText(Titulo);
        cuadro_Nota.setText(Nota);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_edit_notas, menu);
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

    public void Guardar(View v){
        EditText cuadro_titulo_nota;
        EditText cuadro_nota_en_si;

        cuadro_titulo_nota = (EditText) findViewById(R.id.titaddedit);
        cuadro_nota_en_si = (EditText) findViewById(R.id.noteaddedit);

        String titulo_nota;
        String nota_en_si;

        titulo_nota = cuadro_titulo_nota.getText().toString();
        nota_en_si = cuadro_nota_en_si.getText().toString();

        if(titulo_nota != "" || nota_en_si != ""){
            Intent in = new Intent(this, ListaNotasAcitvity.class);
            if(Id <= -1){
                ListaNotasAcitvity.db.addNote(titulo_nota, nota_en_si);//ListaNotasAcitvity.db.addNote()--> ejecuta la función addNote de db
            }
            else{
                ListaNotasAcitvity.db.updateNote(Id, titulo_nota, nota_en_si);
            }
            startActivity(in);
        }

    }

}
