package tienda.models.impl;

import java.util.ArrayList;

public class CaretakerPrecio {

    private String id;
    private int contadorMementos;
    private ArrayList<MementoPrecio> mementos = new ArrayList<>();

    public CaretakerPrecio()    {
        this.contadorMementos = 0;
    }

    public void addMementoPrecio(MementoPrecio m) {
        mementos.add(m);
        this.contadorMementos++;
    }

    public MementoPrecio ultimoMementoPrecio() {
        return mementos.get(contadorMementos-1);
    }

    public MementoPrecio unMementoPrecio(int historia) {
        return mementos.get(historia);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
