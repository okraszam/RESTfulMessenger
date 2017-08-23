package org.okrasa.messenger.service;

import org.okrasa.messenger.mockdao.DatabaseClass;
import org.okrasa.messenger.model.Profile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {

    private Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public ProfileService() {
    }

    public List<Profile> getAllProfiles () {

        return new ArrayList<>(profiles.values());

    }

    public Profile getProfile (String profileName) {

        return profiles.get(profileName);

    }

    public Profile addProfile (Profile profile) {

        profile.setId(profiles.size() + 1);
        profiles.put(profile.getPrifileName(), profile);
        return profile;

    }

    public Profile updateProfile (Profile profile) {

        if(profile.getId() <= 0) {
            return null;
        } else {
            profiles.put(profile.getPrifileName(), profile);
            return profile;
        }

    }

    public Profile deleteProfile (String profileName) {

        return profiles.remove(profileName);

    }

}
