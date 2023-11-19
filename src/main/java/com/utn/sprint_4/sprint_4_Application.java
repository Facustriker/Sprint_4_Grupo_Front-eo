package com.utn.sprint_4;

import com.utn.sprint_4.entidades.*;
import com.utn.sprint_4.enumeraciones.EstadoPedido;
import com.utn.sprint_4.enumeraciones.FormaPago;
import com.utn.sprint_4.enumeraciones.Rol;
import com.utn.sprint_4.enumeraciones.TipoEnvio;
import com.utn.sprint_4.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class sprint_4_Application {

    @Autowired
    private PersonaRepository personaRepository;

    public static void main(String[] args) {
        SpringApplication.run(sprint_4_Application.class, args);
        System.out.println("Hola, estoy andando bien");
    }


    @Bean
    CommandLineRunner init() {
        return args -> {

            SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoHora= new SimpleDateFormat("hh:mm:ss");
            String fechaString = "2023-09-13";
            String fechaString2 = "2023-05-19";
            String fechaString3 = "2023-03-21";
            String fechaString4 = "2023-07-11";
            String horaString = "21:05:03";

            Date fecha = formatoFecha.parse(fechaString); //2023-09-13
            Date fecha2 = formatoFecha.parse(fechaString2); //2023-05-19
            Date fecha3 = formatoFecha.parse(fechaString3); //2023-03-21
            Date fecha4 = formatoFecha.parse(fechaString4); //2023-07-11
            Date hora = formatoHora.parse(horaString);

            UnidadMedida unidadMedida = UnidadMedida.builder()
                    .denominacion("gramo")
                    .abreviatura("Gr")
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .build();

            UnidadMedida unidadMedida2 = UnidadMedida.builder()
                    .denominacion("litro")
                    .abreviatura("Lt")
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .build();

            RubroArticuloInsumo rubroArticuloInsumo = RubroArticuloInsumo.builder()
                    .denominacion("Carne")
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .build();

            RubroArticuloInsumo rubroArticuloInsumo2 = RubroArticuloInsumo.builder()
                    .denominacion("Verdura")
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .build();

            RubroArticuloManufacturado rubroArticuloManufacturado1 = RubroArticuloManufacturado.builder()
                    .denominacion("Hamburguesa")
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .build();

            RubroArticuloManufacturado rubroArticuloManufacturado2 = RubroArticuloManufacturado.builder()
                    .denominacion("Lomo")
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .build();

            DetalleArticuloManufacturado detArtManu1 = DetalleArticuloManufacturado.builder()
                    .cantidad(4)
                    .build();

            DetalleArticuloManufacturado detArtManu2 = DetalleArticuloManufacturado.builder()
                    .cantidad(5)
                    .build();

            DetalleArticuloManufacturado detArtManu3 = DetalleArticuloManufacturado.builder()
                    .cantidad(9)
                    .build();

            DetalleFactura detalleFactura1 = DetalleFactura.builder()
                    .cantidad(3)
                    .subtotal(4000)
                    .build();

            DetalleFactura detalleFactura2 = DetalleFactura.builder()
                    .cantidad(4)
                    .subtotal(5000)
                    .build();

            DetalleFactura detalleFactura3 = DetalleFactura.builder()
                    .cantidad(7)
                    .subtotal(12000)
                    .build();

            Factura factura1 = Factura.builder()
                    .fechaAlta(fecha)
                    .fechaFacturacion(fecha2)
                    .fechaModificacion(fecha3)
                    .formapago(FormaPago.EFECTIVO)
                    .merchantorder(201933394L)
                    .paymentid(3423523775L)
                    .paymenttype("niIdea")
                    .preferenceid("3423523775")
                    .totalventa(3099)
                    .build();

            Factura factura2 = Factura.builder()
                    .fechaAlta(fecha)
                    .fechaFacturacion(fecha2)
                    .fechaModificacion(fecha3)
                    .formapago(FormaPago.EFECTIVO)
                    .merchantorder(201933394L)
                    .paymentid(3423523775L)
                    .paymenttype("niIdea")
                    .preferenceid("3423523775")
                    .totalventa(3099)
                    .build();

            ArticuloInsumo artIns1 = ArticuloInsumo.builder()
                    .denominacion("Carne molida")
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .precioCompra(50000)
                    .stockActual(200)
                    .stockMinimo(100)
                    .urlImagen("http://imagengod.com")
                    .build();

            ArticuloInsumo artIns2 = ArticuloInsumo.builder()
                    .denominacion("Tomate")
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .precioCompra(25000)
                    .stockActual(200)
                    .stockMinimo(100)
                    .urlImagen("httpp//imagengodtomatito.com")
                    .build();

            ArticuloInsumo artIns3 = ArticuloInsumo.builder()
                    .denominacion("Lechuga")
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .precioCompra(12000)
                    .stockActual(200)
                    .stockMinimo(100)
                    .urlImagen("http://www.lalechugadelabuena.com")
                    .build();

            ArticuloManufacturado articuloManufacturado1 = ArticuloManufacturado.builder()
                    .costo(3922)
                    .denominacion("Hamburguesa")
                    .descripcion("Hamburguesa simple con cheddar")
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .precioVenta(13000)
                    .tiempoEstimadoCocina(20)
                    .urlImagen("http//hamborguesitas-o-ke.com")
                    .rubroArticuloManufacturado(rubroArticuloManufacturado1)
                    .build();

            ArticuloManufacturado articuloManufacturado2 = ArticuloManufacturado.builder()
                    .costo(2000)
                    .denominacion("Pizza")
                    .descripcion("Pizza con jamón")
                    .fechaAlta(fecha2)
                    .fechaBaja(fecha)
                    .fechaModificacion(fecha3)
                    .precioVenta(15000)
                    .tiempoEstimadoCocina(30)
                    .urlImagen("http//mmmmPizzita.com")
                    .rubroArticuloManufacturado(rubroArticuloManufacturado2)
                    .build();

            ArticuloManufacturado articuloManufacturado3 = ArticuloManufacturado.builder()
                    .costo(5000)
                    .denominacion("Hamburguesa")
                    .descripcion("Grand Tasty")
                    .fechaAlta(fecha3)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha)
                    .precioVenta(12000)
                    .tiempoEstimadoCocina(45)
                    .urlImagen("http://www.burguerCopada.com")
                    .rubroArticuloManufacturado(rubroArticuloManufacturado2)
                    .build();

            Usuario usuario = Usuario.builder()
                    .auth0Id("User01")
                    .username("Facustriker")
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .build();


            Domicilio domicilio1 = Domicilio.builder()
                    .calle("Beltrán")
                    .codigoPostal(5500)
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .localidad("Godoy Cruz")
                    .numero(342)
                    .numeroDpto(3)
                    .pisoDpto(2)
                    .build();

            Domicilio domicilio2 = Domicilio.builder()
                    .calle("Belgrano")
                    .codigoPostal(5519)
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .localidad("Capital")
                    .numero(302)
                    .numeroDpto(4)
                    .pisoDpto(1)
                    .build();

            DetallePedido detallePedido1 = DetallePedido.builder()
                    .cantidad(6)
                    .subtotal(7000)
                    .subtotal_costo(5000)
                    .build();

            DetallePedido detallePedido2 = DetallePedido.builder()
                    .cantidad(4)
                    .subtotal(3000)
                    .subtotal_costo(1000)
                    .build();

            DetallePedido detallePedido3 = DetallePedido.builder()
                    .cantidad(12)
                    .subtotal(13000)
                    .subtotal_costo(4500)
                    .build();


            Pedido pedido1 = Pedido.builder()
                    .estado(EstadoPedido.PAGADO)
                    .nroPedido("876324")
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .fechaPedido(fecha4)
                    .formaPago(FormaPago.EFECTIVO)
                    .horaEstimadaFinalizacion(hora)
                    .tipoEnvio(TipoEnvio.TAKE_AWAY)
                    .total(4000)
                    .totalCosto(2000)
                    .build();

            Pedido pedido2 = Pedido.builder()
                    .estado(EstadoPedido.COMPLETADO)
                    .nroPedido("978345")
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .fechaPedido(fecha4)
                    .formaPago(FormaPago.EFECTIVO)
                    .horaEstimadaFinalizacion(hora)
                    .tipoEnvio(TipoEnvio.TAKE_AWAY)
                    .total(9000)
                    .totalCosto(3260)
                    .build();

            Persona persona = Persona.builder()
                    .nombre("Juan")
                    .apellido("Marquez")
                    .telefono("2617223459")
                    .email("juansito@gmail.com")
                    .password("123")
                    .legajo("4500")
                    .fechaNacimiento("1998/09/18")
                    .fechaAlta(fecha)
                    .fechaBaja(fecha2)
                    .fechaModificacion(fecha3)
                    .rol(Rol.DELIVERY)
                    .build();

            NotaCredito nota1 = NotaCredito.builder()
                    .montoTotal(7000)
                    .motivoAnulacion("Cancelado por cliente")
                    .factura(factura1)
                    .persona(persona)
                    .build();

            NotaCredito nota2 = NotaCredito.builder()
                    .montoTotal(13000)
                    .motivoAnulacion("Cancelado por cliente")
                    .factura(factura2)
                    .persona(persona)
                    .build();

            persona.setUsuario(usuario);
            persona.AgregarDomicilios(domicilio1);
            persona.AgregarDomicilios(domicilio2);
            persona.AgregarPedidos(pedido1);
            persona.AgregarPedidos(pedido2);
            //domicilio1.setPersona(persona);
            //domicilio2.setPersona(persona);
            //domicilio1.AgregarPedidos(pedido1);
            //domicilio2.AgregarPedidos(pedido2);
            //pedido1.setPersona(persona);
            //pedido2.setPersona(persona);
            pedido1.AgregarDetallePedido(detallePedido1);
            pedido1.AgregarDetallePedido(detallePedido2);
            pedido2.AgregarDetallePedido(detallePedido3);
            pedido1.setDomicilio(domicilio1);
            pedido2.setDomicilio(domicilio2);
            //detallePedido1.setPedido(pedido1);
            //detallePedido2.setPedido(pedido1);
            //detallePedido3.setPedido(pedido2);
            detallePedido1.setArticuloManufacturado(articuloManufacturado1);
            detallePedido2.setArticuloManufacturado(articuloManufacturado2);
            detallePedido3.setArticuloManufacturado(articuloManufacturado3);
            //articuloManufacturado1.setDetallePedido(detallePedido1);
            //articuloManufacturado2.setDetallePedido(detallePedido2);
            //articuloManufacturado3.setDetallePedido(detallePedido3);
            //factura1.setPedido(pedido1);
            //factura2.setPedido(pedido2);
            pedido1.setFactura(factura1);
            pedido2.setFactura(factura2);
            factura1.AgregarDetalleFacturas(detalleFactura1);
            factura1.AgregarDetalleFacturas(detalleFactura2);
            factura2.AgregarDetalleFacturas(detalleFactura3);
            //detalleFactura1.setFactura(factura1);
            //detalleFactura2.setFactura(factura1);
            //detalleFactura3.setFactura(factura2);
            //articuloManufacturado1.AgregarDetalleFactura(detalleFactura1);
            //articuloManufacturado2.AgregarDetalleFactura(detalleFactura2);
            //articuloManufacturado3.AgregarDetalleFactura(detalleFactura3);


            //detalleFactura1.setArticuloManufacturado(articuloManufacturado1);
            //detalleFactura2.setArticuloManufacturado(articuloManufacturado2);
            //detalleFactura3.setArticuloManufacturado(articuloManufacturado3);
            articuloManufacturado1.AgregarDetalleArtManufac(detArtManu1);
            articuloManufacturado2.AgregarDetalleArtManufac(detArtManu2);
            articuloManufacturado3.AgregarDetalleArtManufac(detArtManu3);
            articuloManufacturado1.AgregarDetalleFacturas(detalleFactura1);
            articuloManufacturado2.AgregarDetalleFacturas(detalleFactura2);
            articuloManufacturado3.AgregarDetalleFacturas(detalleFactura3);
            //detArtManu1.setArticuloManufacturado(articuloManufacturado1);
            //detArtManu2.setArticuloManufacturado(articuloManufacturado2);
            //detArtManu3.setArticuloManufacturado(articuloManufacturado3);

            //artIns1.AgregarDetalleArticuloManufacturado(detArtManu1);
            //artIns2.AgregarDetalleArticuloManufacturado(detArtManu2);
            //artIns3.AgregarDetalleArticuloManufacturado(detArtManu3);
            detArtManu1.setArticuloInsumo(artIns1);
            detArtManu2.setArticuloInsumo(artIns2);
            detArtManu3.setArticuloInsumo(artIns3);

            artIns1.setRubroArticuloInsumo(rubroArticuloInsumo);
            artIns2.setRubroArticuloInsumo(rubroArticuloInsumo);
            artIns3.setRubroArticuloInsumo(rubroArticuloInsumo);
            artIns1.setRubroArticuloInsumo(rubroArticuloInsumo2);
            artIns2.setRubroArticuloInsumo(rubroArticuloInsumo2);
            artIns3.setRubroArticuloInsumo(rubroArticuloInsumo2);
            artIns1.setUnidadMedida(unidadMedida);
            artIns2.setUnidadMedida(unidadMedida);
            artIns3.setUnidadMedida(unidadMedida);


            personaRepository.save(persona);


        };
    }


}
