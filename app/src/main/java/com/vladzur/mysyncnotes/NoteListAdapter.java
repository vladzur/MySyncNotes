package com.vladzur.mysyncnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vladzur.mysyncnotes.Model.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by vladzur on 10-11-14.
 */
public class NoteListAdapter extends BaseAdapter {

    private ArrayList<Note> listado;
    private LayoutInflater inflater;
    private Context context;

    public NoteListAdapter(Context c, ArrayList<Note> list) {
        listado = list;
        inflater = LayoutInflater.from(c);
        context = c;
    }

    @Override
    public int getCount() {
        return listado.size();
    }

    @Override
    public Object getItem(int i) {
        return listado.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.titulo = (TextView) view.findViewById(R.id.titulo);
            holder.fecha = (TextView) view.findViewById(R.id.fecha);
            holder.cuerpo = (TextView) view.findViewById(R.id.cuerpo);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Note l = (Note) getItem(i);
        Date created = new Date(l.getCreated());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        holder.titulo.setText(l.getTitle());
        holder.fecha.setText(sdf.format(created));
        holder.cuerpo.setText(l.getBody());

        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public static class ViewHolder {
        public TextView titulo;
        public TextView fecha;
        public TextView cuerpo;
    }
}
