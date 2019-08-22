package com.jiyun.jsonjiexi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiyun.jsonjiexi.R;
import com.jiyun.jsonjiexi.beans.Da;

import java.util.List;

/**
 * Created by 刘建康 on 2019/6/26.
 */

public class My_Rec_adapter extends RecyclerView.Adapter<My_Rec_adapter.ViewHolder>{
    private Context context;
    private List<Da.DataBean.DatasBean> list;

    public My_Rec_adapter(Context context, List<Da.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.my_rec,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.title.setText(list.get(position).getTitle());
            holder.date.setText(list.get(position).getNiceDate());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a.onClick(v,position);
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
        }
    }
    public A a;
    public void setA(A a){
        this.a = a;
    }

    public interface A{
        void onClick(View view,int position);
    }
}
