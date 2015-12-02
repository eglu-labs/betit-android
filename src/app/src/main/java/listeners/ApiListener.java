package listeners;

import com.gasstan.backend.myApi.model.MyBean;

public interface ApiListener {
    void onApiResponse(MyBean response);
}
