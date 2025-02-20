module org.example.discerial {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.validation;
    requires jakarta.validation;
    requires org.apache.commons.pool2;

    opens org.example.discerial to javafx.fxml;
    exports org.example.discerial;
    exports org.example.discerial.Controladores;
    opens org.example.discerial.Controladores to javafx.fxml;
}