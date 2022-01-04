package tienda.models.impl;

import tienda.models.Pedido;
import tienda.models.interfaces.IAprobadorPedido;

public abstract class AprobadorBase implements IAprobadorPedido {

    protected IAprobadorPedido aprobadorSiguiente;

    @Override
    public void setAprobadorSiguiente(IAprobadorPedido aprobador)   {
        
        this.aprobadorSiguiente = aprobador;
    }

    @Override
    public void aprobar(Pedido pedido) {
        
        if (aprobadorSiguiente != null)
            aprobadorSiguiente.aprobar( pedido );
        
    }
    
}
