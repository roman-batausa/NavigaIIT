package com.example.navigaiit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyHolder> implements Filterable {

    Context context;
    ArrayList<SearchModel> arrayList;
    LayoutInflater layoutInflater;
    SelectedRoom selectedRoom;
    ArrayList<SearchModel> getArrayListFiltered;

    public RoomAdapter(Context context, ArrayList<SearchModel> arrayList, SelectedRoom selectedRoom) {
        this.context = context;
        this.arrayList = arrayList;
        this.getArrayListFiltered = arrayList;
        layoutInflater = LayoutInflater.from(context);
        this.selectedRoom = selectedRoom;
    }

    @NonNull
    @Override
    public RoomAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_file, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.MyHolder holder, int position) {
        holder.room.setText(arrayList.get(position).getRoom());
        holder.floor.setText(arrayList.get(position).getFloor());
        holder.building.setText(arrayList.get(position).getBuilding());
        holder.room_descriptions.setText(arrayList.get(position).getRoom_descriptions());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint == null | constraint.length() == 0) {
                    filterResults.count = getArrayListFiltered.size();
                    filterResults.values = getArrayListFiltered;
                }
                else {
                    String searchChar = constraint.toString().toLowerCase();
                    ArrayList<SearchModel> resultData = new ArrayList<>();

                    for(SearchModel searchModel : getArrayListFiltered) {
                        if(searchModel.getRoom().toLowerCase().contains(searchChar) || searchModel.getBuilding().toLowerCase().contains(searchChar) || searchModel.getFloor().toLowerCase().contains(searchChar) || searchModel.getRoom_descriptions().toLowerCase().contains(searchChar)) {
                            resultData.add(searchModel);
                        }
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arrayList = (ArrayList<SearchModel>) results.values;
                notifyDataSetChanged();
            }
        };

        return filter;
    }

    public interface SelectedRoom {
        void selectedRoom(SearchModel searchModel);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView room, floor, building, room_descriptions;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            room = itemView.findViewById(R.id.txt3);
            floor = itemView.findViewById(R.id.txt2);
            building = itemView.findViewById(R.id.txt1);
            room_descriptions = itemView.findViewById(R.id.txt4);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedRoom.selectedRoom(arrayList.get(getAdapterPosition()));
                }
            });
        }
    }
}
