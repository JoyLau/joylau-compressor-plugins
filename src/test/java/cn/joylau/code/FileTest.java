package cn.joylau.code;

import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoyLau on 2017/10/16.
 * java
 * 2587038142@qq.com
 */
public class FileTest {
    public static void main(String[] args) {
//        try {
//            List<File> files = findFiles(new File("E:\\idea_dev\\website\\joylau-media\\target\\classes\\static\\js"),"*","",new ArrayList<>());
//            System.out.println(files.size());
//            for (File file : files) {
//                System.out.println(file.getPath());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            List<File> files2 = findFiles2(new File("E:\\idea_dev\\website\\joylau-media\\target\\classes\\static\\js"),"*","",new ArrayList<>());
            System.out.println(files2.size());
            for (File file : files2) {
                System.out.println(file.getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<File> findFiles(File directory, String includes, String excludes, List<File> list){
        try {
            List fileAndDir = FileUtils.getFileAndDirectoryNames(directory,includes,excludes,true,true,true,true);
            for (Object path : fileAndDir) {
                File file = new File(path.toString());
                if (file.isFile()) {
                    list.add(file);
                } else {
                    findFiles(file,includes,excludes,list);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<File> findFiles2(File directory, String includes, String excludes, List<File> list){
        try {
            List fileList = FileUtils.getFiles(directory,includes,excludes);
            for (Object path : fileList) {
                list.add(new File(path.toString()));
            }
            List dirList = FileUtils.getDirectoryNames(directory,"*","",true);
            System.out.println(dirList.size());
            for (Object path : dirList) {
                findFiles2(new File(path.toString()),includes,excludes,list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
