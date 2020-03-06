package com.huangyuanlove.sunflower_java;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;


public class WriteLogToFile {

    public static void writePushMessageToFile(String message){

        File publicFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        try {

            File pushLogFile =  new File(publicFile,"crash_message.txt");
            if(!pushLogFile.exists()){
                pushLogFile.createNewFile();
            }
            Writer out =new FileWriter(pushLogFile,true);
            out.append(message);
            out.close();


        }
        catch (Exception e  ){
            e.printStackTrace();
        }



    }

}
