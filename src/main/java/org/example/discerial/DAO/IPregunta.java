package org.example.discerial.DAO;

import org.example.discerial.entities.Pregunta;
import java.util.List;

/**
 * Interfaz para gestionar operaciones CRUD en la entidad Pregunta.
 */
public interface IPregunta {

    /**
     * Obtiene todas las preguntas de la base de datos.
     * @return Lista de todas las preguntas.
     */
    List<Pregunta> findAll();

    /**
     * Busca preguntas por su ID.
     * @param id Identificador único de la pregunta.
     * @return Lista con la(s) pregunta(s) encontradas (normalmente 0 o 1).
     */
    List<Pregunta> findById(int id);

    /**
     * Busca preguntas por el ID de la categoría.
     * @param categoriaId Identificador de la categoría.
     * @return Lista de preguntas que pertenecen a la categoría indicada.
     */
    List<Pregunta> findByCategoria(int categoriaId);

    /**
     * Guarda una nueva pregunta en la base de datos.
     * @param pregunta Objeto Pregunta a guardar.
     * @return La pregunta guardada con su ID generado.
     */
    Pregunta save(Pregunta pregunta);

    /**
     * Actualiza la información de una pregunta existente.
     * @param pregunta Objeto Pregunta con los nuevos datos.
     * @return La pregunta actualizada.
     */
    Pregunta update(Pregunta pregunta);

    /**
     * Elimina una pregunta por su ID.
     * @param id Identificador único de la pregunta a eliminar.
     * @return La pregunta eliminada o null si no se encontró.
     */
    Pregunta deleteById(int id);
}
