package org.example.discerial.DAO;

import org.example.discerial.entities.Usuarios;

import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a los datos relacionadas con los usuarios.
 * Esta interfaz proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar)
 * sobre la entidad {@link Usuarios}.
 *
 * <p>Las implementaciones de esta interfaz deben encargarse de interactuar con la base de datos
 * o con cualquier otra fuente de datos persistente para gestionar la información de los usuarios.</p>
 *
 * @author Niko
 * @version 1.0
 * @since 2025-02-20
 */
public interface Iusuarios {

    /**
     * Obtiene todos los usuarios almacenados.
     *
     * @return Una lista con todos los usuarios.
     */
    List<Usuarios> findAll();

    /**
     * Busca un usuario por su ID.
     *
     * @param id El ID del usuario a buscar.
     * @return Una lista con el usuario correspondiente, o una lista vacía si no se encuentra.
     */
    List<Usuarios> findById(int id);

    /**
     * Busca usuarios por su correo electrónico.
     *
     * @param correo El correo electrónico del usuario.
     * @return Una lista de usuarios que coinciden con el correo proporcionado.
     */
    List<Usuarios> findByCorreo(String correo);

    /**
     * Busca usuarios por su nombre de usuario o nickname.
     *
     * @param nickname El nombre de usuario o nickname del usuario.
     * @return Una lista de usuarios que coinciden con el nickname proporcionado.
     */
    List<Usuarios> findByNickname(String nickname);

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param usuarios El objeto {@link Usuarios} que se desea guardar.
     * @return El usuario guardado, con los datos persistidos (incluido el ID generado).
     */
    Usuarios save(Usuarios usuarios);

    void activateUser(int id);
    void deactivateUser(int id);
    boolean isUserActive(int id);
    Usuarios login(String correo, String contrasena);
    /**
     * Actualiza la información de un usuario existente.
     *
     * @param usuarios El objeto {@link Usuarios} con la nueva información del usuario.
     * @return El usuario actualizado.
     */
    Usuarios update(Usuarios usuarios);

    /**
     * Elimina un usuario por su ID.
     *
     * @param id El ID del usuario a eliminar.
     * @return El usuario eliminado, o {@code null} si no se encuentra un usuario con el ID proporcionado.
     */
    Usuarios deleteById(int id);
}
