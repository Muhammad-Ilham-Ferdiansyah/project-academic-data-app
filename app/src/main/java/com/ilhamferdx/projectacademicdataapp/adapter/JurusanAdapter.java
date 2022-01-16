package com.ilhamferdx.projectacademicdataapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ilhamferdx.projectacademicdataapp.R;
import com.ilhamferdx.projectacademicdataapp.database.entity.Jurusan;

import java.util.List;

public class JurusanAdapter extends RecyclerView.Adapter<JurusanAdapter.ViewAdapter>{
    private List<Jurusan> list;
    private Context context;
    private JurusanAdapter.Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(JurusanAdapter.Dialog dialog) {
        this.dialog = dialog;
    }

    public JurusanAdapter(Context context, List<Jurusan> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public JurusanAdapter.ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_jurusan, parent, false);
        return new JurusanAdapter.ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JurusanAdapter.ViewAdapter holder, int position) {
        holder.nama_jurusan.setText(list.get(position).nama_jurusan);
        holder.jenjang.setText(list.get(position).jenjang);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView nama_jurusan, jenjang; // becarefull in there bro

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            nama_jurusan = itemView.findViewById(R.id.namajrsn);
            jenjang      = itemView.findViewById(R.id.jenjang);
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

