module com.example.atividade04_mensageria {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.atividade04_mensageria to javafx.fxml;
    exports com.example.atividade04_mensageria;
}