package com.kl.io;

import java.io.File;

/**
 * Created by mac on 2020-09-21.
 */
public class FileDemo {



    public static void main(String args[]) throws Exception {
        File file=new File("/Users/mac/Desktop/bunny");
        System.out.println(""+file.exists());
    }
}
