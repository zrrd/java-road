package cn.learn.validation.req;

import java.util.Date;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * 测试请求
 * 每个注释都能加message
 * 这些是原有的校验,还能自定义校验
 *
 * @author shaoyijiong
 * @date 2018/7/5
 */

@Data
public class TestRequest {
  /**
   * 不为空 @Null
   */
  @NotNull
  String notNullTest;

  /**
   * 必须为真 @AssertFalse
   */
  @AssertTrue
  String assertTrueTest;

  /**
   *   数字必须大于等于指定值  @Max
   */
  @Min(10)
  int minTest;

  /**
   * 数字在指定范围内
   */
  @Size(min = 1,max = 10)
  int sizeTest;

  /**
   * 必须为过去的日期 @Feature
   */
  @Past
  Date pastTest;

  /**
   * 验证字符串非null，且长度必须大于0
   */
  @NotBlank
  String notBlankTest;

  /**
   * 必须为邮箱地址
   */
  @Email
  String emailTest;

  /**
   * 被注释的字符串的大小必须在指定的范围内
   */
  @Length(min = 1,max = 5)
  String lengthTest;

  /**
   *  被注释的字符串的必须非空
   */
  @NotEmpty
  String notEmptyTest;

  /**
   * 被注释的元素必须在合适的范围内
   */
  @Range(min = 0,max = 2)
  String rangeTest;

}
