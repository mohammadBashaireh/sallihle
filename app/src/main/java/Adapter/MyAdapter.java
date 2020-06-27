package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sallihle.R;

import java.util.List;

import Model.Listitem;
import com.example.sallihle.request_details;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<Listitem> listitems;

    public MyAdapter(Context context, List listitem) {
        this.context = context;
        this.listitems = listitem;

    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.provider_list,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

        Listitem item = listitems.get(position);
        holder.clientname.setText(item.getName());
        holder.servicetype.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView clientname;
        private TextView servicetype;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            clientname = itemView.findViewById(R.id.request_client_name);
            servicetype = itemView.findViewById(R.id.request_service_type);

        }

        @Override
        public void onClick(View v) {
            int position =getAdapterPosition();
            Listitem item=listitems.get(position);
            Intent myint = new Intent(context ,request_details.class);
            myint.putExtra("name",item.getName());
            myint.putExtra("description",item.getDescription());
            context.startActivity(myint);


        }
    }
}
