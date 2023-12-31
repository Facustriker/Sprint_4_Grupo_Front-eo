package com.utn.sprint_4.servicios;

import com.utn.sprint_4.dtos.BusquedaProductosDTO;
import com.utn.sprint_4.dtos.RankingProductosDTO;
import com.utn.sprint_4.dtos.RankingProductosFiltroDTO;
import com.utn.sprint_4.entidades.ArticuloManufacturado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticuloManufacturadoService extends BaseService<ArticuloManufacturado, Long>{

   List<RankingProductosDTO> rankingProductos(RankingProductosFiltroDTO rankingProductosFiltroDTO) throws Exception;

    List<BusquedaProductosDTO> findByDenominacion(String denominacion) throws Exception;
    List<ArticuloManufacturado> buscarProducto(String filtro) throws Exception;
    Page<ArticuloManufacturado> buscarProducto(String filtro, Pageable pageable) throws Exception;


}
