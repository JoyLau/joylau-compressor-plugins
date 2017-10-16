package cn.joylau.code;

/**
 * Created by LiuFa on 2017/10/14.
 * cn.joylau.code
 * greatapp
 */
public interface Compressor<T> {
    void compress(String fileName,T config);
}
