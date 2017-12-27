package ucml.drsv.upcommingmovieslist.services;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


import retrofit.RestAdapter;

/**
 * Created by denny on 21/12/17.
 */

public class ImdbClientBuilder {


    static {
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException e) {

        }
        TrustManager[] trustAllCerts = getTrustManager();
        try {
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

    private static TrustManager[] getTrustManager() {
        TrustManager[] trustAllCerts = new TrustManager[] {

                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
                            throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
                            throws CertificateException {

                    }

                } };

        return trustAllCerts;

    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        RestAdapter.Builder builder = new RestAdapter.Builder().setEndpoint(baseUrl);
        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }


}
