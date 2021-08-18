package cn.learn.test.unit;

import cn.learn.test.bean.Game;
import cn.learn.test.bean.GameVo;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 游戏转化工具类 (用于单元测试)
 *
 * @author shaoyijiong
 * @date 2021/8/18
 */
public class GameConvertUnits {

  public static GameVo convert(Game game) {
    if (game == null) {
      throw new RuntimeException("参数不能为空");
    }
    GameVo gameVo = new GameVo();
    gameVo.setName(game.getName());
    gameVo.setImage(game.getImage());
    // 保留1位小数 4舍5入
    gameVo.setPrice(game.getPrice().setScale(1, BigDecimal.ROUND_HALF_UP).toString());
    // 保留1位小数 4舍5入
    NumberFormat numberFormat = NumberFormat.getInstance();
    numberFormat.setRoundingMode(RoundingMode.HALF_UP);
    numberFormat.setMaximumFractionDigits(1);
    gameVo.setScore(numberFormat.format(game.getScore()));
    gameVo.setPublishDate(game.getPublishDate().format(DateTimeFormatter.ISO_DATE));
    if (game.getStatus() == 1) {
      gameVo.setStatus("在售");
    } else if (game.getStatus() == 9) {
      gameVo.setStatus("删除");
    } else if (game.getStatus() == 0) {
      gameVo.setStatus("未发售");
    } else {
      gameVo.setStatus("未知");
    }
    game.setType(game.getType());
    return gameVo;
  }

  public static List<GameVo> convertList(List<Game> games) {
    List<GameVo> gameVos = new ArrayList<>();
    for (Game game : games) {
      gameVos.add(convert(game));
    }
    return gameVos;
  }
}
