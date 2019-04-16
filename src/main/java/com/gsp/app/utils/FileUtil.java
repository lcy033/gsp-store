package com.gsp.app.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.comparator.SizeFileComparator;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 * @author: dongchen
 * @ClassName: FileUtil
 * @Description: 文件操作工具类
 * @date 2015年11月19日 下午11:53:39
 */
public class FileUtil {
    private static Pattern FilePattern = Pattern
            .compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]");

    /**
     * @auth:dongchen
     * @Title: getFile
     * @Description: 获取文件
     * @param @param path
     * @param @return 设定文件
     * @return File 返回类型
     * @throws
     */
    public static File getFile(String path) {
        return FileUtils.getFile(path);
    }

    /**
     * @auth:dongchen
     * @Title: getFullPath
     * @Description: 获取全路径
     * @param @param path
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String getFullPath(String path) {
        return FilenameUtils.getFullPath(path);
    }

    /**
     * @auth:dongchen
     * @Title: getName
     * @Description: 获取文件名
     * @param @param path
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String getName(String path) {
        return FilenameUtils.getName(path);
    }

    /**
     * @auth:dongchen
     * @Title: getExtension
     * @Description: 获取文件后缀
     * @param @param path
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String getExtension(String path) {
        return FilenameUtils.getExtension(path);
    }

    /**
     * @auth:dongchen
     * @Title: getBaseName
     * @Description: 获取文件名不带路径 (例如aa.txt,返回结果为aa)
     * @param @param path
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String getBaseName(String path) {
        return FilenameUtils.getBaseName(path);
    }

    /**
     * @auth:dongchen
     * @Title: mkdir
     * @Description: 创建文件夹
     * @param @param path
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean mkdirs(String path) {
        return getFile(path).mkdirs();
    }

    /**
     * @throws IOException
     * @auth:dongchen
     * @Title: cleanDirectory
     * @Description: 清空目录，但不删除目录
     * @param @param path 设定文件
     * @return void 返回类型
     * @throws
     */
    public static void cleanDirectory(String path) throws IOException {
        FileUtils.cleanDirectory(getFile(path));
    }

    /**
     * @auth:dongchen
     * @Title: deleteDirectory
     * @Description: 删除目录
     * @param @param path
     * @param @throws IOException 设定文件
     * @return void 返回类型
     * @throws
     */
    public static void deleteDirectory(String path) throws IOException {
        FileUtils.deleteDirectory(getFile(path));
    }

    /**
     * @auth:dongchen
     * @Title: isFile
     * @Description: 判断是否为文件
     * @param @param path
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean isFile(String path) {
        return getFile(path).isFile();
    }

    /**
     * @auth:dongchen
     * @Title: isDirectory
     * @Description: 判断是否为文件夹
     * @param @param path
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean isDirectory(String path) {
        return getFile(path).isDirectory();
    }

    /**
     * @auth:dongchen
     * @Title: exists
     * @Description:判断文件是否存在
     * @param @param path
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean isExists(String path) {
        return getFile(path).exists();
    }

    /**
     * @auth:dongchen
     * @Title: copyFile
     * @Description: 复制文件或者目录,复制前后文件完全一样
     * @param @param resFilePath
     * @param @param distFolder
     * @param @throws IOException 设定文件
     * @return void 返回类型
     * @throws
     */
    public static void copyFile(String resFilePath, String distFolder) throws IOException {
        File resFile = getFile(resFilePath);
        File distFile = getFile(distFolder);
        if (isDirectory(resFilePath)) {
            FileUtils.copyDirectoryToDirectory(resFile, distFile);
        } else if (isFile(resFilePath)) {
            FileUtils.copyFileToDirectory(resFile, distFile, true);
        }
    }

    public static boolean copyFiles(String resFilePath, String distFolder) throws IOException {
        File resFile = getFile(resFilePath);
        File distFile = getFile(distFolder);
        if (isDirectory(resFilePath)) {
            FileUtils.copyDirectoryToDirectory(resFile, distFile);
        } else if (isFile(resFilePath)) {
            FileUtils.copyFileToDirectory(resFile, distFile, true);
        }

        return true;
    }

    /**
     * @auth:dongchen
     * @Title: copyDirectory
     * @Description: 将一个目录内容拷贝到另一个目录
     * @param @param resFilePath
     * @param @param distFolder
     * @param @throws IOException 设定文件
     * @return void 返回类型
     * @throws
     */
    public static void copyDirectory(String resFilePath, String distFolder) throws IOException {
        File resFile = getFile(resFilePath);
        File distFile = getFile(distFolder);
        if (isDirectory(resFilePath)) {
            FileUtils.copyDirectory(resFile, distFile);
        } else if (isFile(resFilePath)) {
            FileUtils.copyFileToDirectory(resFile, distFile, true);
        }
    }

    /**
     * @auth:dongchen
     * @Title: deleteFile
     * @Description: 删除一个文件或者目录
     * @param @param targetPath
     * @param @throws IOException 设定文件
     * @return void 返回类型
     * @throws
     */
    public static void deleteFile(String targetPath) throws IOException {
        File targetFile = getFile(targetPath);
        if (isDirectory(targetPath)) {
            FileUtils.deleteDirectory(targetFile);
        } else if (isFile(targetPath)) {
            FileUtils.deleteQuietly(targetFile);
        }
    }

    /**
     * @auth:dongchen
     * @Title: moveFile
     * @Description: 移动文件或者目录,移动前后文件完全一样,如果目标文件夹不存在则创建
     * @param @param resFilePath
     * @param @param distFolder
     * @param @throws IOException 设定文件
     * @return void 返回类型
     * @throws
     */
    public static void moveFile(String resFilePath, String distFolder) throws IOException {
        File resFile = getFile(resFilePath);
        File distFile = getFile(distFolder);
        if (isDirectory(resFilePath)) {
            FileUtils.moveDirectoryToDirectory(resFile, distFile, true);
        } else if (isFile(resFilePath)) {
            FileUtils.moveFileToDirectory(resFile, distFile, true);
        }
    }

    /**
     * @auth:dongchen
     * @Title: renameFile
     * @Description: 重命名文件或文件夹
     * @param @param resFilePath
     * @param @param newFileName
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean renameFile(String resFilePath, String newFileName) {
        File resFile = getFile(resFilePath);
        File newFile = getFile(newFileName);
        return resFile.renameTo(newFile);
    }

    /**
     * @auth:dongchen
     * @Title: listsuffixFileFilterFilesName
     * @Description: 某个目录下的文件列表
     * @param @param folder 文件目录
     * @param @param suffix 文件的后缀名(.xml,.pdf)
     * @param @return 设定文件
     * @return String[] 返回类型
     * @throws
     */
    public static String[] listsuffixFileFilterFilesName(String path, String suffix) {
        IOFileFilter fileFilter1 = new SuffixFileFilter(suffix);
        IOFileFilter fileFilter2 = new NotFileFilter(DirectoryFileFilter.INSTANCE);
        FilenameFilter filenameFilter = new AndFileFilter(fileFilter1, fileFilter2);
        return getFile(path).list(filenameFilter);
    }

    /**
     * @auth:dongchen
     * @Title: listsuffixFileFilterFiles
     * @Description: 列出指定目录下指定后缀的所有文件
     * @param @param path
     * @param @param suffix
     * @param @return 设定文件
     * @return Collection<File> 返回类型
     * @throws
     */
    public static Collection<File> listsuffixFileFilterFiles(String path, String suffix) {
        return FileUtils.listFiles(getFile(path), FileFilterUtils.suffixFileFilter(suffix, IOCase.INSENSITIVE),
                DirectoryFileFilter.INSTANCE);
    }

    /**
     * @auth:dongchen
     * @Title: listNameFileFifterFiles
     * @Description: 通过名字过滤指定路径下的文件
     * @param @param path
     * @param @param acceptedNames
     * @param @return 设定文件
     * @return String[] 返回类型
     * @throws
     */
    public static String[] listNameFileFifterFiles(String path, String[] acceptedNames) {
        return getFile(path).list(new NameFileFilter(acceptedNames, IOCase.SENSITIVE));
    }

    /**
     * @auth:dongchen
     * @Title: listNameFileFifterFiles
     * @Description: 通过指定名字过滤指定路径下的文件
     * @param @param path
     * @param @param acceptedNames
     * @param @return 设定文件
     * @return Collection<File> 返回类型
     * @throws
     */
    public static Collection<File> listNameFileFifterFiles(String path, String acceptedNames) {
        return FileUtils.listFiles(getFile(path), FileFilterUtils.nameFileFilter(acceptedNames, IOCase.SENSITIVE),
                DirectoryFileFilter.INSTANCE);
    }

    /**
     * @auth:dongchen
     * @Title: listWildcardFileFilterFilesNames
     * @Description: 通过指定关键字通配过滤指定路径下的文件
     * @param @param path
     * @param @param wild
     * @param @return 设定文件
     * @return String[] 返回类型
     * @throws
     */
    public static String[] listWildcardFileFilterFilesNames(String path, String wild) {
        return getFile(path).list(
                new WildcardFileFilter(new StringBuffer().append("*").append(wild).append("*").toString()));
    }

    /**
     * @auth:dongchen
     * @Title: listWildcardFileFilterFiles
     * @Description: 通过指定关键字通配过滤指定路径下的文件
     * @param @param path
     * @param @param wild
     * @param @return 设定文件
     * @return Collection<File> 返回类型
     * @throws
     */
    public static Collection<File> listWildcardFileFilterFiles(String path, String wild) {
        return FileUtils.listFiles(
                getFile(path),
                FileFilterUtils.and(new WildcardFileFilter(new StringBuffer().append("*").append(wild).append("*")
                        .toString())), DirectoryFileFilter.INSTANCE);
    }

    /**
     * @auth:dongchen
     * @Title: listPrefixFileFilterFilesNames
     * @Description: 通过前缀名过滤文件
     * @param @param path
     * @param @param prefix
     * @param @return 设定文件
     * @return String[] 返回类型
     * @throws
     */
    public static String[] listPrefixFileFilterFilesNames(String path, String prefix) {
        return getFile(path).list(new PrefixFileFilter(prefix));
    }

    /**
     * @auth:dongchen
     * @Title: listPrefixFileFilterFiles
     * @Description: 通过前缀过滤文件
     * @param @param path
     * @param @param prefix
     * @param @return 设定文件
     * @return Collection<File> 返回类型
     * @throws
     */
    public static Collection<File> listPrefixFileFilterFiles(String path, String prefix) {
        return FileUtils.listFiles(getFile(path), FileFilterUtils.prefixFileFilter(prefix, IOCase.SENSITIVE),
                DirectoryFileFilter.INSTANCE);
    }

    /**
     * @auth:dongchen
     * @Title: nameFileComparator
     * @Description: 通过文件名排序
     * @param @param path
     * @param @return 设定文件
     * @return File[] 返回类型
     * @throws
     */
    public static File[] nameFileComparator(String path) {
        if (isDirectory(path)) {
            NameFileComparator comparator = new NameFileComparator(IOCase.SENSITIVE);
            return comparator.sort(getFile(path).listFiles());
        } else {
            return null;
        }
    }

    /**
     * @auth:dongchen
     * @Title: sizeFileComparator
     * @Description: 通过文件大小排序
     * @param @param path
     * @param @return 设定文件
     * @return File[] 返回类型
     * @throws
     */
    public static File[] sizeFileComparator(String path) {
        if (isDirectory(path)) {
            SizeFileComparator sizeComparator = new SizeFileComparator(true);
            return sizeComparator.sort(getFile(path).listFiles());
        } else {
            return null;
        }
    }

    /**
     * @auth:dongchen
     * @Title: lastModifiedFileComparator
     * @Description: 通过修改时间进行排序
     * @param @param path
     * @param @return 设定文件
     * @return File[] 返回类型
     * @throws
     */
    public static File[] lastModifiedFileComparator(String path) {
        if (isDirectory(path)) {
            LastModifiedFileComparator lastModified = new LastModifiedFileComparator();
            return lastModified.sort(getFile(path).listFiles());
        } else {
            return null;
        }
    }

    /**
     * @auth:dongchen
     * @Title: getSize
     * @Description: 获取文件或者目录大小
     * @param @param path
     * @param @return 设定文件
     * @return long 返回类型
     * @throws
     */
    public static long getSize(String path) {
        File file = getFile(path);
        if (isDirectory(path)) {
            return FileUtils.sizeOfDirectory(file);
        } else {
            return FileUtils.sizeOf(file);
        }
    }

    /**
     * @auth:dongchen
     * @Title: filenameFilter
     * @Description: 文件名过滤特殊字符
     * @param @param str
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String filenameFilter(String str) {
        return str == null ? null : FilePattern.matcher(str).replaceAll("");
    }

}
