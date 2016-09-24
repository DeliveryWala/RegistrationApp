package com.example.arif.registrationapp;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Arif on 21/09/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private String[] titles={"Item 1","Item 2","Item 3","Item 4","Item 5","Item 6","Item 7","Item 8","Item 9","Item 10"};
    private String[] details={"Item 1 details","Item 2 details","Item 3 details","Item 4 details","Item 5 details","Item 6 details","Item 7 details","Item 8 details","Item 9 details","Item 10 details"};
   private int[] images={R.drawable.cinthol,R.drawable.denimp,R.drawable.dettol,R.drawable.dovep,R.drawable.dovew,R.drawable.fiamam,R.drawable.fiamap,R.drawable.lifel,R.drawable.lirill,R.drawable.pearsgreen};

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view,viewGroup,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(images[position]);
        holder.title.setText(titles[position]);
        holder.detail.setText(details[position]);

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
public int currentItem;
        public ImageView imageView;
        public TextView title,detail;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.cardImage);
            title= (TextView) itemView.findViewById(R.id.cardTitle);
            detail= (TextView) itemView.findViewById(R.id.cardDetail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    Snackbar.make(v,"Clicked "+position,Snackbar.LENGTH_LONG).setAction("Action",null).show();
                }
            });
        }
    }
}