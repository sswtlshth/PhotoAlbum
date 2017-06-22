package app.com.loginsqllite;

import android.util.Log;

/**
 * Created by GS-0902 on 31-05-2017.
 */

public class CreateList {
    private String image_title;
    private Integer image_id;

    public String getImage_title() {
        return image_title;
    }

    public void setImage_title(String android_version_name) {
        Log.d("The fileHelper class  ",android_version_name);
        this.image_title = android_version_name;
        Log.d("file assifnged name  ",this.image_title);
    }

    public Integer getImage_ID() {
        return image_id;
    }

    public void setImage_ID(Integer android_image_url) {
        this.image_id = android_image_url;
    }

}
