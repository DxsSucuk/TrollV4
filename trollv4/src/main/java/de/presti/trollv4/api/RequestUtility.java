package de.presti.trollv4.api;

import com.google.gson.*;
import de.presti.trollv4.main.Data;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RequestUtility {

    static OkHttpClient httpClient = new OkHttpClient();

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.52 Safari/537.36 TrollV4/" + Data.version;

    public static JsonElement getJSON(String url) {
        Request request = new Request.Builder().url(url).addHeader("User-Agent", USER_AGENT).build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.body() == null) return new JsonObject();
            return new JsonParser().parse(response.body().string());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new JsonObject();
    }

    public static byte[] getBytes(String url) {
        Request request = new Request.Builder().url(url).addHeader("User-Agent", USER_AGENT).build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.body() == null) return new byte[0];
            return response.body().bytes();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new byte[0];
    }
}
