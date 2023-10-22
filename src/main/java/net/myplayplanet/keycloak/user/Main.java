package net.myplayplanet.keycloak.user;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import lombok.SneakyThrows;
import net.myplayplanet.client.player.implementation.PlayerClientModule;
import net.myplayplanet.di.DependencyContainer;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        AbstractModule abstractModule = new AbstractModule() {
            @Override
            protected void configure() {
                super.configure();
                bind(String.class).annotatedWith(Names.named("playerServiceBaseUrl")).toInstance("http://localhost:8083/");
                bind(String.class).annotatedWith(Names.named("banServiceBaseUrl")).toInstance("http://ban-service:8080/");

            }
        };


        DependencyContainer.register(abstractModule);
        DependencyContainer.register(new PlayerClientModule(false, ClassLoader.getSystemClassLoader()));
        Injector instance = DependencyContainer.require(PlayerClientModule.class)
                .getInstance();
        instance.getInstance(PlayerServiceRequestFacade.class)
                .request();
    }
}
