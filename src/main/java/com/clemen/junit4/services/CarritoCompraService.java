package com.clemen.junit4.services;

import com.clemen.junit4.entities.Articulo;

import java.util.List;

public interface CarritoCompraService {




    public void limpiarCesta();


    public void addArticulo(Articulo articulo);

    public Integer getNumArticulos();

    public List<Articulo> getArticulos();

    public Double totalPrice();

    public Double calculadorDescuento(Double precio, Double porcentajeDescuento);


    Double aplicarDescuento(Integer i, Double porcentaje);

    int insertarArticulo(Articulo a);

    Articulo verArticulo(Integer id);
}
