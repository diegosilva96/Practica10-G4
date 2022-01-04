package tienda.models;

import tienda.models.impl.CaretakerPrecio;
import tienda.models.impl.MementoPrecio;
import tienda.models.interfaces.IFamilia;

public class Producto {
    private String id;
    private String codigo;
    private String descripcion;
    private Double precioBase;
    private String lineaProducto;
    private String mantenimiento;
    private IFamilia familia;
    private CaretakerPrecio caretakerPrecio = new CaretakerPrecio();
    
    public Producto(String codigo, String descripcion, Double precioBase, String linea, String mantenimiento)   {

        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.lineaProducto = linea;
        this.mantenimiento = mantenimiento;
    }

    public IFamilia getFamilia() {
        return familia;
    }

    public void setFamilia(IFamilia familia) {
        this.familia = familia;
    }

    public Producto(String id)   {
        this.id = id;
    }    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
        System.out.println("Estableciendo precio base en: " + this.precioBase);
    }

    public String getLineaProducto() {
        return lineaProducto;
    }

    public void setLineaProducto(String lineaProducto) {
        this.lineaProducto = lineaProducto;
    }

    public String getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(String mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public CaretakerPrecio getCaretakerPrecio() {
        return caretakerPrecio;
    }

    public void setCaretakerPrecio(CaretakerPrecio caretakerPrecio) {
        this.caretakerPrecio = caretakerPrecio;
    }

    public MementoPrecio grabaPrecio() {
        System.out.println("Grabando en Memento Precio.");
        return new MementoPrecio(precioBase);
    }
    public void recuperaPrecio(MementoPrecio m) {
        precioBase = m.getPrecio();
        System.out.println("Recuperando desde Memento Precio: " + precioBase);
    }
}