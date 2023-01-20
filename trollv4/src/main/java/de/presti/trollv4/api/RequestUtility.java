package de.presti.trollv4.api;

import com.google.gson.*;
import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.Main;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestUtility {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.52 Safari/537.36 TrollV4/" + Data.version;

    private static InputStream GET(String url) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                return con.getInputStream();
            } else {
                Main.getPlugin().getLogger().warning("GET request not worked");
            }
        } catch (Exception exception) {
            Main.getPlugin().getLogger().warning("Error while sending GET request to " + url);
        }

        return null;
    }

    public static JsonElement getJSON(String url) {
        try (InputStream inputStream = GET(url)) {
            if (inputStream == null) return new JsonObject();
            return new JsonParser().parse(new InputStreamReader(inputStream));
        } catch (Exception exception) {
            Main.getPlugin().getLogger().warning("Error while getting JSON from " + url);
        }

        return new JsonObject();
    }

    public static byte[] getBytes(String url) {
        try (InputStream inputStream = GET(url)) {
            if (inputStream == null)
                return new byte[0];

            return IOUtils.toByteArray(inputStream);
        } catch (Exception exception) {
            Main.getPlugin().getLogger().warning("Error while getting JSON from " + url);
        }

        return new byte[0];
    }
}
