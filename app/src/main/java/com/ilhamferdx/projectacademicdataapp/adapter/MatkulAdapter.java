package com.ilhamferdx.projectacademicdataapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ilhamferdx.projectacademicdataapp.R;
import com.ilhamferdx.projectacademicdataapp.database.entity.Matkul;

import java.util.List;

public class MatkulAdapter extends RecyclerView.Adapter<MatkulAdapter.ViewAdapter>{
    private List<Matkul> list;
    private Context context;
    private MatkulAdapter.Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(MatkulAdapter.Dialog dialog) {
        this.dialog = dialog;
    }

    public MatkulAdapter(Context context, List<Matkul> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MatkulAdapter.ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_matkul, parent, false);
        return new MatkulAdapter.ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatkulAdapter.ViewAdapter holder, int position) {
//        holder.kode_matkul.setText(list.get(position).kode_matkul);
        holder.nama_matkul.setText(list.get(position).nama_matkul);
        holder.sks.setText(list.get(position).sks);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView kode_matkul, nama_matkul, sks; // becarefull in there bro

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            kode_matkul = itemView.findViewById(R.id.kode_matkul);
            nama_matkul = itemView.findViewById(R.id.nama_matkul);
            sks         = itemView.findViewById(R.id.sks);
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
