package com.vladzur.mysyncnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.vladzur.mysyncnotes.Model.Note;


public class EditNoteActivity extends ActionBarActivity {

    private Note nota;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Intent intent = getIntent();
        this.id = intent.getStringExtra("id");
        nota = new Note(this);
        nota.Read(this.id);
        EditText title = (EditText) findViewById(R.id.editTitle);
        EditText body = (EditText) findViewById(R.id.editBody);
        title.setText(nota.getTitle());
        body.setText(nota.getBody());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            SaveNote();
        }

        return super.onOptionsItemSelected(item);
    }

    public void SaveNote() {
        EditText title = (EditText) findViewById(R.id.editTitle);
        EditText body = (EditText) findViewById(R.id.editBody);
        nota.setTitle(title.getText().toString());
        nota.setBody(body.getText().toString());
        nota.Save();
        finish();
    }
}
