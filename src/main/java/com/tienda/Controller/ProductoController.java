package com.tienda.Controller;

import com.tienda.domain.Categoria;
import com.tienda.domain.Producto;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import com.tienda.service.impl.FirebaseStorageServiceImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
/*permite hacer logs en consola-msj en el output*/
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    /*responde al metodo http get*/
    @GetMapping("/listado")
    /*model es el transporte entre el controlador y la vista*/
    public String inicio(Model model) {
        List<Producto> productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        
        //esto es agregado para hacer el dropdown en fragmentos y que puedan seleccionar la categoria
        List<Categoria>  categorias = categoriaService.getCategorias(true); //obtener todas las categorias activas, para llenar formulario en producto
        model.addAttribute("categorias", categorias);
        return "/producto/listado";
    }

    /*si quiero agregar un elemento nuevo, me redirige a una vista y en esa vista lleno los datos*/
    @GetMapping("/nuevo")
    public String productoNuevo(Producto producto) {
        return "/producto/modifica";
    }

    /*meotodo post= no se llega por un ancla, sino que se llega por un formulario*/
    /*primer parametro es obj categ, pero no recibe el obejto sino los datos que calzan con los de la clase producto*/
    @PostMapping("/guardar")
    public String productoGuardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            productoService.save(producto);
            producto.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "producto",
                            producto.getIdProducto()));
        }
        productoService.save(producto);
        return "redirect:/producto/listado";
    }

    /*se usa get porque se usa un boton=ancla*/
    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(Producto producto) {
        productoService.delete(producto);
        return "redirect:/producto/listado";
    }

    /*trae los datos y nos da la opcion de modificar con el model*/
    /*muestra los campos llenos y deja modificarlos*/
    @GetMapping("/modificar/{idProducto}")
    public String productoModificar(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        model.addAttribute("producto", producto);
        
        List<Categoria>  categorias = categoriaService.getCategorias(true); //obtener todas las categorias activas, para llenar formulario en producto
        model.addAttribute("categorias", categorias);
        
        return "/producto/modifica";
    }
}
