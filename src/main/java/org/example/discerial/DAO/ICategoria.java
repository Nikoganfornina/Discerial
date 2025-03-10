package org.example.discerial.DAO;

import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Pregunta;

import java.util.List;

public interface ICategoria {
    Categoria save(Categoria categoria);

    public List<Categoria> findAll();
}
