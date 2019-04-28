open module jeonserver.standalone {
    uses io.github.ma1uta.jeonserver.standalone.Bundle;
    uses io.github.ma1uta.jeonserver.standalone.CommandLineExtension;
    uses io.github.ma1uta.jeonserver.standalone.ConfigurationModule;

    requires jeonserver.core;
    requires info.picocli;
    requires com.google.guice;
    requires com.google.guice.extensions.persist;
    requires org.slf4j;
    requires typesafe.config;
    requires jsr305;
    requires javax.inject;
    requires java.persistence;
    requires ch.qos.logback.classic;
    requires org.pf4j;
}
