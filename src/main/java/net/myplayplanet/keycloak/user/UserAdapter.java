package net.myplayplanet.keycloak.user;

import net.myplayplanet.player.core.v2.dto.user.UserDto;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.SubjectCredentialManager;
import org.keycloak.storage.adapter.AbstractUserAdapter;

public class UserAdapter
        extends AbstractUserAdapter
{

    private final UserDto user;

    public UserAdapter(KeycloakSession session, RealmModel realm, ComponentModel storageProviderModel, UserDto user) {
        super(session, realm, storageProviderModel);
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public SubjectCredentialManager credentialManager() {
        return null;
    }
}
