package tienda.models.impl;

import tienda.models.Cliente;
import tienda.models.interfaces.ICommand;

public class BajaCommand implements ICommand {

    @Override
    public void ejecutar(Cliente cliente) {
        //System.out.println("[BajaCommand] usando cliente");
        cliente.darDeBaja();
    }
}
