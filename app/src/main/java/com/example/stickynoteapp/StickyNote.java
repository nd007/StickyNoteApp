package com.example.stickynoteapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class StickyNote {


    String getStick(Context context)
    {
        File file =new File(context.getFilesDir().getPath()+"/ntt.txt");
        StringBuilder text=new StringBuilder();

        try {
            BufferedReader br=new BufferedReader(new FileReader(file));
            String line=br.readLine();
            while (line!=null)
            {
                text.append(line);
                text.append("\n");

            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();

        }
        return text.toString();
    }



    void setStick(String texttobeSaved,Context context)
    {
        String text=texttobeSaved;
        FileOutputStream fos = null;
        try{
            fos=context.getApplicationContext().openFileOutput("ntt.txt",Context.MODE_PRIVATE);
            fos.write(text.getBytes());

        }
        catch (IOException e){
            e.printStackTrace();

        }finally {
            if(fos!=null)
            {
                try{
                    fos.close();;
                }
                catch (IOException e){
                    e.printStackTrace();

                }

            }
        }

    }

}
