package top.imoli.girl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.imoli.girl.entity.Album;
import top.imoli.girl.entity.Girl;

/**
 * @author moli
 * @date 2022/5/1 19:19
 */
@Mapper
public interface AlbumMapper extends BaseMapper<Album> {
}

