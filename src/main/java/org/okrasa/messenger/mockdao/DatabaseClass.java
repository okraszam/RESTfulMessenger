package org.okrasa.messenger.mockdao;

import org.okrasa.messenger.model.Message;
import org.okrasa.messenger.model.Profile;
import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {

    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();

    static {

        messages.put(1L, new Message(1L,"Siema", "jarko"));
        messages.put(2L, new Message(2L,"Elo", "barko"));
        messages.put(3L, new Message(3L,"Fakamaka", "sparko"));

        profiles.put("Janek", new Profile(1L, "JanekJoł", "Janek", "Kanej"));
        profiles.put("Franek", new Profile(2L, "FranekGaj", "Franek", "Gajer"));
        profiles.put("Zbynek", new Profile(3L, "ZbynekPyn", "Zbynek", "Pynek"));

    }

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }
}
