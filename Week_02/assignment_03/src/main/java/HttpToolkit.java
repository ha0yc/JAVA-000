import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpToolkit {
    private PoolingHttpClientConnectionManager poolManager;
    private static final HttpToolkit INSTANCE = new HttpToolkit();

    public static HttpToolkit getInstance() {
        return INSTANCE;
    }

    public void init(){
        poolManager = new PoolingHttpClientConnectionManager(60000, TimeUnit.MILLISECONDS);
    }

    public String doGet(String url) throws IOException {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolManager)
                .build();
        try {
            final HttpGet httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return EntityUtils.toString(response.getEntity());
    }

    public static void main(String[] args) {
        HttpToolkit.getInstance().init();
        try {
            System.out.println(HttpToolkit.getInstance().doGet("http://localhost:8088/api/hello"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
