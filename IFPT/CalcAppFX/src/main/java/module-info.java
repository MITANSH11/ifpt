module calc.calcappfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires jbcrypt;
    requires org.mongodb.driver.core;
    requires java.desktop;

    opens calc.calcappfx to javafx.fxml;
    exports calc.calcappfx;
}