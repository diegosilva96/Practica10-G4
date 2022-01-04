package tienda.models;

import java.util.ArrayList;
import java.util.List;

import tienda.models.impl.AltaCommand;
import tienda.models.impl.BajaCommand;
import tienda.models.interfaces.ICommand;

public class ClienteEmpresa {

    private ICommand command;
    private List<Cliente> listaClientes = new ArrayList<Cliente>(); 

    public void setCommand(ICommand command){
        this.command = command;
    }

    public void ejecutarAccion(Cliente cliente){
        
        this.command.ejecutar(cliente);

        if (this.command instanceof AltaCommand)
            listaClientes.add( cliente );
        else if (this.command instanceof BajaCommand)
            listaClientes.remove( cliente);
        else
            System.out.println("Ninguno");

        for (Cliente c : listaClientes) {
            System.out.println( "[ClienteEmpresa] cliente: " + c.getNombre());
        }
        
    }
}
