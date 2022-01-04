package tienda.models.interfaces;

import tienda.models.Cliente;

public interface ICommand {

    void ejecutar(Cliente cliente);

}
