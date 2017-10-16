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
    //是否混淆
    private boolean munge = false;
    //保留所有的分号
    private boolean preserveAllSemiColons = false;
    //禁用自带的所有优化措施
    private boolean disableOptimizations = false;
}
