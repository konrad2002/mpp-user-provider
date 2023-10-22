package net.myplayplanet.keycloak.user;

import net.myplayplanet.player.core.v2.dto.user.UserDto;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.GroupModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

public class MppUserProvider implements UserStorageProvider,
        UserLookupProvider,
        UserQueryProvider {

    protected KeycloakSession session;
    protected ComponentModel model;

    protected Map<String, UserDto> users = new HashMap<>();

    public MppUserProvider(KeycloakSession session, ComponentModel model) {
        this.session = session;
        this.model = model;

        users.put("978fe81d-14ee-49cf-95cc-4d0c4105b348", new UserDto(0L, UUID.fromString("978fe81d-14ee-49cf-95cc-4d0c4105b348"), "Coernerbrot", "", ""));
    }

    @Override
    public void close() {

    }

    private UserModel createAdapter(RealmModel realm, UserDto user) {
        return new UserAdapter(session, realm, model, user);
    }

    @Override
    public UserModel getUserByUsername(RealmModel realm, String username) {
        UserDto user = new UserDto(); // TODO fetch user from api by username
        user = users.values().stream().filter(userDto -> userDto.getName().equals(username)).limit(1).findFirst().orElse(user);
        return createAdapter(realm, user);
    }

    @Override
    public UserModel getUserById(RealmModel realm, String id) {
        StorageId storageId = new StorageId(id);
        String uuid = storageId.getExternalId();
        UserDto user = users.get(uuid); // TODO fetch user from api by uuid
        //UserDto user = new UserDto();
        return createAdapter(realm, user);
    }

    @Override
    public UserModel getUserByEmail(RealmModel realm, String email) {
        return null;
    }

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realmModel, String s, Integer integer, Integer integer1) {
        return users.values().stream().map(userDto -> createAdapter(realmModel, userDto));
    }

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realmModel, Map<String, String> map, Integer integer, Integer integer1) {
        return users.values().stream().map(userDto -> createAdapter(realmModel, userDto));
    }

    @Override
    public Stream<UserModel> getGroupMembersStream(RealmModel realmModel, GroupModel groupModel, Integer integer, Integer integer1) {
        return Stream.empty();
    }

    @Override
    public Stream<UserModel> searchForUserByUserAttributeStream(RealmModel realmModel, String s, String s1) {
        return Stream.empty();
    }
}
