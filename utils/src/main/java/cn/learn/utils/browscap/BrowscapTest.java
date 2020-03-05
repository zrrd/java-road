package cn.learn.utils.browscap;

import com.blueconic.browscap.BrowsCapField;
import com.blueconic.browscap.Capabilities;
import com.blueconic.browscap.ParseException;
import com.blueconic.browscap.UserAgentParser;
import com.blueconic.browscap.UserAgentService;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author shaoyijiong
 * @date 2020/3/5
 */
public class BrowscapTest {

  private static String chrome = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36";
  private static String android = "Mozilla/5.0 (Linux; Android 9; MRD-AL00 Build/HUAWEIMRD-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.136 Mobile Safari/537.36 autohomeapp/1.1 (auto_android;10.5.0;bd83OCH_SAIUYZAdy5PMLArt4wpnvBIGRQk2IMvpnS8NQnOUPQ_6T9lQuSjhPEh0LRp7f74sok8E3HPIpSo7GSqs-4xsW4ZY;9;MRD-AL00;86b01d15c1aec6d83ce3e4c65483a7e9) auto_android/10.5.0 nettype/4g";
  private static String ios = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 auto_iphone/9.11.6 nettype/wifi autohomeapp/1.0 (auto_iphone;9.11.6;ogVdSGe10f0msF8sfFgt6ICqejn25XPsH9pRGovTt-_WLZ23svBNYqlgSLW6ICDj8CM_gUspTJ5EUaBCUKWKlKpoi3z9PPf8f_K68RhByIiBQ_K7rtZ2EpQ3wu9XzRCS;13.3;iPhone;6CC911A997BCCC616A67088234E23060)";

  /**
   * 使用默认的解析器
   */
  final UserAgentParser parser = new UserAgentService().loadParser();
  /**
   * 自定义解析器属性
   */
  final UserAgentParser parser1 = new UserAgentService().loadParser(Arrays.asList(
      BrowsCapField.IS_MASTER_PARENT,
      BrowsCapField.IS_LITE_MODE,
      BrowsCapField.PARENT,
      BrowsCapField.COMMENT,
      BrowsCapField.BROWSER,
      BrowsCapField.BROWSER_TYPE,
      BrowsCapField.BROWSER_BITS,
      BrowsCapField.BROWSER_MAKER,
      BrowsCapField.BROWSER_MODUS,
      BrowsCapField.BROWSER_VERSION,
      BrowsCapField.BROWSER_MAJOR_VERSION,
      BrowsCapField.BROWSER_MINOR_VERSION,
      BrowsCapField.PLATFORM,
      BrowsCapField.PLATFORM_VERSION,
      BrowsCapField.PLATFORM_DESCRIPTION,
      BrowsCapField.PLATFORM_BITS,
      BrowsCapField.PLATFORM_MAKER,
      BrowsCapField.IS_ALPHA,
      BrowsCapField.IS_BETA,
      BrowsCapField.IS_WIN16,
      BrowsCapField.IS_WIN32,
      BrowsCapField.IS_WIN64,
      BrowsCapField.IS_IFRAMES,
      BrowsCapField.IS_FRAMES,
      BrowsCapField.IS_TABLES,
      BrowsCapField.IS_COOKIES,
      BrowsCapField.IS_BACKGROUND_SOUNDS,
      BrowsCapField.IS_JAVASCRIPT,
      BrowsCapField.IS_VBSCRIPT,
      BrowsCapField.IS_JAVA_APPLETS,
      BrowsCapField.IS_ACTIVEX_CONTROLS,
      BrowsCapField.IS_MOBILE_DEVICE,
      BrowsCapField.IS_TABLET,
      BrowsCapField.IS_SYNDICATION_READER,
      BrowsCapField.IS_CRAWLER,
      BrowsCapField.IS_FAKE,
      BrowsCapField.IS_ANONYMIZED,
      BrowsCapField.IS_MODIFIED,
      BrowsCapField.CSS_VERSION,
      BrowsCapField.AOL_VERSION,
      BrowsCapField.DEVICE_NAME,
      BrowsCapField.DEVICE_MAKER,
      BrowsCapField.DEVICE_TYPE,
      BrowsCapField.DEVICE_POINTING_METHOD,
      BrowsCapField.DEVICE_CODE_NAME,
      BrowsCapField.DEVICE_BRAND_NAME,
      BrowsCapField.RENDERING_ENGINE_NAME,
      BrowsCapField.RENDERING_ENGINE_VERSION,
      BrowsCapField.RENDERING_ENGINE_DESCRIPTION,
      BrowsCapField.RENDERING_ENGINE_MAKER));

  public BrowscapTest() throws IOException, ParseException {
  }

  private void parserTest() {
    final Capabilities capabilities = parser1.parse(chrome);
    capabilities.getBrowser();
    capabilities.getBrowserMajorVersion();
    capabilities.getBrowserType();
    capabilities.getDeviceType();
    capabilities.getPlatform();
    capabilities.getPlatformVersion();
    capabilities.getValues();
  }

  public static void main(String[] args) {
    try {
      new BrowscapTest().parserTest();
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }
}
