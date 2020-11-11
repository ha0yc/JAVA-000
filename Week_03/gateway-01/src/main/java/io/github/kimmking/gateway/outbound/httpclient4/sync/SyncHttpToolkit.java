package io.github.kimmking.gateway.outbound.httpclient4.sync;

import io.github.kimmking.gateway.outbound.httpclient4.IHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SyncHttpToolkit implements IHttpClient {
    private PoolingHttpClientConnectionManager poolManager;
    private static final SyncHttpToolkit INSTANCE = new SyncHttpToolkit();

    public static SyncHttpToolkit getInstance() {
        return INSTANCE;
    }

    static {
        SyncHttpToolkit.getInstance().init();
    }

    public void init(){
        poolManager = new PoolingHttpClientConnectionManager(60000, TimeUnit.MILLISECONDS);
    }

    public String doGet(final String url, final Map<String, String> headers) throws IOException {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolManager)
                .build();
        try {
            final HttpGet httpGet = new HttpGet(url);
            headers.forEach((k, v) -> httpGet.addHeader(k, v));
            response = httpClient.execute(httpGet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return EntityUtils.toString(response.getEntity());
    }

//    public static void main(String[] args) {
//        SyncHttpToolkit.getInstance().init();
//        try {
//            System.out.println(SyncHttpToolkit.getInstance().doGet("http://localhost:8088/api/hello"));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}
