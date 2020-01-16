package com.kit.discview.model;

import java.util.List;

/**
 * @ClassName: MusicRes
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2020/1/16
 */
public class MusicRes {

    private List<Integer> musicIcon;

    public MusicRes(List<Integer> musicIcon){
        this.musicIcon = musicIcon;
    }

    public List<Integer> getMusicIcon() {
        return musicIcon;
    }

    public void setMusicIcon(List<Integer> musicIcon) {
        this.musicIcon = musicIcon;
    }
}
