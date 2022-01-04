package tienda.models.impl;

import tienda.models.Pedido;

public class AprobadorDespacho extends AprobadorBase    {


    @Override
    public void aprobar(Pedido pedido) {
        
        if (pedido.getMontoTotal() > 300)   {

            pedido.setAprobadoDespacho( true );
            setAprobadorSiguiente( new AprobadorComercial() );
        }
        
        this.aprobadorSiguiente.aprobar( pedido );
        
    }

}
