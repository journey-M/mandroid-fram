package dev.guowj.androidfram.net.https;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import dev.guowj.androidfram.App;
import dev.guowj.androidfram.R;

/**
 * Created by bwang on 2016/5/28.
 */
public class GoogleSsl {


    public static SSLContext test() {

        SSLContext context = null;
        try {
            // Load CAs from an InputStream
            // (could be from a resource or ByteArrayInputStream or ...)
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            // From https://www.washington.edu/itconnect/security/ca/load-der.crt
            // InputStream caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));

            InputStream caInput = App.getInstance().getResources().openRawResource(R.raw.zhiri);

            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
                System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
            } finally {
                caInput.close();
            }

            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            // Create an SSLContext that uses our TrustManager
            context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);

//            Tell the URLConnection to use a SocketFactory from our SSLContext
//            URL url = new URL("https://certs.cac.washington.edu/CAtest/");
//            HttpsURLConnection urlConnection =
//                    (HttpsURLConnection) url.openConnection();
//            urlConnection.setSSLSocketFactory(context.getSocketFactory());
//            InputStream in = urlConnection.getInputStream();
//            copyInputStreamToOutputStream(in, System.out);

        } catch (Exception e) {

        }
        return context;
    }


}
