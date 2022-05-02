package top.imoli.girl.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author moli
 * @date 2022/5/1 16:13
 */
@TableName("t_u_girl")
public class Girl {

    private int id;
    private String chineseName;
    private String girlName;
    private String englishName;
    private String alias;
    private String birthday;
    private String constellation;
    private String zodiac;
    private String bloodType;
    private String height;
    private String weight;
    private String threeCircumference;
    private String birth;
    private String occupation;
    private String interest;
    private String introduction;
    private String createTime;
    private String hits;
    private String cover;
    private int status;

    public Girl() {
    }

    private Girl(Builder builder) {
        setId(builder.id);
        setChineseName(builder.chineseName);
        setGirlName(builder.girlName);
        setEnglishName(builder.englishName);
        setAlias(builder.alias);
        setBirthday(builder.birthday);
        setConstellation(builder.constellation);
        setZodiac(builder.zodiac);
        setBloodType(builder.bloodType);
        setHeight(builder.height);
        setWeight(builder.weight);
        setThreeCircumference(builder.threeCircumference);
        setBirth(builder.birth);
        setOccupation(builder.occupation);
        setInterest(builder.interest);
        setIntroduction(builder.introduction);
        setCreateTime(builder.createTime);
        setHits(builder.hits);
        setCover(builder.cover);
        setStatus(builder.status);
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

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getGirlName() {
        return girlName;
    }

    public void setGirlName(String girlName) {
        this.girlName = girlName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getThreeCircumference() {
        return threeCircumference;
    }

    public void setThreeCircumference(String threeCircumference) {
        this.threeCircumference = threeCircumference;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static final class Builder {
        private int id;
        private String chineseName;
        private String girlName;
        private String englishName;
        private String alias;
        private String birthday;
        private String constellation;
        private String zodiac;
        private String bloodType;
        private String height;
        private String weight;
        private String threeCircumference;
        private String birth;
        private String occupation;
        private String interest;
        private String introduction;
        private String createTime;
        private String hits;
        private String cover;
        private int status;

        private Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder chineseName(String chineseName) {
            this.chineseName = chineseName;
            return this;
        }

        public Builder girlName(String girlName) {
            this.girlName = girlName;
            return this;
        }

        public Builder englishName(String englishName) {
            this.englishName = englishName;
            return this;
        }

        public Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public Builder birthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder constellation(String constellation) {
            this.constellation = constellation;
            return this;
        }

        public Builder zodiac(String zodiac) {
            this.zodiac = zodiac;
            return this;
        }

        public Builder bloodType(String bloodType) {
            this.bloodType = bloodType;
            return this;
        }

        public Builder height(String height) {
            this.height = height;
            return this;
        }

        public Builder weight(String weight) {
            this.weight = weight;
            return this;
        }

        public Builder threeCircumference(String threeCircumference) {
            this.threeCircumference = threeCircumference;
            return this;
        }

        public Builder birth(String birth) {
            this.birth = birth;
            return this;
        }

        public Builder occupation(String occupation) {
            this.occupation = occupation;
            return this;
        }

        public Builder interest(String interest) {
            this.interest = interest;
            return this;
        }

        public Builder introduction(String introduction) {
            this.introduction = introduction;
            return this;
        }

        public Builder createTime(String createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder hits(String hits) {
            this.hits = hits;
            return this;
        }

        public Builder cover(String cover) {
            this.cover = cover;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Girl build() {
            return new Girl(this);
        }
    }
}
