package com.tienda.dao;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ProductoDao extends JpaRepository<Producto, Long> {

    //Ejemplo de método utilizando Métodos de Query
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);

    //Ejemplo de método utilizando Consultas con JPQL
    @Query(value = "SELECT a FROM Producto a where a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Producto> metodoJPQL(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);

    //Ejemplo de método utilizando Consultas con SQL nativo
    @Query(nativeQuery = true,
            value = "SELECT * FROM producto where producto.precio BETWEEN :precioInf AND :precioSup ORDER BY producto.descripcion ASC")
    public List<Producto> metodoNativo(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);

    // Ejemplo de método utilizando Consultas con SQL nativo
    @Query(nativeQuery = true,
            value = "SELECT * FROM producto WHERE LOWER(producto.descripcion) LIKE LOWER(CONCAT('%', :nombre, '%')) ORDER BY producto.descripcion ASC")
    public List<Producto> buscarPorNombreNativo(@Param("nombre") String nombre);
}
