module jeonserver.core {
    exports io.github.ma1uta.jeonserver;
    exports io.github.ma1uta.jeonserver.model.user;
    exports io.github.ma1uta.jeonserver.model.media;
    exports io.github.ma1uta.jeonserver.model.room;
    exports io.github.ma1uta.jeonserver.model.core;
    exports io.github.ma1uta.jeonserver.model.filter;

    requires lombok;
    requires java.persistence;
    requires javax.inject;
    requires typesafe.config;
    requires java.compiler;
}
