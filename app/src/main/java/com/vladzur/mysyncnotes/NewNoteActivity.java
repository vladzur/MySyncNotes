package com.vladzur.mysyncnotes;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.vladzur.mysyncnotes.Model.Note;


public class NewNoteActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_note, menu);
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
        Note nota = new Note(this);
        EditText title = (EditText) findViewById(R.id.editTitle);
        EditText body = (EditText) findViewById(R.id.editBody);
        nota.Create();
        nota.setTitle(title.getText().toString());
        nota.setBody(body.getText().toString());
        nota.Save();
        finish();
    }
}
