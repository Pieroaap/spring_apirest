package org.palvaradop.apirest.Controllers;

import org.palvaradop.apirest.Entities.Producto;
import org.palvaradop.apirest.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto findById(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con id: " + id));
    }

    @PostMapping
    public Producto add(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto producto) {
        Producto product = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con id: " + id));

        product.setNombre(producto.getNombre());
        product.setPrecio(producto.getPrecio());

        return productoRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Producto product = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con id: " + id));
        productoRepository.delete(product);

        return "El producto con ID: " + id + " fue eliminado correctamente.";
    }

}
