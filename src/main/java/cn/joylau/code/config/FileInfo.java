package cn.joylau.code.config;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by LiuFa on 2017/10/14.
 * cn.joylau.code.config
 * greatapp
 */
@Data
@NoArgsConstructor
public class FileInfo {
    private String fileName;
    private long fileLength;
    private String fileSize;
}
