package tienda.controllers.impl;

import tienda.config.Paths;
import tienda.controllers.OrderController;
import tienda.models.BancoMetodoPago;
import tienda.models.BlockChainMetodoPago;
import tienda.models.Cliente;
import tienda.models.ClienteEmpresa;
import tienda.models.Pedido;
import tienda.models.impl.AltaCommand;
import tienda.models.impl.AprobadorDespacho;
import tienda.models.impl.BajaCommand;
import tienda.models.impl.DescuentoFactory;
import tienda.models.impl.PedidoCreado;
import tienda.models.impl.PedidoDetalleInternet;
import tienda.models.impl.PedidoDetallePromocion;
import tienda.models.interfaces.IAprobadorPedido;
import tienda.models.interfaces.ICommand;
import tienda.models.interfaces.IDescuento;
import tienda.models.interfaces.IPedidoDetalle;
import tienda.repositories.ClienteRepositorio;
import tienda.repositories.PedidoRepositorio;

import io.javalin.http.Context;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.NotFoundResponse;

import java.util.ArrayList;
import java.util.List;

//import org.bson.types.ObjectId;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;

public class OrderControllerImpl implements OrderController {
    private static final String ID = "id";

    private PedidoRepositorio orderRepository;
    private ClienteRepositorio customerRepository;

    public OrderControllerImpl(PedidoRepositorio orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderControllerImpl(PedidoRepositorio orderRepository, ClienteRepositorio custRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = custRepository;
        System.out.println(customerRepository);
    }

    public void create(Context context) {
        Pedido order = context.bodyAsClass(Pedido.class);

        List<IPedidoDetalle> items = new ArrayList<>();
        PedidoDetalleInternet oi1 = new PedidoDetalleInternet( "P01010034", 1, 400.90);
        PedidoDetallePromocion oi2 = new PedidoDetallePromocion( "P01010025", 1, 600.90);
        items.add(oi1);
        items.add(oi2);
        order.setDetallePedido(items);

        order.setEstadoPedido( new PedidoCreado() );
        order.procesar();

        Cliente cliObj = (Cliente)customerRepository.find("616f797ea7539a581e64e7e8");
        order.setClienteObj( cliObj );
        System.out.println( cliObj.imprimeDatosCliente() );

        orderRepository.create(order);
        //String idO = order.getId().toString(); 
        context.status(HttpStatus.CREATED_201)
                .header(HttpHeader.LOCATION.name(), Paths.formatPostLocation(order.getId().toString()));

        order.procesar();
        
        order.procesar();

        order.procesar();

        //  Actualiza CLienteEmpresa para probar Command
        System.out.println("\nLogica comando para clientes de Empresa");
        ClienteEmpresa empresa = new ClienteEmpresa();
        
        ICommand alta = new AltaCommand();    
        empresa.setCommand(alta);
        empresa.ejecutarAccion(cliObj);
        
        Cliente c2 = new Cliente("4334343");
        c2.setNombre("Paolo Guerrero");
        empresa.ejecutarAccion(c2);
        
        ICommand baja = new BajaCommand();    
        empresa.setCommand(baja);
        empresa.ejecutarAccion(c2);
        

        // Cadena de Aprobacion del Pedido
        order.verAprobaciones();
        IAprobadorPedido aprobador1 = new AprobadorDespacho();
        order.setMontoTotal(550.00);
        aprobador1.aprobar( order );
        order.verAprobaciones();
    }

    public void find(Context context) {
        String id = context.pathParam(ID);
        Pedido order = orderRepository.find(id);

        if (order == null) {
            throw new NotFoundResponse(String.format("A order with id '%s' is not found", id));
        }

        context.json(order);

    }

    public void findAll(Context context) {
        context.json(orderRepository.findAll());
    }

    @Override
    public void delete(Context context) {
        orderRepository.delete(context.pathParam(ID));

    }


    @Override
    public void update(Context context) {
        Pedido order = context.bodyAsClass(Pedido.class);
        String id = context.pathParam(ID);

        if (order.getId() != null && !order.getId().toString().equals(id)) {
            throw new BadRequestResponse("Id update is not allowed");
        }

        orderRepository.update(order, id);

    }
}
