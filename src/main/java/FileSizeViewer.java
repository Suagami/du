import javafx.util.Pair;
import java.io.*;
import java.util.*;

import static java.lang.Math.round;

class FileSizeViewer {

    private final List<String> fileNames;
    Long sumSize = 0L;
    List<Pair<String, Long>> table = new ArrayList<Pair<String, Long>>();

    FileSizeViewer(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    int size() {
            for (String name: this.fileNames) {
                File tempFile = new File(name);
                long tempSize;
                if (!tempFile.exists()) {
                    System.err.println("This file or directory does not exist");
                    return 1;
                }
                if (tempFile.isDirectory()) {
                    tempSize = folderSize(tempFile);
                } else tempSize = tempFile.length();
                Pair<String, Long> newPair = new Pair<String, Long>(name, tempSize);
                this.table.add(newPair);
            }
            return 0;
    }

    private long folderSize(File directory) {
        long length = 0;
        try {
            for (File file : directory.listFiles()) {
                if (file.isFile())
                    length += file.length();
                else
                    length += folderSize(file);
            }
        }
        catch (NullPointerException e) {}
        return length;
    }


    void getSum(){
        for(Pair size: this.table){
            Long tempSize = (Long)size.getValue();
            this.sumSize += tempSize;
        }
    }


    private String humanisation(Long size, int base) {
        String t = "B";
        double rSize = (double) size;
        if (rSize > base) {
            rSize /= base;
            t = "KB";
            if (rSize > base) {
                rSize = round(rSize);
                rSize /= base;
                t = "MB";
                if (rSize > base) {
                    rSize = round(rSize);
                    rSize /= base;
                    t = "GB";
                }
            }
        }
        String strSize = String.format("%.2f", rSize);
        return strSize + t;
    }


    void output(int base, boolean isSum, boolean isHum) {
        if (isSum) {
            if (isHum) {
                System.out.println("Total size: " + humanisation(this.sumSize, base));
            } else {
                System.out.println("Total size: " + this.sumSize + "B");
            }
        }
        else {
            if (isHum) {
                for (Pair<String, Long> pair : this.table) {
                    System.out.println(pair.getKey() + ": " + humanisation(pair.getValue(), base));
                }
            } else {
                for (Pair<String, Long> pair : this.table) {
                    System.out.println(pair.getKey() + ": " + pair.getValue() + "B");
                }
            }
        }

    }

}
