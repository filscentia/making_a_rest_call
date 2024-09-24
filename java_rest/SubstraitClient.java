package com.ibm.flexdata.examples;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.tinylog.Logger;

public class SubstraitClient {

    public void run() {

        try {
            Logger.info("Processing via Calcite builder");
            // create substrait from builder created relations
            byte[] payload = null;
            HttpClient client = HttpClient.newHttpClient();

            var serviceUrl = "http://127.0.0.1:8000/v1/execute";

            var body = "{\"payload_b64\":\"" + payload + "\",  \"demo_engine\":\"\"}";

            Logger.info(body);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(serviceUrl))
                    .version(HttpClient.Version.HTTP_1_1)
                    .header("Content-type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Logger.info(response.body());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String name() {
        return "Substrait Client";
    }

}
