package com.nbhope.chia.cache.bean;

import java.util.List;

/**
 * @ClassName: CacheBean
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/4
 */
public class CacheBean {

    /**
     * code : 200
     * message : 成功!
     * result : [{"sid":"29953906","text":"最危险的地方就是最安全的地方，两天了，龙鱼快疯了\u2026","type":"video","thumbnail":"http://wimg.spriteapp.cn/picture/2019/1127/5dde1e22485fe_wpd.jpg","video":"http://uvideo.spriteapp.cn/video/2019/1127/5dde1e22485fe_wpd.mp4","images":null,"up":"62","down":"4","forward":"0","comment":"2","uid":"23131570","name":"塔卡塔","header":"http://wimg.spriteapp.cn/profile/large/2019/07/04/5d1d9079edf2e_mini.jpg","top_comments_content":null,"top_comments_voiceuri":null,"top_comments_uid":null,"top_comments_name":null,"top_comments_header":null,"passtime":"2019-12-04 02:58:02"},{"sid":"29931808","text":"这猫当时蹲在这好久了，哈哈哈。","type":"video","thumbnail":"http://wimg.spriteapp.cn/picture/2019/1115/5dcec3a11aeeb_wpd.jpg","video":"http://uvideo.spriteapp.cn/video/2019/1115/5dcec3a11aeeb_wpd.mp4","images":null,"up":"281","down":"9","forward":"3","comment":"17","uid":"23128674","name":"落水","header":"http://wimg.spriteapp.cn/profile/large/2019/07/04/5d1d70d768749_mini.jpg","top_comments_content":null,"top_comments_voiceuri":null,"top_comments_uid":null,"top_comments_name":null,"top_comments_header":null,"passtime":"2019-12-04 02:18:01"}]
     */

    private int code;
    private String message;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * sid : 29953906
         * text : 最危险的地方就是最安全的地方，两天了，龙鱼快疯了…
         * type : video
         * thumbnail : http://wimg.spriteapp.cn/picture/2019/1127/5dde1e22485fe_wpd.jpg
         * video : http://uvideo.spriteapp.cn/video/2019/1127/5dde1e22485fe_wpd.mp4
         * images : null
         * up : 62
         * down : 4
         * forward : 0
         * comment : 2
         * uid : 23131570
         * name : 塔卡塔
         * header : http://wimg.spriteapp.cn/profile/large/2019/07/04/5d1d9079edf2e_mini.jpg
         * top_comments_content : null
         * top_comments_voiceuri : null
         * top_comments_uid : null
         * top_comments_name : null
         * top_comments_header : null
         * passtime : 2019-12-04 02:58:02
         */

        private String sid;
        private String text;
        private String type;
        private String thumbnail;
        private String video;
        private Object images;
        private String up;
        private String down;
        private String forward;
        private String comment;
        private String uid;
        private String name;
        private String header;
        private Object top_comments_content;
        private Object top_comments_voiceuri;
        private Object top_comments_uid;
        private Object top_comments_name;
        private Object top_comments_header;
        private String passtime;

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public Object getImages() {
            return images;
        }

        public void setImages(Object images) {
            this.images = images;
        }

        public String getUp() {
            return up;
        }

        public void setUp(String up) {
            this.up = up;
        }

        public String getDown() {
            return down;
        }

        public void setDown(String down) {
            this.down = down;
        }

        public String getForward() {
            return forward;
        }

        public void setForward(String forward) {
            this.forward = forward;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public Object getTop_comments_content() {
            return top_comments_content;
        }

        public void setTop_comments_content(Object top_comments_content) {
            this.top_comments_content = top_comments_content;
        }

        public Object getTop_comments_voiceuri() {
            return top_comments_voiceuri;
        }

        public void setTop_comments_voiceuri(Object top_comments_voiceuri) {
            this.top_comments_voiceuri = top_comments_voiceuri;
        }

        public Object getTop_comments_uid() {
            return top_comments_uid;
        }

        public void setTop_comments_uid(Object top_comments_uid) {
            this.top_comments_uid = top_comments_uid;
        }

        public Object getTop_comments_name() {
            return top_comments_name;
        }

        public void setTop_comments_name(Object top_comments_name) {
            this.top_comments_name = top_comments_name;
        }

        public Object getTop_comments_header() {
            return top_comments_header;
        }

        public void setTop_comments_header(Object top_comments_header) {
            this.top_comments_header = top_comments_header;
        }

        public String getPasstime() {
            return passtime;
        }

        public void setPasstime(String passtime) {
            this.passtime = passtime;
        }
    }
}
