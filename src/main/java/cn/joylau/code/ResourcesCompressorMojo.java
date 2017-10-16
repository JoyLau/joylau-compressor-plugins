package cn.joylau.code;

import cn.joylau.code.compressor.CSSCompressor;
import cn.joylau.code.compressor.HTMLCompressor;
import cn.joylau.code.compressor.JSCompressor;
import cn.joylau.code.config.CSSConfig;
import cn.joylau.code.config.FileInfo;
import cn.joylau.code.config.HTMLConfig;
import cn.joylau.code.config.JSConfig;
import org.apache.maven.plugin.AbstractMojo;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @goal resource-compressor
 * @phase compile
 */
public class ResourcesCompressorMojo extends AbstractMojo {
    /**
     * @parameter property="project.build.directory"
     * @required
     */
    private File targetDir;

    /**
     * @parameter property="project.basedir"
     * @required
     * @readonly
     */
    private File baseDir;
    /**
     * @parameter property="project.build.sourceDirectory"
     * @required
     * @readonly
     */
    private File sourceDir;

    /**
     * @parameter property="project.build.outputDirectory"
     * @required
     * @readonly
     */
    private File outDir;

    /**
     * @parameter property="project.build.testSourceDirectory"
     * @required
     * @readonly
     */
    private File testSourceDir;

    /**
     * @parameter
     */
    private CSSConfig[] cssConfigs;

    /**
     * @parameter
     */
    private JSConfig[] jsConfigs;

    /**
     * @parameter
     */
    private HTMLConfig[] htmlConfigs;


    public void execute() {
        compressCSS();
        compressJS();
        compressHTML();
    }

    /**
     * 压缩css文件
     */
    private void compressCSS() {
        if (null == cssConfigs || null == cssConfigs[0].getDir()) {
            return;
        }
        getLog().info("开始压缩CSS文件...");
        try {
            /*压缩css*/
            for (CSSConfig cssConfig : cssConfigs) {
                List list = findFiles(new File(outDir.getPath() + cssConfig.getDir()), cssConfig.getInclude
                        (), cssConfig.getExclude(), new ArrayList<>());
                for (Object file : list) {
                    if (file instanceof File) {
                        FileInfo source = getFileSize(((File) file));
                        CSSCompressor cssCompressor = new CSSCompressor();
                        cssCompressor.compress(((File) file).getPath(),cssConfig);
                        FileInfo target = getFileSize(((File) file));
                        getLog().info(compressorInfo(source, target));
                    }
                }
            }
        } catch (Exception e) {
            getLog().error("css compress error");
            e.printStackTrace();
        }
    }

    /**
     * 压缩js文件
     */
    private void compressJS() {
        if (null == jsConfigs || null == jsConfigs[0].getDir()) {
            return;
        }
        getLog().info("开始压缩JS文件...");
        try {
            for (JSConfig jsConfig : jsConfigs) {
                List list = findFiles(new File(outDir.getPath() + jsConfig.getDir()), jsConfig.getInclude
                        (), jsConfig.getExclude(), new ArrayList<>());
                for (Object file : list) {
                    if (file instanceof File) {
                        FileInfo source = getFileSize(((File) file));
                        JSCompressor jsCompressor = new JSCompressor();
                        jsCompressor.compress(((File) file).getPath(),jsConfig);
                        FileInfo target = getFileSize(((File) file));
                        getLog().info(compressorInfo(source, target));
                    }
                }
            }
        } catch (Exception e) {
            getLog().error("js compress error");
            e.printStackTrace();
        }
    }


    /**
     * 压缩html文件
     */
    private void compressHTML() {
        if (null == htmlConfigs || null == htmlConfigs[0].getDir()) {
            return;
        }
        getLog().info("开始压缩HTML文件...");
        try {
            for (HTMLConfig htmlConfig : htmlConfigs) {
                List list = findFiles(new File(outDir.getPath() + htmlConfig.getDir()), htmlConfig.getInclude
                        (), htmlConfig.getExclude(), new ArrayList<>());
                for (Object file : list) {
                    if (file instanceof File) {
                        FileInfo source = getFileSize(((File) file));
                        HTMLCompressor htmlCompressor = new HTMLCompressor();
                        htmlCompressor.compress(((File) file).getPath(),htmlConfig);
                        FileInfo target = getFileSize(((File) file));
                        getLog().info(compressorInfo(source, target));
                    }
                }
            }
        } catch (Exception e) {
            getLog().error("html compress error");
            e.printStackTrace();
        }
    }


    /**
     * 获取文件大小，等信息
     *
     * @param file 目标问价
     * @return FileInfo
     */
    private FileInfo getFileSize(File file) {
        String size;
        FileInfo fileInfo = new FileInfo();
        if (file.exists() && file.isFile()) {
            long fileS = file.length();
            fileInfo.setFileLength(fileS);
            fileInfo.setFileName(file.getName());
            DecimalFormat df = new DecimalFormat("#.00");
            if (fileS < 1024) {
                size = df.format((double) fileS) + "BT";
            } else if (fileS < 1048576) {
                size = df.format((double) fileS / 1024) + "KB";
            } else if (fileS < 1073741824) {
                size = df.format((double) fileS / 1048576) + "MB";
            } else {
                size = df.format((double) fileS / 1073741824) + "GB";
            }
        } else if (file.exists() && file.isDirectory()) {
            size = "";
        } else {
            size = "0BT";
        }
        fileInfo.setFileSize(size);
        return fileInfo;
    }

    /**
     * 压缩信息
     *
     * @param source 源文件
     * @param target 压缩文件
     * @return 压缩信息
     */
    private String compressorInfo(FileInfo source, FileInfo target) {
        DecimalFormat df = new DecimalFormat("#.00");
        long compressorLength = source.getFileLength() - target.getFileLength();
        String rate = df.format((float) compressorLength / source.getFileLength() * 100);
        return target.getFileName() + "(" + source.getFileSize() + "==>" + target.getFileSize() + "," + rate + "%)";

    }


    /**
     * 递归查找目标文件
     *
     * @param directory 源文件
     * @param includes  包括文件
     * @param excludes  排除文件
     * @param list      目标文件集合
     * @return 目标文件集合
     */
    private List<File> findFiles(File directory, String includes, String excludes, List<File> list) {
        try {
            List fileList = FileUtils.getFiles(directory, includes, excludes);
            for (Object path : fileList) {
                list.add(new File(path.toString()));
            }
            List dirList = FileUtils.getDirectoryNames(directory, "*", "", true);
            for (Object path : dirList) {
                findFiles(new File(path.toString()), includes, excludes, list);
            }
        } catch (Exception e) {
            getLog().error("find file or directory error");
            e.printStackTrace();
        }
        return list;
    }

}
