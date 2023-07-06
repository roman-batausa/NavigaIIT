package com.example.navigaiit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {

    private ArrayList<BookmarkModel> bookmarkModel;
    private onItemClickListener listener;

    public interface onItemClickListener {
        void onItemClicked(int position);
        void onDeleteClicked(int position);
    }
    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public BookmarkAdapter(ArrayList<BookmarkModel> bookmarkArrayList) {
        bookmarkModel = bookmarkArrayList;
    }
    public BookmarkModel getItemAtPosition(int position) {
        BookmarkModel mbookmarkModel = this.bookmarkModel.get(position);
        return mbookmarkModel;
    }
    public int getIndex(String arr[], int position) {
        BookmarkModel nbookmarkModel = this.bookmarkModel.get(position);
        int index = -1;
        for(int i = 0; i < arr.length; i++) {
            if(!(arr[i] == nbookmarkModel.getRoom()) && !(arr[i] == nbookmarkModel.getFloor())) {
                index++;
            }
            else {
                break;
            }
        }
        return index;
    }

    @NonNull
    @Override
    public BookmarkAdapter.BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_item, parent, false);
        return (new BookmarkViewHolder(view, listener));
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkAdapter.BookmarkViewHolder holder, int position) {
        BookmarkModel mbookmarkModel = bookmarkModel.get(position);

        holder.building.setText(mbookmarkModel.getBuilding());
        holder.floor.setText(mbookmarkModel.getFloor());
        holder.room.setText(mbookmarkModel.getRoom());
        holder.notes.setText(mbookmarkModel.getNotes());
    }

    @Override
    public int getItemCount() {
        return bookmarkModel.size();
    }

    public static class BookmarkViewHolder extends RecyclerView.ViewHolder {

        public TextView building, floor, room, notes;
        ImageView delete_img;

        public BookmarkViewHolder(View view, onItemClickListener listener) {
            super(view);
            building = view.findViewById(R.id.bm_building);
            floor = view.findViewById(R.id.bm_floor);
            room = view.findViewById(R.id.bm_room);
            notes = view.findViewById(R.id.bm_notes);
            delete_img = view.findViewById(R.id.delete_bookmark_img);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClicked(position);
                        }
                    }

                }
            });
            delete_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClicked(position);
                        }
                    }
                }
            });


        }

    }

}
