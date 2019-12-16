package com.e.webapirequest.webApi.web;

import java.io.IOException;
import java.security.Key;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public class WebApi {

    public static final String API_URL = "https://developers.zomato.com/api/v2.1/";

    public static final String CONTENT_TYPE_JSON = "Content-Type: application/json";
    public static final String APP_JSON = "application/json";
    public static final String ACEPT_JSON = "Accept: application/json";
    public static final String KEY = "key-user: 6047611312f65c0a950a9d1d9f937246";

    private static Retrofit mRetrofitInstance;

    private static Retrofit mRetrofitInstanceRxJava;

    private static final int TIMEOUT_REQUEST = 90;
    private static OkHttpClient mOkHttpClient;
    private static final String KEY_USER = "6047611312f65c0a950a9d1d9f937246";

    /**
     * <p>Código de error mandado por el servidor.</p>
     */
    public interface CODIGO_SERVIDOR {
        // Respuestas informativas
        int CONTINUE = 100;
        int SWITCHING_PROTOCOL = 101;
        int PROCESSING = 102;
        // Respuestas satisfactorias
        int OK = 200;
        int CREATED = 201;
        int ACCEPTED = 202;
        int NON_AUTHORITATIVE_INFORMATION = 203;
        int NO_CONTENT = 204;
        int RESET_CONTENT = 205;
        int PARTIAL_CONTENT = 206;
        int MULTI_STATUS = 207;
        int IM_USER = 226;
        int MULTIPLE_CHOICES = 300;
        int MOVED_PERMANENTLY = 301;
        int FOUND = 302;
        int SEE_OTHER = 303;
        int NOT_MODIFIED = 304;
        int USE_PROXY = 305;
        int UNUSED = 306;
        int TEMPORARY_REDIRECT = 307;
        int PERMANENT_REDIRECT = 308;
        // Errores del cliente
        int BAD_REQUEST = 400;
        int UNAUTHORIZED = 401;
        int PAYMENT_REQUIRED = 402;
        int FORBIDDEN = 403;
        int NOT_FOUND = 404;
        int METHOD_NOT_ALLOWED = 405;
        int NOT_ACCEPTABLE = 406;
        int PROXY_AUTHENTICATION_REQUIRED = 407;
        int REQUEST_TIMEOUT = 408;
        int CONFLICT = 409;
        int GONE = 410;
        int LENGTH_REQUIRED = 411;
        int PRECONDITION_FAILED = 412;
        int PAYLOAD_TOO_LARGE = 413;
        int URI_TOO_LONG = 414;
        int UNSUPPORTED_MEDIA_TYPE = 415;
        int REQUESTED_RANGE_NOT_SATISFIABLE = 416;
        int EXPECTATION_FAILED = 417;
        int TEAPOT = 418;
        int MISDIRECTED_REQUEST = 421;
        int UNPROCESSABLE_ENTITY = 422;
        int LOCKED = 423;
        int FAILED_DEPENDENCY = 424;
        int UPGRADE_REQUIRED = 426;
        int PRECONDITION_REQUIRED = 428;
        int TOO_MANY_REQUEST = 429;
        int REQUEST_HEADER_FIELDS_TOO_LARGE = 431;
        int UNAVAILABLE_FOR_LEGAL_REASONS = 451;
        // Errores del servidor
        int INTERNAL_SERVER_ERROR = 500;
        int NOT_IMPLEMENTED = 501;
        int BAD_GATEWAY = 502;
        int SERVICE_UNAVAILABLE = 503;
        int GATEWAY_TIMEOUT = 504;
        int HTTP_VERSION_NOT_SUPPORTED = 505;
        int VARIANT_ALSO_NEGOTIATES = 506;
        int INSUFFICIENT_STORAGE =507;
        int LOOP_DETECTED = 508;
        int NOT_EXTENDED = 510;
        int NETWORK_AUTHENTICATION_REQUIRED = 511;
    }

    /**
     * <p>Código de error mandado por la respuesta del servidor.</p>
     */
    public interface CODIGO_RESPUESTA {
        int RESPONSE_OK = 200;
        int RESPONSE_CREDENCIALES_INCORRECTAS = 204;
        int RESPONSE_ERROR_CREACION = 3;
    }

    /**
     * <p>Método que permite obtener al cliente de la petición Retrofit.</p>
     * @return Objeto de la petición tipo Retrofit.
     */
    public static Retrofit getCliente() {
        if (mOkHttpClient == null) {
            initOkHttp();

        }

        if (mRetrofitInstanceRxJava == null) {
            mRetrofitInstanceRxJava = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(mOkHttpClient)
                    .build();
        }

        return mRetrofitInstanceRxJava;
    }

    /**
     * <p>Método que permite obtener al cliente de la petición Retrofit.</p>
     * @return Objeto de la petición tipo Retrofit.
     */
    public static Retrofit getClienteRxJava() {
        if (mOkHttpClient == null) {
            initOkHttp();
        }

        if (mRetrofitInstance == null) {
            mRetrofitInstance = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(mOkHttpClient)
                    .build();
        }

        return mRetrofitInstance;
    }

    /**
     * <p>Método que inicializa el cliente Http con parámetros como el tiempo máximo de respuesta de
     * la petición.</p>
     */
    private static void initOkHttp() {
        mOkHttpClient = getUnsafeOkHttpClient().build();
    }




    /**
     * <p>Método que permite validar la petición al servidor, con rutas de tipo HTTPS.</p>
     * @return Cliente con la validación al servidor.
     */
    private static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Accept:", APP_JSON)
                            .header("user-key", KEY_USER)
                            .method(original.method(),original.body())
                            .build();

                    return chain.proceed(request);
                }
            });
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.connectTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public interface GetMethods {
        @Headers({
                ACEPT_JSON,
                KEY
        })
        @GET("collections")
        Call<ResponseBody> requestObtenerLugares(@Query("city_id" )int city, @Query("count")int count);

        @Headers({
                ACEPT_JSON,
                KEY
        })
        @GET("restaurant")
        Call<ResponseBody> requestObtenerRestaurant(@Query("res_id")int id);


    }

}
