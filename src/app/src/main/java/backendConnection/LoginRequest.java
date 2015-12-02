package backendConnection;

import android.os.AsyncTask;

import com.gasstan.backend.myApi.MyApi;
import com.gasstan.backend.myApi.model.MyBean;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import data.User;
import listeners.ApiListener;

public class LoginRequest extends AsyncTask<User, Void, MyBean> {
    private static MyApi myApiService = null;
    private ApiListener apiListener;

    public LoginRequest(ApiListener apiListener) {
        this.apiListener = apiListener;
    }

    @Override
    protected MyBean doInBackground(User... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://egluapp.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        User user = params[0];
        try {
            return myApiService.login(user.getEmail(), user.getPassword()).execute();
        } catch (IOException e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    protected void onPostExecute(MyBean result) {
        apiListener.onApiResponse(result);
    }
}
