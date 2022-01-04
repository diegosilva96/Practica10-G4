package tienda.models.impl;

import tienda.models.Cliente;
import tienda.models.interfaces.ICommand;

public class AltaCommand implements ICommand {

    @Override
    public void ejecutar(Cliente cliente) {
        //System.out.println("[AltaCommand] usando cliente");
        cliente.darDeAlta();
    }
}
