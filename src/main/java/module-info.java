module com.example.tecno {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens com.example.tecno to javafx.fxml;
    exports com.example.tecno;
}