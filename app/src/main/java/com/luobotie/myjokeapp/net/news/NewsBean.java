package com.luobotie.myjokeapp.net.news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * TheKing_Shun 欢迎来到我的 ->MyJokeApp<- 项目
 * 该项目创建于2017/10/17.
 * 努力 加油 我是最棒的！2017
 */

public class NewsBean {


    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {


        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {


            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            @SerializedName("thumbnail_pic_s")
            private String pic_one;
            @SerializedName("thumbnail_pic_s02")
            private String pic_two;
            @SerializedName("thumbnail_pic_s03")
            private String pic_three;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getPic_one() {
                return pic_one;
            }

            public void setPic_one(String pic_one) {
                this.pic_one = pic_one;
            }

            public String getPic_two() {
                return pic_two;
            }

            public void setPic_two(String pic_two) {
                this.pic_two = pic_two;
            }

            public String getPic_three() {
                return pic_three;
            }

            public void setPic_three(String pic_three) {
                this.pic_three = pic_three;
            }
        }
    }
}
