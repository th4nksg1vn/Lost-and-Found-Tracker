module lostfound {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens lostfound to javafx.fxml;
    exports lostfound;
}
