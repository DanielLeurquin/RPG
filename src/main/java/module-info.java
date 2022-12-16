module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.isep.rpg to javafx.fxml;
    exports com.isep.rpg;
    exports com.isep.rpg.controllers;
    opens com.isep.rpg.controllers to javafx.fxml;
    opens com.isep.rpg.Combattant to javafx.base;
    opens com.isep.rpg.Item to javafx.base;
}