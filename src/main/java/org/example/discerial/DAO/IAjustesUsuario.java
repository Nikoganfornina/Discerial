package org.example.discerial.DAO;

import org.example.discerial.Util.TiempoPreguntas;
import org.example.discerial.entities.AjustesUsuario;
import org.example.discerial.entities.Usuarios;

import java.util.List;

public interface IAjustesUsuario {



    AjustesUsuario getAjustesByUsuarioId(int usuarioId) throws Exception;

    void actualizarAjustes(AjustesUsuario ajustes);
}
