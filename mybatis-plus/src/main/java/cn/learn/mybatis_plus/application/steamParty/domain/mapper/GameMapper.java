package cn.learn.mybatis_plus.application.steamParty.domain.mapper;

import cn.learn.mybatis_plus.application.steamParty.domain.Game;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

/**
 * @author win
 */
public interface GameMapper extends BaseMapper<Game> {
  List<Game> selectAll();

}