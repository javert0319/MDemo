package com.nbhope.chia.viewpager

/**
 * @ClassName: MusicBean
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2020/1/3
 */
class MusicBean {

    var artist: String? = null
    var song: String? = null
    var icon: Int = 0

    constructor(artist: String) {
        this.artist = artist
    }

    constructor(artist: String, song: String, icon: Int) {
        this.artist = artist
        this.song = song
        this.icon = icon
    }

    constructor(icon: Int) {
        this.icon = icon
    }
}
