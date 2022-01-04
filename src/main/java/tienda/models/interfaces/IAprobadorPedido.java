package tienda.models.interfaces;

import tienda.models.Pedido;

public interface IAprobadorPedido {
    
    public void setAprobadorSiguiente( IAprobadorPedido aprobador );

    public void aprobar( Pedido pedido );

}
