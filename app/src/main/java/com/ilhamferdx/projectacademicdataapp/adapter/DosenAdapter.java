package com.ilhamferdx.projectacademicdataapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ilhamferdx.projectacademicdataapp.R;
import com.ilhamferdx.projectacademicdataapp.database.entity.Dosen;

import java.util.List;

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.ViewAdapter> {

    private List<Dosen> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public DosenAdapter(Context context, List<Dosen> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_dosen, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.nama_dosen.setText(list.get(position).nama_dosen);
        holder.nip.setText(list.get(position).nip);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView nama_dosen, nip; // becarefull in there bro

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            nama_dosen = itemView.findViewById(R.id.full_namedsn);
            nip        = itemView.findViewById(R.id.nip);
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
