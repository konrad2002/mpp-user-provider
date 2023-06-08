package net.myplayplanet.keycloak.user;

import org.keycloak.component.ComponentModel;
import org.keycloak.component.ComponentValidationException;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;
import org.keycloak.storage.UserStorageProviderFactory;
import org.keycloak.utils.StringUtil;

import java.util.List;

public class MppUserProviderFactory implements UserStorageProviderFactory<MppUserProvider> {

    public static final String PROVIDER_ID = "mpp-user-provider";

    @Override
    public MppUserProvider create(KeycloakSession keycloakSession, ComponentModel componentModel) {
        return new MppUserProvider(keycloakSession, componentModel);
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getHelpText() {
        return "MPP User Provider";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return ProviderConfigurationBuilder.create()
                .property(Constants.PLAYER_SERVICE_URL, "Player Service URL", "URL of the player service to fetch users from", ProviderConfigProperty.STRING_TYPE, "", null)
                .build();
    }

    @Override
    public void validateConfiguration(KeycloakSession session, RealmModel realm, ComponentModel config) throws ComponentValidationException {
        if (StringUtil.isBlank(config.get(Constants.PLAYER_SERVICE_URL))) {
            throw new ComponentValidationException("Player Service URL configuration missing");
        }
    }
}
