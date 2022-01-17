package com.ilhamferdx.projectacademicdataapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ilhamferdx.projectacademicdataapp.R;
import com.ilhamferdx.projectacademicdataapp.database.entity.Mahasiswa;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewAdapter>{
    private List<Mahasiswa> list;
    private Context context;
    private MahasiswaAdapter.Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(MahasiswaAdapter.Dialog dialog) {
        this.dialog = dialog;
    }

    public MahasiswaAdapter(Context context, List<Mahasiswa> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MahasiswaAdapter.ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mahasiswa, parent, false);
        return new MahasiswaAdapter.ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaAdapter.ViewAdapter holder, int position) {
        holder.nama_mahasiswa.setText(list.get(position).nama_mahasiswa);
        holder.npm.setText(String.valueOf(list.get(position).npm));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView nama_mahasiswa, npm; // becarefull in there bro

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            nama_mahasiswa = itemView.findViewById(R.id.nama_mhs);
            npm            = itemView.findViewById(R.id.npm);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
