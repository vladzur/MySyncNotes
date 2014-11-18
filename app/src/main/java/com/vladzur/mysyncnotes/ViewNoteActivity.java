package com.vladzur.mysyncnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.vladzur.mysyncnotes.Model.Note;


public class ViewNoteActivity extends ActionBarActivity {

    private String id;
    private Note nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        Intent intent = getIntent();
        this.id = intent.getStringExtra("id");
        nota = new Note(this);
        nota.Read(this.id);
        TextView title = (TextView) findViewById(R.id.textTitle);
        TextView body = (TextView) findViewById(R.id.textBody);
        title.setText(nota.getTitle());
        body.setText(nota.getBody());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_note, menu);
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
}
