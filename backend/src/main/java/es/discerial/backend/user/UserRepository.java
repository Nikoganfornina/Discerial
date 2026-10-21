package es.discerial.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

/** Acceso a usuarios. El servicio de registro se implementara despues. */
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByNicknameIgnoreCase(String nickname);
}
