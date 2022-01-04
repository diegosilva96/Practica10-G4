package tienda.models.impl;

import tienda.models.Pedido;

public class AprobadorComercial extends AprobadorBase    {


    @Override
    public void aprobar(Pedido pedido) {
        
        if (pedido.getMontoTotal() > 500)   {

            pedido.setAprobadoComercial( true );
            setAprobadorSiguiente( new AprobadorGerencia() );
        }
        else    {
            pedido.setAprobadoComercial( false );
        }
        
        this.aprobadorSiguiente.aprobar( pedido );
        
    }

}
