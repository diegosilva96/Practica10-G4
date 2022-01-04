package tienda.models.impl;

import tienda.models.Pedido;

public class AprobadorGerencia extends AprobadorBase {
    
    @Override
    public void aprobar(Pedido pedido) {
        
        if (pedido.getMontoTotal() > 700 && pedido.getMontoTotal() <= 900)   {

            pedido.setAprobadoGerencia( true );
        }
        else    {

            pedido.setAprobadoGerencia( false );
        }
        
    }

}
