module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.controllers;
    opens com.example.demo.controllers to javafx.fxml;
    opens com.example.demo.Combattant to javafx.base;
    opens com.example.demo.Item to javafx.base;
}