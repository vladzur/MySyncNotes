package com.vladzur.mysyncnotes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
        updateContent();
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
            Intent intent = new Intent(this, NewNoteActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateContent();
    }

    public void updateContent() {
        ListView listaNotas = (ListView) findViewById(R.id.listView);
        Note nota = new Note(this);
        ArrayList<Note> notas = nota.All();
        NoteListAdapter adapter = new NoteListAdapter(this, notas);
        listaNotas.setAdapter(adapter);
        //OnClickListener
        listaNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note item = (Note) parent.getItemAtPosition(position);
                Intent intent = new Intent(parent.getContext(), ViewNoteActivity.class);
                intent.putExtra("id", item.getId());
                startActivity(intent);
            }
        });
        //OnLongClickListener
        listaNotas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Note item = (Note) parent.getItemAtPosition(position);
                DeleteNote(item.getId());
                return false;
            }
        });
    }

    public void DeleteNote(String id) {
        final Note note = new Note(this);
        note.Read(id);
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete_dialog_title)
                .setMessage(R.string.delete_dialog_message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        note.Delete();
                        updateContent();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
