package org.example.discerial.Controladores;

import org.example.discerial.Controladores.InicioSesionController;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.entities.Usuarios;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InicioSesionControllerTest {

    @Test
    public void testAutenticarUsuario_CredencialesCorrectas() throws Exception {
        InicioSesionController controller = new InicioSesionController();

        IusuariosImpl daoMock = mock(IusuariosImpl.class);
        Usuarios userMock = new Usuarios();
        when(daoMock.login("user@example.com", "1234")).thenReturn(userMock);

        boolean resultado = controller.autenticarUsuario("user@example.com", "1234", daoMock);

        assertTrue(resultado);
        verify(daoMock).login("user@example.com", "1234");
    }

    @Test
    public void testAutenticarUsuario_CredencialesIncorrectas() throws Exception {
        InicioSesionController controller = new InicioSesionController();

        IusuariosImpl daoMock = mock(IusuariosImpl.class);
        when(daoMock.login("user@example.com", "wrongpass")).thenReturn(null);

        boolean resultado = controller.autenticarUsuario("user@example.com", "wrongpass", daoMock);

        assertFalse(resultado);
    }
}