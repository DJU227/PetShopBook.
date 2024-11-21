module org.example.petshopbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.petshopbook to javafx.fxml;
    exports org.example.petshopbook;
}