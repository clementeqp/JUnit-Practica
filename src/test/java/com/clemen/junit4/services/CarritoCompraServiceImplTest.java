package com.clemen.junit4.services;

import com.clemen.junit4.entities.Articulo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class CarritoCompraServiceImplTest {

    @InjectMocks
    private final CarritoCompraService carritoService = new CarritoCompraServiceImpl();

    @Mock
    BaseDatos bd;

    @Test
    public void limpiarCesta() {

        Articulo a = new Articulo("camiseta", 50.0);
        carritoService.addArticulo(a);

        carritoService.limpiarCesta();

        Assertions.assertEquals(0, carritoService.getNumArticulos());
        Assertions.assertTrue(carritoService.getArticulos().isEmpty());
    }

    @Test
    public void addArticulo() {
        Articulo a = new Articulo("camiseta", 50.0);
        carritoService.addArticulo(a);
        Articulo b = new Articulo("pantalon", 30.0);
        carritoService.addArticulo(b);
        Assertions.assertEquals(2, carritoService.getNumArticulos());
        Articulo c = new Articulo("camisa", 45.0);
        carritoService.addArticulo(c);
        Assertions.assertTrue(0 < carritoService.getNumArticulos());

    }

    @Test
    public void getNumArticulo() {
        Articulo a = new Articulo("camiseta", 50.0);
        carritoService.addArticulo(a);
        Articulo b = new Articulo("pantalon", 30.0);
        carritoService.addArticulo(b);
        Assertions.assertEquals(2, carritoService.getNumArticulos());
        Articulo c = new Articulo("camisa", 45.0);
        carritoService.addArticulo(c);
        Assertions.assertEquals(3, carritoService.getNumArticulos());
    }

    @Test
    public void getArticulos() {
        Articulo a = new Articulo("camiseta", 50.0);
        carritoService.addArticulo(a);
        Articulo b = new Articulo("pantalon", 30.0);
        carritoService.addArticulo(b);
        Assertions.assertEquals(2, carritoService.getNumArticulos());
        Articulo c = new Articulo("camisa", 45.0);
        carritoService.addArticulo(c);

        Assertions.assertEquals("pantalon", carritoService.getArticulos().get(1).getNombre());
        Assertions.assertEquals(carritoService.getArticulos().size(), carritoService.getNumArticulos());
    }

    @Test
    public void totalPrice() {

        Articulo a = new Articulo("camiseta", 50.0);
        carritoService.addArticulo(a);
        Articulo b = new Articulo("pantalon", 30.0);
        carritoService.addArticulo(b);
        Assertions.assertEquals(2, carritoService.getNumArticulos());
        Articulo c = new Articulo("camisa", 45.0);
        carritoService.addArticulo(c);

        Assertions.assertEquals(125.0, carritoService.totalPrice());

    }

    @Test
    public void calculadorDescuento() {
        Articulo a = new Articulo("camiseta", 100.0);
        carritoService.addArticulo(a);
        Assertions.assertEquals(75.0, carritoService.calculadorDescuento(100.0, 25.0));

    }

    @Test
    public void aplicarDescuento() {
        when(bd.buscarArticulo(any(Integer.class))).thenReturn(new Articulo("camiseta", 20.0));
        //when(bd.buscarArticulo(1)).thenReturn(new Articulo("camiseta",20.0));
        Assertions.assertEquals(18.0, carritoService.aplicarDescuento(1, 10.0));
        //verify(bd).buscarArticulo(any());
    }

    @Test
    public void insertarArticulo() {

        //Se insertar articulo
        Articulo a = new Articulo("Camiseta", 22D);
        when(bd.insertarArticulo(a)).thenReturn(5);
        carritoService.addArticulo(a);
        Integer id = carritoService.insertarArticulo(a);


        Assertions.assertEquals(5, id);
        Assertions.assertSame(a, carritoService.getArticulos().get(0));
        Assertions.assertEquals(carritoService.getArticulos().get(0).getNombre(), "Camiseta");
        Assertions.assertEquals(carritoService.getArticulos().get(0).getPrecio(), 22.0);


        verify(bd, times(1)).insertarArticulo(a);


    }
}