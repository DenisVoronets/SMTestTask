package dev.den.mycountdowntimer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<MyTime> myTimesList;

    public MyRecyclerViewAdapter(List<MyTime> myTimesList) {
        this.myTimesList = myTimesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyTime myTime = myTimesList.get(position);
        holder.textViewList.setText(myTime.getCurrentTime());
    }

    @Override
    public int getItemCount() {
        return myTimesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewList = itemView.findViewById(R.id.textView_list);
        }

    }
}
