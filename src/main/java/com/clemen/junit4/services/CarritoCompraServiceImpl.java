package com.clemen.junit4.services;

import com.clemen.junit4.entities.Articulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoCompraServiceImpl implements CarritoCompraService {

    public List<Articulo> cesta = new ArrayList<>();

    @Autowired
    private BaseDatos bd;

    @Override
    public void limpiarCesta() {
        cesta.clear();
    }

    @Override
    public void addArticulo(Articulo articulo) {
        cesta.add(articulo);
    }

    @Override
    public Integer getNumArticulos() {
        return cesta.size();
    }

    @Override
    public List<Articulo> getArticulos() {
        return cesta;
    }

    @Override
    public Double totalPrice() {
        Double total = 0.0;
        for (Articulo a : cesta) {
            total += a.getPrecio();
        }
        return total;
    }

    @Override
    public Double calculadorDescuento(Double precio, Double porcentajeDescuento) {
        return precio - (precio * porcentajeDescuento / 100);
    }

    @Override
    public Double aplicarDescuento(Integer i, Double porcentaje) {
        //bd.iniciar();

        Articulo a = bd.buscarArticulo(i);
        if(Optional.ofNullable(a).isPresent())
            return calculadorDescuento(a.getPrecio(), porcentaje);
        System.out.println("Articulo " + i + "no encontrado.");
        return 0.0;
    }

    @Override
    public int insertarArticulo(Articulo a) {
        addArticulo(a);
        return bd.insertarArticulo(a);
    }

    @Override
    public Articulo verArticulo(Integer id) {
            return cesta.get(id);
    }

}
