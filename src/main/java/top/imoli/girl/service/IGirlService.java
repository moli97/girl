package top.imoli.girl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.imoli.girl.entity.Girl;

/**
 * @author moli
 * @date 2022/5/1 19:20
 */
public interface IGirlService extends IService<Girl> {

    boolean parseGirl(int page);

    boolean parseAlbum(int girlId);

}
