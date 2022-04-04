package com.clemen.junit4.services;

import com.clemen.junit4.entities.Articulo;

public interface BaseDatos {
    public void iniciar();
    public Integer insertarArticulo(Articulo a);
    public Articulo buscarArticulo(Integer i);
}
