package org.example.discerial.DAO;

import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Pregunta;

import java.util.List;

public interface ICategoria {
    Categoria save(Categoria categoria);


    List<Categoria> findByNombre(String nombre);

    int getIdCategoriaPorNombre(String nombre);

    List<Categoria> findAll();
     Categoria findById(int id);
}
