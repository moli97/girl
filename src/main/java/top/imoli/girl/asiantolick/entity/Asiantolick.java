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


    public static final class Builder {
        private int postId;
        private String href;
        private String alt;
        private int size;
        private String downHref;
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
            asiantolick.setStatus(status);
            return asiantolick;
        }
    }
}
