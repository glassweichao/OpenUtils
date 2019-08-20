package com.chaow.openutils.basic;


import java.io.File;

/**
 * Creator Char 2019/8/19
 */
public final class FileUtils {

    private FileUtils() {

    }

    /**
     * 文件是否存在
     *
     * @param filePath 文件路径
     * @return {@code true}: 存在 <br>{@code false}: 不存在
     */
    public static boolean isFileExists(final String filePath) {
        return isFileExists(getFileByPath(filePath));
    }

    /**
     * 文件是否存在
     *
     * @param file 文件
     * @return {@code true}: 存在 <br>{@code false}: 不存在
     */
    public static boolean isFileExists(final File file) {
        return file != null && file.exists();
    }

    /**
     * 根据路径获取文件
     *
     * @param filePath 文件路径
     * @return 找到的文件，或者为null
     */
    public static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    /**
     * 文件重命名
     *
     * @param filePath 文件路径
     * @param newName  新名字
     * @return {@code true}: 重命名成功 <br>{@code false}: 重命名失败
     */
    public static boolean rename(final String filePath, final String newName) {
        return rename(getFileByPath(filePath), newName);
    }

    /**
     * 文件重命名
     *
     * @param file    文件
     * @param newName 新名字
     * @return {@code true}: 重命名成功 <br>{@code false}: 重命名失败
     */
    public static boolean rename(final File file, final String newName) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return false;
        }
        if (isSpace(newName)) {
            return false;
        }
        if (newName.equals(file.getName())) {
            return true;
        }
        File newFile = new File(file.getParent() + File.separator + newName);
        return !newFile.exists()
                && file.renameTo(newFile);
    }

    /**
     * 是否是目录
     *
     * @param dirPath 目录路径
     * @return {@code true}: 是 <br>{@code false}: 不是
     */
    public static boolean isDir(final String dirPath) {
        return isDir(getFileByPath(dirPath));
    }

    /**
     * 是否是目录
     *
     * @param file 文件名
     * @return {@code true}: 是 <br>{@code false}: 不是
     */
    public static boolean isDir(final File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    /**
     * 是否是文件
     *
     * @param filePath 文件路径
     * @return {@code true}: 是 <br>{@code false}: 不是
     */
    public static boolean isFile(final String filePath) {
        return isFile(getFileByPath(filePath));
    }

    /**
     * 是否是文件
     *
     * @param file 文件路径
     * @return {@code true}: 是 <br>{@code false}: 不是
     */
    public static boolean isFile(final File file) {
        return file != null && file.exists() && file.isFile();
    }


    /**
     * 是否有空格
     *
     * @param s 待检字符串
     * @return true - 有空格
     */
    private static boolean isSpace(final String s) {
        if (StringUtils.isEmpty(s)) {
            return true;
        }
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
