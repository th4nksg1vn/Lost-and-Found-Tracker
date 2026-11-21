module lostfound {
    requires javafx.controls;
    requires javafx.fxml;

    opens lostfound to javafx.fxml;
    exports lostfound;
}
