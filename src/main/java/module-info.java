module project.asmvladislav {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.objectweb.asm.util;
    requires java.compiler;


    opens kursovaya to javafx.fxml;
    exports kursovaya;
}