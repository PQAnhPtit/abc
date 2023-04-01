package com.example.appbc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class CongViecAdapter extends RecyclerView.Adapter<CongViecAdapter.CongViecViewHolder>{
    private Context context;

    private List<CongViec> listBackUp;
    private List<CongViec> mList;
    private CongViecItemListener mCongViecItem;

    public CongViecAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
        listBackUp = new ArrayList<>();
    }

    public List<CongViec> getBackup(){
        return listBackUp;
    }

    public void setmList(List<CongViec> mList){
        this.mList = mList;
    }

    public void filterList(List<CongViec> filterlist){
        mList = filterlist;
        notifyDataSetChanged();
    }

    public void setClickListener(CongViecItemListener mCongViecItem){
        this.mCongViecItem = mCongViecItem;
    }

    @NonNull
    @Override
    public CongViecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new CongViecViewHolder(view);
    }

    public CongViec getItem(int position){
        return mList.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull CongViecViewHolder holder, int position) {
        CongViec CongViec = mList.get(position);
        if(CongViec == null)
            return;
        holder.img.setImageResource(CongViec.getImg());
        holder.tvName.setText(CongViec.getName());
        holder.tvNgay.setText(CongViec.getNgay());
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban chac chan muon xoa " + CongViec.getName() + " nay khong");
                builder.setIcon(R.drawable.remove);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listBackUp.remove(position);
                        mList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void add(CongViec c){
        listBackUp.add(c);
        mList.add(c);
        notifyDataSetChanged();
    }

    public void update(int position, CongViec congViec){
        listBackUp.set(position, congViec);
        mList.set(position, congViec);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mList != null)
            return mList.size();
        return 0;
    }


    public class CongViecViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView tvName, tvNgay;
        private Button btRemove;

        public CongViecViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvName = itemView.findViewById(R.id.txtNv);
            tvNgay = itemView.findViewById(R.id.txtDate);
            btRemove= itemView.findViewById(R.id.btRemove);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mCongViecItem != null){
                mCongViecItem.OnItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface CongViecItemListener{
        void OnItemClick(View view, int position);
    }
}

