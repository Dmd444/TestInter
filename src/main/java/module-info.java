module com.dmd.testinter {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.dmd.testinter to javafx.fxml;
    exports com.dmd.testinter;
}
