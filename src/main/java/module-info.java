module org.example.discerial {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires transitive jakarta.validation;
    requires java.naming;

    opens org.example.discerial to javafx.fxml;
    exports org.example.discerial;
    exports org.example.discerial.Controladores;
    opens org.example.discerial.Controladores to javafx.fxml;
    opens org.example.discerial.entities to org.hibernate.orm.core;

}
