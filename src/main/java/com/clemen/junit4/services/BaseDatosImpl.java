package com.clemen.junit4.services;


import com.clemen.junit4.entities.Articulo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BaseDatosImpl implements BaseDatos{

    private Map<Integer, Articulo> bd ;

    @Override
    public void iniciar() {
        bd = new HashMap<>();
        Articulo a = new Articulo("camiseta",29.90);
        Articulo b = new Articulo("pantalon ",39.90);
        bd.put(1,a);
        bd.put(2,b);
        bd.put(3, new Articulo("chaleco",19.90));
        bd.put(4, new Articulo("corbata",12.90));
        bd.put(5, new Articulo("zapatos",44.90));

    }


    @Override
    public Integer insertarArticulo(Articulo a) {
        bd.put(bd.size()+1,a);
        return bd.size();
    }

    @Override
    public Articulo buscarArticulo(Integer i) {
        return bd.get(i);
    }
}
