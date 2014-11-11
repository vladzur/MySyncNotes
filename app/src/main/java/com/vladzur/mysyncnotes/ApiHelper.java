package com.vladzur.mysyncnotes;

import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.vladzur.mysyncnotes.Model.ApiResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by vladzur on 07-11-14.
 */
public class ApiHelper {

    private final String TAG = "ApiHelper";
    private final Gson gson = new Gson();

    ApiHelper() {

    }

    public boolean ping(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
        return true;
    }

    public JSONArray callAPIList(String url) {
        Log.d(TAG, url);
        String resp = "";
        JSONArray returned = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            resp = response.body().string();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return returned;
        }
        try {
            returned = new JSONArray(resp);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        return returned;
    }

    public JSONObject callAPIObject(String url) {
        Log.d(TAG, url);
        String resp = "";
        JSONObject returned = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            resp = response.body().string();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return returned;
        }
        try {
            returned = new JSONObject(resp);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        return returned;
    }

    public Boolean callPost(String url, HashMap params) {
        ApiResponse respuesta;
        Log.d(TAG, url);
        String resp = "";
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        FormEncodingBuilder form = new FormEncodingBuilder();
        Iterator iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry e = (Map.Entry) iterator.next();
            String clave = e.getKey().toString();
            String valor;
            try {
                valor = e.getValue().toString();
            } catch (Exception ex) {
                valor = "null";
            }
            Log.d(TAG, clave + "=" + valor + "&");
            form.add(clave, valor);
        }
        RequestBody formBody = form.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            resp = response.body().string();
            Log.d(TAG, resp);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
        respuesta = gson.fromJson(resp, ApiResponse.class);
        if (respuesta.getEstado() == 0) {
            return true;
        } else {
            return false;
        }
    }

}
