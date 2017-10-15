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
public class JSConfig {
    private String dir;
    private String include;
    private String exclude;
}
