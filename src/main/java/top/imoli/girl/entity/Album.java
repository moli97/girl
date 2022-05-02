package top.imoli.girl.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * @author moli
 * @date 2022/5/1 16:24
 */
@TableName("t_u_album")
public class Album {
    private int id;
    private int girlId;
    private String title;
    private String cover;
    private int size;
    private int status;

    public Album() {
    }

    private Album(Builder builder) {
        setId(builder.id);
        setGirlId(builder.girlId);
        setTitle(builder.title);
        setCover(builder.cover);
        setSize(builder.size);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGirlId() {
        return girlId;
    }

    public void setGirlId(int girlId) {
        this.girlId = girlId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static final class Builder {
        private int id;
        private int girlId;
        private String title;
        private String cover;
        private int size;
        private int status;

        private Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder girlId(int girlId) {
            this.girlId = girlId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder cover(String cover) {
            this.cover = cover;
            return this;
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Album build() {
            return new Album(this);
        }
    }
}
