
/*primero creamos domain, dao, service, serviceImpl, controladores*/
package com.tienda.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

@Data
/*automaticamente tener set y get*/
@Entity
/*nos permite utilizar el obejto*/
@Table(name = "producto")
/*proyecto se relaciona con aaa tabla de base de datos*/

public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    /*este es el PK de la tabla*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*cuando cree un objeto nuevo tiene que darle esta identidad*/
    @Column(name = "id_producto")
    private Long idProducto;
    private String descripcion;
    private String detalle;
    private double precio;
    private int existencias;
    private String rutaImagen;
    private boolean activo;
    private Long idCategoria; //FK de la tabla categoria

    public Producto() {
    }

    public Producto(String descripcion, String detalle, double precio, int existencias, String rutaImagen, boolean activo, Long idCategoria) {
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.precio = precio;
        this.existencias = existencias;
        this.rutaImagen = rutaImagen;
        this.activo = activo;
        this.idCategoria = idCategoria;
    }

}
