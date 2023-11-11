package com.utn.sprint_4.servicios;

import com.utn.sprint_4.dtos.DTORankingProductos;
import com.utn.sprint_4.entidades.ArticuloManufacturado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticuloManufacturadoService extends BaseService<ArticuloManufacturado, Long>{

    List<DTORankingProductos> findBy() throws Exception;

    List<ArticuloManufacturado> buscarProducto(String filtro) throws Exception;
    Page<ArticuloManufacturado> buscarProducto(String filtro, Pageable pageable) throws Exception;


}