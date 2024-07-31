
/*primero creamos domain, dao, service, serviceImpl, controladores*/
package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="producto")
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long idProducto;    
    private String descripcion;
    private String detalle;
    private double precio;
    private int existencias;
    private String rutaImagen;
    private boolean activo;

    //semana9 se cambia con recorte de semana8
    //este obj categoria se va a relacionar con producto mediante un campo llamado id categoria
    @ManyToOne
    @JoinColumn(name="id_categoria") //insertable = false, updatable = false
    Categoria categoria;

    public Producto() {        
    }


    public Producto(String descripcion, String detalle, double precio, int existencias, String imagen, boolean activo) {
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.precio = precio;
        this.existencias = existencias;
        this.rutaImagen = imagen;
        this.activo = activo;
    }
}
