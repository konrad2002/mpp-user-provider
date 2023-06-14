package net.myplayplanet.keycloak.user;

import lombok.RequiredArgsConstructor;
import net.myplayplanet.client.player.api.PlayerClient;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PlayerServiceRequestFacade {

    private final PlayerClient playerClient;

    public void request(){
        playerClient.getUser(UUID.fromString("56602753-5bb1-4b72-bb6d-2a3a764a1c8d"))
                .onErrorResume(throwable -> {
                    System.out.println("ey hier iwie fehler");
                    throwable.printStackTrace();
                    return Mono.empty();
                })
                .doOnNext(userDto -> {
                    System.out.println("mach iwas damit " + userDto);
                })
                .block();
    }


}
