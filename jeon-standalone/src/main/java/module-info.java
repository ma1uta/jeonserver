module jeonserver.standalone {
    uses io.github.ma1uta.jeonserver.standalone.Bundle;

    requires jeonserver.core;
    requires info.picocli;
    requires com.google.guice;
    requires com.google.guice.extensions.persist;
    requires org.slf4j;
    requires typesafe.config;
    requires jsr305;
    requires java.persistence;
}
