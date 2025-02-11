module org.example.restaurante {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens org.example.restaurante to javafx.fxml;
    exports org.example.restaurante;

    opens org.example.restaurante.entity to org.hibernate.orm.core, javafx.base;
    exports org.example.restaurante.controller;
    opens org.example.restaurante.controller to javafx.fxml;
}
