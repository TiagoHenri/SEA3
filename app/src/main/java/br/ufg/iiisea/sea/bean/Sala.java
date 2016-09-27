package br.ufg.iiisea.sea.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fellipe on 14/09/16.
 */
public class Sala {
    private String id;
    private String lugar;

    public Sala(String id, String lugar) {
        this.id = id;
        this.lugar = lugar;
    }
}
