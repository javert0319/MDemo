package com.kit.chia.utils;

/**
 * @ClassName: GlobalParameters
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2020/1/14
 */
public class GlobalParameters {

    private static String PARAM_MUSIC_LIST;
    private static int MUSIC_MESSAGE = 0;

    public static int getMUSIC_MESSAGE() {
        return MUSIC_MESSAGE;
    }

    public static void setMUSIC_MESSAGE(int MUSIC_MESSAGE) {
        GlobalParameters.MUSIC_MESSAGE = MUSIC_MESSAGE;
    }

    public static String getPARAM_MUSIC_LIST() {
        return PARAM_MUSIC_LIST;
    }

    public static void setPARAM_MUSIC_LIST(String PARAM_MUSIC_LIST) {
        GlobalParameters.PARAM_MUSIC_LIST = PARAM_MUSIC_LIST;
    }

}
