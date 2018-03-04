package com.homework.buxbot.client.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.homework.buxbot.client.model.*;
import com.homework.buxbot.config.ApplicationConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static com.homework.buxbot.client.constants.Constants.*;

@Slf4j
@Singleton
public class ApacheHttpClient implements HttpClient {
    @Inject
    private ApplicationConfig config;

    private final PoolingHttpClientConnectionManager cm;
    private final SSLContext sc;
    private final CloseableHttpClient httpClient;

    public ApacheHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        sc = SSLContext.getInstance("TLS");
        sc.init(null, null, new SecureRandom());
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sc, new NoopHostnameVerifier());
        ConnectionSocketFactory socketFactory = new PlainConnectionSocketFactory();
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslSocketFactory).register("http", socketFactory).build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }

    public void sellOrder(String positionId) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            URIBuilder builder = new URIBuilder(config.getCloseUrl() + positionId);
            URI httpURI = builder.build();
            HttpDelete delete = new HttpDelete(httpURI);
            delete.setHeader("Connection", "Keep-Alive");
            delete.setHeader("Proxy-Connection", "Keep-Alive");
            delete.setHeader("Content-type", "application/json");
            delete.setHeader("accept", "application/json");
            delete.setHeader("Accept-Language", "nl-NL,en;q=0.8");
            delete.setHeader("Authorization", AUTH_TOKEN);
            CloseableHttpResponse httpResponse = httpClient.execute(delete);

            int httpStatus = httpResponse.getStatusLine().getStatusCode();
            if (httpStatus == 200) {
                log.info("SELL:" + EntityUtils.toString(httpResponse.getEntity(), Charset.forName("UTF-8")));
            }else{
                String responseJSON = EntityUtils.toString(httpResponse.getEntity(), Charset.forName("UTF-8"));
                RestError error = mapper.readValue(responseJSON, RestError.class);
                log.error(error.getErrorCode() + ":" + error.getMessage() + ":" + error.getDeveloperMessage());
            }
        } catch (IOException | URISyntaxException e) {
            log.error("Error while SELL request", e);
        }
    }

    public String buyOrder() {
        String positionId = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            OpenPosition position = new OpenPosition(config.getProductId(), new BigMoney("BUX", 2, new BigDecimal(200)), 2, TradeDirection.BUY, "NEWS_ITEM");
            URIBuilder builder = new URIBuilder(config.getOpenUrl());
            URI httpURI = builder.build();
            HttpPost post = new HttpPost(httpURI);
            post.setEntity(new StringEntity(mapper.writeValueAsString(position)));
            post.setHeader("Connection", "Keep-Alive");
            post.setHeader("Proxy-Connection", "Keep-Alive");
            post.setHeader("Content-type", "application/json");
            post.setHeader("accept", "application/json");
            post.setHeader("Accept-Language", "nl-NL,en;q=0.8");
            post.setHeader("Authorization", AUTH_TOKEN);
            CloseableHttpResponse httpResponse = httpClient.execute(post);

            int httpStatus = httpResponse.getStatusLine().getStatusCode();
            if (httpStatus == 200) {
                String responseJSON = EntityUtils.toString(httpResponse.getEntity(), Charset.forName("UTF-8"));
                Trade trade = mapper.readValue(responseJSON, Trade.class);
                positionId = trade.getPositionId();
                log.info("BOUGHT!" + responseJSON);
            } else {
                String responseJSON = EntityUtils.toString(httpResponse.getEntity(), Charset.forName("UTF-8"));
                RestError error = mapper.readValue(responseJSON, RestError.class);
                log.error(error.getErrorCode() + ":" + error.getMessage() + ":" + error.getDeveloperMessage());
            }
        } catch (IOException | URISyntaxException e) {
            log.error("Error while BUY request", e);
        }
        return positionId;
    }
}
