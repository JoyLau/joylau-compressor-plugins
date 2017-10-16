package cn.joylau.code.config;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by LiuFa on 2017/10/14.
 * cn.joylau.code
 * greatapp
 */
@Data
@NoArgsConstructor
public class HTMLConfig {
    private String dir;
    private String include;
    private String exclude;
    private boolean removeComments = true;
    private boolean removeIntertagSpaces = false;
    private boolean compressJavaScript = false;
    private boolean compressCss = false;
}
