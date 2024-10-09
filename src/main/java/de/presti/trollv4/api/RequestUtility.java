package de.presti.trollv4.api;

import com.google.gson.*;
import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.Main;
import io.sentry.Sentry;
import org.apache.commons.io.IOUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RequestUtility {

    static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.52 Safari/537.36 TrollV4/" + Data.version;

    private static InputStream GET(String url) {
        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) { // success
                return con.getInputStream();
            }
        } catch (Exception exception) {
            Sentry.captureException(exception);
            Main.getInstance().getLogger().warning("Error while sending GET request to " + url + "\nException: " + exception.getMessage());
        }

        return null;
    }

    public static JsonElement getJSON(String url) {
        try (InputStream inputStream = GET(url)) {
            if (inputStream == null) return new JsonObject();
            return new JsonParser().parse(new InputStreamReader(inputStream));
        } catch (Exception exception) {
            Sentry.captureException(exception);
            Main.getInstance().getLogger().warning("Error while getting JSON from " + url + "\nException: " + exception.getMessage());
        }

        return new JsonObject();
    }

    public static byte[] getBytes(String url) {
        try (InputStream inputStream = GET(url)) {
            if (inputStream == null)
                return new byte[0];

            return IOUtils.toByteArray(inputStream);
        } catch (Exception exception) {
            Sentry.captureException(exception);
            Main.getInstance().getLogger().warning("Error while getting JSON from " + url + "\nException: " + exception.getMessage());
        }

        return new byte[0];
    }

    public static boolean download(String url, String fileName) {
        if (url == null || fileName == null) {
            return false;
        }

        if (url.toLowerCase().startsWith("https://github.com")) {
            return downloadGithub(url, fileName);
        } else {
            return downloadDirect(url, fileName);
        }
    }

    public static boolean downloadGithub(String url, String fileName) {
        String cleaned = url.replaceAll("https://github.com/", "")
                .replace("releases/tag/", "releases/tags/");

        JsonObject jsonObject = RequestUtility.getJSON("https://api.github.com/repos/" + cleaned).getAsJsonObject();
        if (jsonObject.has("assets")) {
            JsonArray assets = jsonObject.getAsJsonArray("assets");
            for (JsonElement jsonElement : assets) {
                JsonObject jsonObject1 = jsonElement.getAsJsonObject();

                if (jsonObject1.has("name") && jsonObject1.get("name").getAsString().endsWith(".jar")) {
                    return downloadDirect(assets.get(0).getAsJsonObject().get("browser_download_url").getAsString(), fileName);
                }
            }
        }

        return false;
    }

    public static boolean downloadDirect(String url, String fileName) {
        try {
            Files.write(Paths.get(fileName), RequestUtility.getBytes(url));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
