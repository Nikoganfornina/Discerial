// src/main/java/org/example/discerial/DAO/IEstadoUsuario.java
package org.example.discerial.DAO;

import org.example.discerial.entities.EstadoUsuario;

import java.util.Map;

public interface IEstadoUsuario {
    /**
     * Para un usuario dado (por su id), devuelve un Map<nombreCategoria, [aciertos, fallos]>
     */
    EstadoUsuario save(EstadoUsuario eu);



    Map<String,int[]> getEstadisticasPorCategoria(int userId);
}
