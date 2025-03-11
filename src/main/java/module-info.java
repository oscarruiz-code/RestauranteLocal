module org.example.restaurante {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires java.desktop;
    requires jasperreports; // Asegúrate de que esta línea esté presente

    opens org.example.restaurante to javafx.fxml;
    exports org.example.restaurante;

    opens org.example.restaurante.entity to org.hibernate.orm.core, javafx.base, net.sf.jasperreports;
    exports org.example.restaurante.entity;

    opens org.example.restaurante.controller to javafx.fxml;
    exports org.example.restaurante.controller;
}