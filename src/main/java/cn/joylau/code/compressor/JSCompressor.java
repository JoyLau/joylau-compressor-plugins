package cn.joylau.code.compressor;

import cn.joylau.code.Compressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import java.io.*;

/**
 * Created by LiuFa on 2017/10/14.
 * cn.joylau.code
 * greatapp
 */
@Data
@NoArgsConstructor
@Log
public class JSCompressor implements Compressor {

    //是否混淆
    private boolean munge = true;
    //保留所有的分号
    private boolean preserveAllSemiColons = false;
    //禁用自带的所有优化措施
    private boolean disableOptimizations = false;
    //在一个指定的列数之后插入一个换行（一般是不需要的）
    private int lineBreak = -1;

    private ErrorReporter errorReporter = new DefaultErrorReporter();


    @Override
    public void compress(String fileName) {
        Reader in = null;
        Writer out = null;
        try {
            String charsetName = "UTF-8";
            in = new InputStreamReader(new FileInputStream(fileName), charsetName);
            JavaScriptCompressor compressor = new JavaScriptCompressor(in, errorReporter);
            out = new OutputStreamWriter(new FileOutputStream(fileName), charsetName);
            compressor.compress(out, lineBreak, true, false, preserveAllSemiColons, disableOptimizations);
        } catch (IOException e) {
            log.info("js compress error");
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

    public static class DefaultErrorReporter implements ErrorReporter {

        public void warning(String message, String sourceName, int line, String lineSource, int lineOffset) {
            if (line < 0) {
                System.err.println("[WARNING] JSCompressor: \"" + message + "\" during JavaScript compression");
            } else {
                System.err.println("[WARNING] JSCompressor: \"" + message + "\" at line [" + line + ":" + lineOffset
                        + "] during JavaScript compression" + (lineSource != null ? ": " + lineSource : ""));
            }
        }

        public void error(String message, String sourceName, int line, String lineSource, int lineOffset) {
            if (line < 0) {
                System.err.println("[ERROR] JSCompressor: \"" + message + "\" during JavaScript compression");
            } else {
                System.err.println("[ERROR] JSCompressor: \"" + message + "\" at line [" + line + ":" + lineOffset +
                        "] during JavaScript compression" + (lineSource != null ? ": " + lineSource : ""));
            }
        }

        public EvaluatorException runtimeError(String message, String sourceName, int line, String lineSource, int
                lineOffset) {
            error(message, sourceName, line, lineSource, lineOffset);
            return new EvaluatorException(message);
        }
    }
}