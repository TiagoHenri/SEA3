package br.ufg.iiisea.sea.utils;

/**
 * Created by fellipe on 28/09/16.
 */
public interface MutableBean<T> extends Comparable<T> {
    long getId();
    void setId(long id);
    boolean equals(Object o);
}
