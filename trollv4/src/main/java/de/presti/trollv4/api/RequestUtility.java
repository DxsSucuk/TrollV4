package de.presti.trollv4.api;

import com.google.gson.*;
import de.presti.trollv4.main.Data;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.InputStream;

public class RequestUtility {

    static HttpClient httpClient = HttpClientBuilder
            .create()
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.52 Safari/537.36 TrollV4/" + Data.version)
            .build();

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static JsonElement getJSON(String url) {
        HttpGet request = new HttpGet(url);

        try {
            return httpClient.execute(request, (c) -> new JsonParser().parse(EntityUtils.toString(c.getEntity())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JsonObject();
    }

    public static byte[] getBytes(String url) {
        HttpGet request = new HttpGet(url);

        try {
            return httpClient.execute(request, (c) -> EntityUtils.toByteArray(c.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new byte[0];
    }
}
