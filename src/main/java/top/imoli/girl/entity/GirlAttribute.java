package top.imoli.girl.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author moli
 * @date 2022/5/1 20:18
 */
public enum GirlAttribute {
    chineseName("中文名", (b, c) -> b.chineseName(c)),
    name("名字", (b, c) -> b.girlName(c)),
    englishName("英文名", (b, c) -> b.englishName(c)),
    alias("别名", (b, c) -> b.alias(c)),
    birthday("生日", (b, c) -> b.birthday(c)),
    constellation("星座", (b, c) -> b.constellation(c)),
    zodiac("属相", (b, c) -> b.zodiac(c)),
    bloodType("血型", (b, c) -> b.bloodType(c)),
    height("身高", (b, c) -> b.height(c)),
    weight("体重", (b, c) -> b.weight(c)),
    threeCircumference("三围", (b, c) -> b.threeCircumference(c)),
    birth("出生", (b, c) -> b.birth(c)),
    occupation("职业", (b, c) -> b.occupation(c)),
    interest("兴趣", (b, c) -> b.interest(c)),
    introduction("简介"),
    create("创建时间", (b, c) -> b.createTime(c)),
    hits("点击", (b, c) -> b.hits(c)),
    cover("封面", (b, c) -> b.cover(c)),
    collected("收录图集"),
    ;
    public final String value;
    public final BiConsumer<Girl.Builder, String> consumer;

    GirlAttribute(String value) {
        this.value = value;
        this.consumer = (builder, content) -> {};
    }

    GirlAttribute(String value, BiConsumer<Girl.Builder, String> consumer) {
        this.value = value;
        this.consumer = consumer;
    }

    public static final Map<String, GirlAttribute> map = new HashMap<>(values().length);

    static {
        for (GirlAttribute value : values()) {
            map.put(value.value, value);
        }
    }

    public static GirlAttribute of(String value) {
        return map.get(value);
    }

    public void setValue(Girl.Builder builder, String content) {
        consumer.accept(builder, content);
    }
}
