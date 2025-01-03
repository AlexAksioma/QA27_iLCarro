package interfaces;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.awt.*;

public interface BaseApi {

    String BASE_URL = "https://ilcarro-backend.herokuapp.com";
    String REGISTRATION_PATH = "/v1/user/registration/usernamepassword";
    String LOGIN_PATH = "/v1/user/login/usernamepassword";
    String ADD_NEW_CAR_PATH = "/v1/cars";
    String GET_USER_CARS_PATH = "/v1/cars/my";
    String DELETE_CAR_PATH = "/v1/cars/";

    Gson GSON = new Gson();

    MediaType JSON = MediaType.get("application/json");

    OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();


}
