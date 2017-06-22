package app.com.loginsqllite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class MyRecyclerView extends AppCompatActivity {

  /*  private final String image_titles[] = {
            "Img1",
            "Img2",
            "Img3",
            "Img4",
            "Img5",
            "Img6",
            "Img7",
            "Img8",
            "Img9",
            "Img10",
            "Img11",
            "Img12"
    };

    private final Integer image_ids[] = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
            R.drawable.img11,
            R.drawable.img12
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        android.support.v7.widget.RecyclerView recyclerView=(android.support.v7.widget.RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);
        android.support.v7.widget.RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<CreateList> createLists=prepareData();
        for(int i=0;i<createLists.size();i++) {
            Log.d("The final list" + i, createLists.get(i).getImage_title());
        }
        MyAdapter adapter=new MyAdapter(getApplicationContext(),createLists);
        recyclerView.setAdapter(adapter);
    }
    private ArrayList<CreateList> prepareData(){

        ArrayList<CreateList> theImages = new ArrayList<>();
      /*  for(int i = 0; i< image_titles.length; i++){
            CreateList createList = new CreateList();
            createList.setImage_title(image_titles[i]);
            createList.setImage_ID(image_ids[i]);
            theimage.add(createList);
        }*/
        FilesHelper fileData=new FilesHelper();
        File[] listFile=fileData.getFromSdcard(getApplicationContext());
        //CreateList createList=new CreateList();
        for(int i=0;i<listFile.length;i++){
            CreateList createList=new CreateList();
            createList.setImage_title(listFile[i].getAbsolutePath());
            theImages.add(createList);
            Log.d("The file::"+i,listFile[i].getAbsolutePath());
        }
        //theimage=fileData.getFromSdcard(getApplicationContext());

        return theImages;
    }
}

