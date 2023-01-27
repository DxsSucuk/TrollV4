package de.presti.trollv4.api;

import com.google.gson.JsonObject;

public class PlayerInfo {

    public static String link = "https://playerdb.co/api/player/minecraft/";

    public static String getUUID(String uuid) {

        JsonObject j = RequestUtility.getJSON(link + uuid).getAsJsonObject();

        if (j.has("data")) {
            try {
                JsonObject datas = j.getAsJsonObject("data");

                JsonObject player = datas.getAsJsonObject("player");

                String guuid = player.get("id").getAsString();

                return guuid;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getName(String uuid) {

        JsonObject j = RequestUtility.getJSON(link + uuid).getAsJsonObject();

        if (j.has("data")) {
            try {
                JsonObject datas = j.getAsJsonObject("data");

                JsonObject player = datas.getAsJsonObject("player");

                String guuid = player.get("username").getAsString();
                return guuid;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getPicture(String uuid) {
        return "https://minotar.net/helm/" + (uuid.trim()) + "/100.png";
    }

    public static String getUUIDtrim(String uuid) {

        JsonObject j = RequestUtility.getJSON(link + uuid).getAsJsonObject();

        if (j.has("data")) {
            try {
                JsonObject datas = j.getAsJsonObject("data");

                JsonObject player = datas.getAsJsonObject("player");

                String guuid = player.get("raw_id").getAsString();

                return guuid;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

}
