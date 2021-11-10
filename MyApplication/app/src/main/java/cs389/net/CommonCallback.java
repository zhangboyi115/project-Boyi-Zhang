package cs389.net;

import android.telecom.Call;

import com.example.onehome.utils.GsonUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class CommonCallback<T> extends StringCallback {

    Type mType;

    public CommonCallback(){
        Class<? extends CommonCallback> clazz = getClass();
        Type genericSuperclass = clazz.getGenericSuperclass();
        if(genericSuperclass instanceof Class){
            throw new RuntimeException("Miss Type Params");
        }

        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        mType =  parameterizedType.getActualTypeArguments()[0];
    }

    public void onError(Call call, Exception e, int id) {
        onError(e);
    }
    @Override
    public void onResponse(String response, int id) {

        try {
            JSONObject resp = new JSONObject(response);
            int resultCode = resp.getInt("resultCode");
            if (resultCode == 1){

                String data = resp.getString("data");

                onSuccess((T) GsonUtil.getGson().fromJson(data, mType));

            }else {
                onError(new RuntimeException(resp.getString("resultMessage")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            onError(e);
        }

    }

    protected abstract void onSuccess(T fromJson);

    public abstract void onError(Exception e);
}
