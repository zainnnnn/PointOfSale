package com.example.caspos.flavour;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.caspos.R;

import java.util.ArrayList;
import java.util.List;

public class FlavourAdapter extends RecyclerView.Adapter<FlavourAdapter.ViewHolder>implements Filterable {
    Context context;
    List<FlavourModelClass> flavourAdaptersList,listFull;

    public OnItemClickListener onItemClickListener;

    public FlavourAdapter(Context context, List<FlavourModelClass> flavourAdaptersList) {
        this.context = context;
        this.flavourAdaptersList = flavourAdaptersList;
        listFull = new ArrayList<>(flavourAdaptersList);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void OnItem(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView;
        rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_view_flavour, null);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FlavourModelClass modelClass = flavourAdaptersList.get(position);
        holder.tvflavourTitle.setText(modelClass.getFlavour_title());
        holder.tvFlavourDescription.setText(modelClass.getFlavour_description());

    }

    @Override
    public int getItemCount() {
        return flavourAdaptersList.size();
    }

    @Override
    public Filter getFilter() {
        return flavourFilter;
    }

    private Filter flavourFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<FlavourModelClass> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (FlavourModelClass item : listFull) {
                    if (item.getFlavour_title().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            flavourAdaptersList.clear();
            flavourAdaptersList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

    };

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        TextView tvflavourTitle, tvFlavourDescription;
        int position, status;
        String FlavoureTitle, FlavourDescription, FlavourID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvflavourTitle = itemView.findViewById(R.id.tv_rowForFlavourTitle);
            tvFlavourDescription = itemView.findViewById(R.id.tv_rowForFlavourDescription);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position >= 0) {
                            onItemClickListener.OnItem(position);
                        }
                    }
                }
            });
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            return false;
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            position = getAdapterPosition();
            FlavoureTitle = flavourAdaptersList.get(position).getFlavour_title();
            FlavourDescription = flavourAdaptersList.get(position).getFlavour_description();
            status = flavourAdaptersList.get(position).getFlavour_Status();
            FlavourID = flavourAdaptersList.get(position).getFlavour_ID();

            MenuItem update = contextMenu.add(contextMenu.NONE, 1, 1, "Update");
            MenuItem delete = contextMenu.add(contextMenu.NONE, 2, 2, "Delete");

            update.setOnMenuItemClickListener(showMenu);
            delete.setOnMenuItemClickListener(showMenu);


        }

        private final MenuItem.OnMenuItemClickListener showMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case 1:
                        UpdateFlavourDialog dialog = new UpdateFlavourDialog(context, FlavoureTitle, FlavourDescription, FlavourID);
                        dialog.show();
                      //  Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        DeleteFlavourDialog dialog1=new DeleteFlavourDialog(context,FlavoureTitle,position,FlavourID);
                        dialog1.show();
                        removeItem(position);
                      //  Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        };
    }
    public void removeItem(int position){
        flavourAdaptersList.remove(position);
        // notifyItemRemoved(position);
    }
}
