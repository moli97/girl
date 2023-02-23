package top.imoli.girl.asiantolick.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author moli
 * @date 2022/5/1 16:24
 */
@TableName("t_u_asiantolick")
public class Asiantolick {
    private int postId;
    private String href;
    private String alt;
    private int size;
    private String downHref;
    private String creationDate;
    private String photosSize;
    private String albumSize;
    private int status;


    public Asiantolick() {
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDownHref() {
        return downHref;
    }

    public void setDownHref(String downHref) {
        this.downHref = downHref;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPhotosSize() {
        return photosSize;
    }

    public void setPhotosSize(String photosSize) {
        this.photosSize = photosSize;
    }

    public String getAlbumSize() {
        return albumSize;
    }

    public void setAlbumSize(String albumSize) {
        this.albumSize = albumSize;
    }

    public static final class Builder {
        private int postId;
        private String href;
        private String alt;
        private int size;
        private String downHref;
        private String creationDate;
        private String photosSize;
        private String albumSize;
        private int status;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder postId(int postId) {
            this.postId = postId;
            return this;
        }

        public Builder href(String href) {
            this.href = href;
            return this;
        }

        public Builder alt(String alt) {
            this.alt = alt;
            return this;
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public Builder downHref(String downHref) {
            this.downHref = downHref;
            return this;
        }

        public Builder creationDate(String creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder photosSize(String photosSize) {
            this.photosSize = photosSize;
            return this;
        }

        public Builder albumSize(String albumSize) {
            this.albumSize = albumSize;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Asiantolick build() {
            Asiantolick asiantolick = new Asiantolick();
            asiantolick.setPostId(postId);
            asiantolick.setHref(href);
            asiantolick.setAlt(alt);
            asiantolick.setSize(size);
            asiantolick.setDownHref(downHref);
            asiantolick.setCreationDate(creationDate);
            asiantolick.setPhotosSize(photosSize);
            asiantolick.setAlbumSize(albumSize);
            asiantolick.setStatus(status);
            return asiantolick;
        }
    }
}
