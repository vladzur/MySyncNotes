package com.vladzur.mysyncnotes;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vladzur.mysyncnotes.Database.DatabaseHelper;
import com.vladzur.mysyncnotes.Model.Note;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHelper Dbh = new DatabaseHelper(this);
        try {
            Dbh.createDataBase();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            System.exit(1);
        }
        setContentView(R.layout.activity_main);
        final ListView listaNotas = (ListView) findViewById(R.id.listView);
        Note nota = new Note(this);
        ArrayList<Note> notas = nota.All();
        NoteListAdapter adapter = new NoteListAdapter(this, notas);
        listaNotas.setAdapter(adapter);
        listaNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note item = (Note) parent.getItemAtPosition(position);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_new) {

        }

        return super.onOptionsItemSelected(item);
    }
}
