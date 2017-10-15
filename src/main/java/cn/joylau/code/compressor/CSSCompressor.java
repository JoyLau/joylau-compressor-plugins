package cn.joylau.code.compressor;

import cn.joylau.code.Compressor;
import com.yahoo.platform.yui.compressor.CssCompressor;
import lombok.Data;
import lombok.extern.java.Log;

import java.io.*;

/**
 * Created by LiuFa on 2017/10/14.
 * cn.joylau.code
 * greatapp
 */
@Log
@Data
public class CSSCompressor implements Compressor {

    //在一个指定的列数之后插入一个换行（一般是不需要的）
    private int lineBreak = -1;

    @Override
    public void compress(String fileName) {
        Reader in = null;
        Writer out = null;
        try {
            String charsetName = "UTF-8";
            in = new InputStreamReader(new FileInputStream(fileName), charsetName);
            CssCompressor csscompressor = new CssCompressor(in);
            out = new OutputStreamWriter(new FileOutputStream(fileName), charsetName);
            csscompressor.compress(out, lineBreak);
        } catch (Exception e) {
            log.info("css compress error");
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}