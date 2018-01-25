package cs371m.lifepointcounter;

import android.support.v7.widget.RecyclerView;

//package info.androidhive.recyclerview;

import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import java.util.List;

/**
 * Created by Eddy on 5/4/2016.
 */
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder>{

    private List<Entry> entryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, lifeChange, lifeNow, time;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            lifeChange = (TextView) view.findViewById(R.id.lifeChange);
            lifeNow = (TextView) view.findViewById(R.id.lifeNow);
            time = (TextView) view.findViewById(R.id.time);
        }
    }

    public RecordAdapter(List<Entry> entryList) {
        this.entryList = entryList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.log_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Entry entry = entryList.get(position);
        holder.name.setText(entry.getName());
        holder.lifeChange.setText(entry.getLifeChange());
        holder.lifeNow.setText(entry.getLifeNow());
    }

    @Override
    public int getItemCount(){
        return entryList.size();
    }

}
