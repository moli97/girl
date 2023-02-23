package top.imoli.girl.asiantolick.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author moli
 * @date 2022/5/1 16:24
 */
@TableName("t_u_asiantolick")
public class Asiantolick {
    private int id;
    private String href;
    private String alt;
    private int size;
    private String downHref;
    private String description;
    private String galleryPictures;
    private String creationDate;
    private String photosSize;
    private String albumSize;
    private int status;
    private String downUrl;
    private String resourcesUrl;


    public Asiantolick() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGalleryPictures() {
        return galleryPictures;
    }

    public void setGalleryPictures(String galleryPictures) {
        this.galleryPictures = galleryPictures;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getResourcesUrl() {
        return resourcesUrl;
    }

    public void setResourcesUrl(String resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
    }


    public static final class Builder {
        private int id;
        private String href;
        private String alt;
        private int size;
        private String downHref;
        private String description;
        private String galleryPictures;
        private String creationDate;
        private String photosSize;
        private String albumSize;
        private int status;
        private String downUrl;
        private String resourcesUrl;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder id(int id) {
            this.id = id;
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

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder galleryPictures(String galleryPictures) {
            this.galleryPictures = galleryPictures;
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

        public Builder downUrl(String downUrl) {
            this.downUrl = downUrl;
            return this;
        }

        public Builder resourcesUrl(String resourcesUrl) {
            this.resourcesUrl = resourcesUrl;
            return this;
        }

        public Asiantolick build() {
            Asiantolick asiantolick = new Asiantolick();
            asiantolick.setId(id);
            asiantolick.setHref(href);
            asiantolick.setAlt(alt);
            asiantolick.setSize(size);
            asiantolick.setDownHref(downHref);
            asiantolick.setDescription(description);
            asiantolick.setGalleryPictures(galleryPictures);
            asiantolick.setCreationDate(creationDate);
            asiantolick.setPhotosSize(photosSize);
            asiantolick.setAlbumSize(albumSize);
            asiantolick.setStatus(status);
            asiantolick.setDownUrl(downUrl);
            asiantolick.setResourcesUrl(resourcesUrl);
            return asiantolick;
        }
    }
}
