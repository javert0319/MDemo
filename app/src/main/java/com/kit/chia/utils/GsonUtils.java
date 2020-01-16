package com.kit.chia.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kit.chia.brvah.bean.MusicBean;

import java.util.List;

/**
 * @ClassName: GsonUtils
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/6
 */
public class GsonUtils {
    private static Gson gson = new Gson();

    public static MusicBean jsonToObject(String jsonStr) {
        return gson.fromJson(jsonStr, MusicBean.class);
    }

    public static List<MusicBean> jsonToList(String jsonStr) {
        return gson.fromJson(jsonStr, new TypeToken<List<MusicBean>>(){}.getType());
    }

    public String objectOrListToJson(Object object) {
        return gson.toJson(object);
    }
}
