package app.com.loginsqllite;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by GS-0902 on 05-06-2017.
 */

public class FilesHelper{
    ArrayList<String> f = new ArrayList<String>();// list of file paths

    //return all the files in directory
    public File[] getFromSdcard(Context context)
    {
        File[] listFile;
        File storageDir1=context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        Log.d("The context file:: ",storageDir1.getAbsolutePath());
        listFile = storageDir1.listFiles();
       /* CreateList tempList=new CreateList();
        for (int i = 0; i < listFile.length; i++)
        {
          //  Log.d("The file:: "+i,listFile[i].getAbsolutePath());
            tempList.setImage_title(listFile[i].getAbsolutePath());
            fileList.add(tempList);
            //f.add(listFile[i].getAbsolutePath());
           // Log.d("The file " +i,fileList.get(i).getImage_title());
        }
        for(int i=0;i< fileList.size();i++){
            Log.d("The final list::"+i,fileList.get(i).getImage_title());
        }
        return fileList;*/
        return listFile;
    }
}
