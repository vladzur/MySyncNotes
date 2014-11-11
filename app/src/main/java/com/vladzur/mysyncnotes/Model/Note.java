package com.vladzur.mysyncnotes.Model;

import android.content.ContentValues;
import android.content.Context;

import java.util.UUID;

/**
 * Created by vladzur on 07-11-14.
 */
public class Note extends BaseModel {

    private String id;
    private String title;
    private String body;
    private long created;
    private long updated;

    public Note() {
    }

    public Note(Context c) {
        super(c);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public void Create() {
        setId(UUID.randomUUID().toString());
        setCreated(System.currentTimeMillis());
    }

    public void Save() {
        Db = Dbh.getWritableDatabase();
        ContentValues registro = new ContentValues();
        setUpdated(System.currentTimeMillis());
        registro.put("id", getId());
        registro.put("title", getTitle());
        registro.put("body", getBody());
        registro.put("created", getCreated());
        registro.put("updated", getUpdated());
        Db.replace("notes", null, registro);
        Db.close();
    }
}
