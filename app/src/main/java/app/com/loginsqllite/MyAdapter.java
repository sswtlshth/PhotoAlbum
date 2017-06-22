package app.com.loginsqllite;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by GS-0902 on 31-05-2017.
 */

public class MyAdapter extends android.support.v7.widget.RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<CreateList> galleryList;
    private Context context;
    MyAdapter(Context context,ArrayList<CreateList> galleryList){
        this.context=context;
        this.galleryList=galleryList;
    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.img.setScaleType(ImageView.ScaleType.FIT_CENTER);
       // holder.img.setImageResource((galleryList.get(position).getImage_ID()));
        holder.img.setImageDrawable(Drawable.createFromPath(galleryList.get(position).getImage_title()));
        holder.title.setText(galleryList.get(position).getImage_title());
    }
    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.title);
            img=(ImageView) itemView.findViewById(R.id.img);
        }
    }
}
