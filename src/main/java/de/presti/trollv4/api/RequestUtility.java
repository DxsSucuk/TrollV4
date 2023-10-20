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
}
