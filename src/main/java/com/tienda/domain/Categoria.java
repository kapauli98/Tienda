package com.tienda.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
/*automaticamente tener set y get*/
@Entity
/*nos permite utilizar el obejto*/
@Table(name = "categoria")
/*proyecto se relaciona con aaa tabla de base de datos*/
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id /*este es el PK de la tabla*/
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*cuando cree un objeto nuevo tiene que darle esta identidad*/
    @Column(name="id_categoria")
    private Long idCategoria;
    private String descripcion;
    private String rutaImagen; /*hibernate lo transforma por si solo en ruta_imagen*/
    private boolean activo;
    
    public Categoria(){
        
    }
    
    public Categoria(String descripcion, String rutaImagen, boolean activo){
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.activo = activo;
    }
}
