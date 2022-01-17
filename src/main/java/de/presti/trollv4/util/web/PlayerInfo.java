package de.presti.trollv4.util.web;

import java.io.IOException;
import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class PlayerInfo {

    public static String link = "https://playerdb.co/api/player/minecraft/";

    public static void setInput(HttpsURLConnection input) {
        PlayerInfo.input = input;
    }

    private static HttpsURLConnection input;

    public static HttpsURLConnection getInput() {
        return input;
    }

    public static void openinput() {
        try {
            getInput().connect();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void closeInput() {
        getInput().disconnect();
    }

    public static String getUUID(String uuid) {

        JSONObject j = JSONApi.GetData(JSONApi.Requests.GET, link + uuid);
        if (j.getBoolean("_success") && !j.toString().contains("Bad Request") && !j.toString().contains("Not Found")) {

            if (j.getInt("_http") == 200) {
                try {
                    JSONObject datas = j.getJSONObject("data");

                    JSONObject player = datas.getJSONObject("player");

                    String guuid = player.getString("id");

                    return guuid;

                } catch (Exception e) {

                    e.printStackTrace();

                    return null;

                }
            } else {

                return null;

            }
        } else {

            return null;

        }
    }

    public static String getName(String uuid) {

        JSONObject j = JSONApi.GetData(JSONApi.Requests.GET, link + uuid);
        if (j.getBoolean("_success") && !j.toString().contains("Bad Request") && !j.toString().contains("Not Found")) {

            if (j.getInt("_http") == 200) {
                try {
                    JSONObject datas = j.getJSONObject("data");

                    JSONObject player = datas.getJSONObject("player");

                    String guuid = player.getString("username");

                    return guuid;

                } catch (Exception e) {

                    e.printStackTrace();

                    return null;
                }
            } else {
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

        JSONObject j = JSONApi.GetData(JSONApi.Requests.GET, link + uuid);
        if (j.getBoolean("_success") && !j.toString().contains("Bad Request") && !j.toString().contains("Not Found")) {

            if (j.getInt("_http") == 200) {
                try {
                    JSONObject datas = j.getJSONObject("data");

                    JSONObject player = datas.getJSONObject("player");

                    String guuid = player.getString("raw_id");

                    return guuid;

                } catch (Exception e) {

                    e.printStackTrace();

                    return null;

                }
            } else {
                return null;

            }
        } else {
            return null;
        }
    }

}