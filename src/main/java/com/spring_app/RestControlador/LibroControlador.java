package com.spring_app.RestControlador;

import com.spring_app.Entidad.Libro;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LibroControlador {

    private List<Libro>libros;
    public  LibroControlador(){
        libros=new ArrayList<>();
        libros.add(new Libro(1,"Cien anos de soledad","Sudamericana",450));
        libros.add(new Libro(2,"El nombre del viento","Palma",200));
        libros.add(new Libro(3,"Orgullo y prejuicio","Roca",380));

    }
    //listar libros
    @GetMapping("/api/libros")
    public List<Libro>listarLibros(){
        return libros;
    }
    @GetMapping("/api/libros/{id}")
    public Libro buscarlibro(@PathVariable int id){
        for(Libro libro:libros){
            if(libro.getId()==id){
                return libro;
            }

        }
        return null;
    }
    @PostMapping("/registrarlibro")
    public Libro crearlibro(@RequestBody Libro libro){
        libro.setId(libros.size()+1);
        libros.add(libro);
        return libro;
    }
    @PutMapping("/actualizarlibro/{id}")
    public Libro actualizarlibro(@PathVariable int id ,@RequestBody Libro libro){
        for(Libro libroExistente:libros){
            if(libroExistente.getId()==id){
                libroExistente.setTitulo(libro.getTitulo());
                libroExistente.setEditorial(libro.getEditorial());
                libroExistente.setPaginas(libro.getPaginas());
                return libroExistente;
            }
        }
        return null;
    }
    @DeleteMapping("/eliminarLibro/{id}")
    public  void eliminarlibro(@PathVariable int id){
        for(Libro libro:libros){
            if(libro.getId()==id){
                libros.remove(libro);
                break;
            }
        }

    }
}
