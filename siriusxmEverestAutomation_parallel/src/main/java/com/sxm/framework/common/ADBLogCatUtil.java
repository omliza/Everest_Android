package com.sxm.framework.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;

public class ADBLogCatUtil {

    private String filepath = System.getProperty("user.dir") + File.separator
            + "src/test/resources";
    private static final String ADB_CMD = "adb pull /storage/emulated/legacy/Android/data/com.sirius/files";
    private static final String SPACE = " ";
    private String FILE_TEXT_EXT = ".log";
    private String temp = null;
    private StringBuilder log = new StringBuilder();
    private List<String> addresses = new ArrayList<String>();

    private boolean b = false;
    private File dir;
    private GenericExtFilter filter;

    public void searchADBLogs(String[] tokens) {
        dir = new File(filepath);
        filter = new GenericExtFilter(FILE_TEXT_EXT);
        readfromMobile(tokens[0]);
        readfromMobile(tokens[1]);
        closeStringBuilder();
        // deleteFile();
    }

    private void readfromMobile(String pattern) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e3) {
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }
        String command = String.format(ADB_CMD + SPACE + filepath);

        Process process = null;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e3) {
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }
        try {
            process = Runtime.getRuntime().exec(command);
            System.out.println("after adb command");
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        System.out.println("before listFile");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e3) {
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }
        listFile(filepath, FILE_TEXT_EXT);
        BufferedReader reader = null;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        Pattern patt = Pattern.compile(pattern);

        System.out.println("array size is " + addresses.size());
        for (String y : addresses) {

            System.out.println("file name is from array  " + y);

            try {
                System.out.println("temp" + y);
                reader = new BufferedReader(new FileReader(y));

            } catch (Exception e1) {

                e1.printStackTrace();
            }

            String line;
            try {
                ArrayList<String> ar = new ArrayList<String>();
                while ((line = reader.readLine()) != null) {

                    Matcher m = patt.matcher(line);

                    while (m.find()) {
                        // System.out.println("aa" + m.group(0));
                        int start = m.start(0);
                        int end = m.end(0);
                        System.out.println("Matched content       "
                                + line.substring(start, end));
                        /*
                         * String s = line.substring(start, end).replace(
                         * "BIIssue : Curr chunk time ", "");
                         */
                        String s = line.substring(start, end).replace(
                                "experience.json", "");

                        ar.add(s);

                        if (ar != null && !ar.isEmpty()) {

                        }
                    }
                }
                System.out.println("array size    " + ar.size());

                if (ar.size() >= 1) {
                    b = true;
                    reader.close();
                    break;
                }

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        Assert.assertTrue(b);
    }

    private void listFile(String folder, String ext) {

        if (dir.isDirectory() == false) {
            System.out.println("Directory does not exists : " + filepath);
            return;
        }

        // list out all the file name and filter by the extension
        String[] list = dir.list(filter);

        if (list.length == 0) {
            System.out.println("no files end with : " + ext);
            return;
        }

        for (String file : list) {

            temp = new StringBuffer(filepath).append(File.separator)
                    .append(file).toString();
            addresses.add(temp);
            System.out.println("file name is : " + temp);
        }

    }

    public void deletefilesfrommobile() {
        String command = String
                .format("adb shell rm /storage/emulated/legacy/Android/data/com.sirius/files/*.log");
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
        } catch (Exception e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        String path = filepath;
        File fileDelete;

        String[] list = dir.list(filter);
        for (String file : list) {
            String temp = new StringBuffer(filepath).append(File.separator)
                    .append(file).toString();
            fileDelete = new File(temp);
            boolean isdeleted = fileDelete.delete();
            System.out.println("file : " + temp + " is deleted : " + isdeleted);
        }

    }

    public void deleteFile() {

        // list out all the file name with .txt extension
        String[] list = dir.list(filter);

        if (list.length == 0)
            return;

        File fileDelete;

        for (String file : list) {
            String temp = new StringBuffer(filepath).append(File.separator)
                    .append(file).toString();
            fileDelete = new File(temp);
            boolean isdeleted = fileDelete.delete();
            System.out.println("file : " + temp + " is deleted : " + isdeleted);
        }
    }

    public class GenericExtFilter implements FilenameFilter {

        private String ext;

        public GenericExtFilter(String ext) {
            this.ext = ext;
        }

        public boolean accept(File dir, String name) {
            return (name.endsWith(ext));
        }
    }

    private void readDate(String text) {
        System.out.println(log.toString());
        if (log.toString().contains(text)) {

            // found.
        } else {
            Assert.assertTrue(false, text + "not displayed");
        }

    }

    private void closeStringBuilder() {
        log.setLength(0);
        System.out.println("log is set to zero" + log.toString());

    }
}