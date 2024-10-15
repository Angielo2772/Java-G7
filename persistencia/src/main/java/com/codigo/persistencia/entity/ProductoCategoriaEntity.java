package com.codigo.persistencia.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "producto_categoria")
public class ProductoCategoriaEntity {

    @EmbeddedId
    private ProductoCategoriaId id;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;
    @ManyToOne
    @MapsId("categoriaId")
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;
    private String estado;
    private int stock;
}
