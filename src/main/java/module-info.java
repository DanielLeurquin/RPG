module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rpg to javafx.fxml;
    exports com.example.rpg;
    exports com.example.rpg.controllers;
    opens com.example.rpg.controllers to javafx.fxml;
    opens com.example.rpg.Combattant to javafx.base;
    opens com.example.rpg.Item to javafx.base;
}