package com.vladzur.mysyncnotes.Model;

/**
 * Created by vladzur on 07-11-14.
 */
public class ApiResponse extends BaseModel {

    private int estado;
    private String mensaje;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
