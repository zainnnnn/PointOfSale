package com.example.caspos.varient;

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

public class VariantAdaptor extends RecyclerView.Adapter<VariantAdaptor.ViewHolder> implements Filterable {
    Context context;
    List<VariantModelClass> variantModellist, listFull;

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void OnItem(int position);
    }

    public VariantAdaptor(Context context, List<VariantModelClass> variantModellist) {
        this.context = context;
        this.variantModellist = variantModellist;
        listFull = new ArrayList<>(variantModellist);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView;
        rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_variant_view, null);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VariantModelClass modelClass = variantModellist.get(position);
        holder.Varianttitle.setText(modelClass.getVariant_title());
        holder.Variantdescription.setText(modelClass.getVariant_description());

    }

    @Override
    public int getItemCount() {
        return variantModellist.size();
    }

    @Override
    public Filter getFilter() {
        return varientFilter;
    }

    private Filter varientFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<VariantModelClass> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (VariantModelClass item : listFull) {
                    if (item.getVariant_title().toLowerCase().contains(filterPattern)) {
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
            variantModellist.clear();
            variantModellist.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

    };

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        TextView Varianttitle, Variantdescription;
        int position, status;
        String VariantTitle, VariantDescription, VariantID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Varianttitle = itemView.findViewById(R.id.tv_rowForVariantTitle);
            Variantdescription = itemView.findViewById(R.id.tv_rowForVariantDescription);
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
            VariantTitle = variantModellist.get(position).getVariant_title();
            VariantDescription = variantModellist.get(position).getVariant_description();
            status = variantModellist.get(position).getVariant_Status();
            VariantID = variantModellist.get(position).getVariant_ID();

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
                        UpdateVariantDialog dialog = new UpdateVariantDialog(context, VariantTitle, VariantDescription, VariantID);
                        dialog.show();
                        Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        DeleteVariantDialog dialog1 = new DeleteVariantDialog(context, VariantTitle, position, VariantID);
                        dialog1.show();
                        removeItem(position);
                        //Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        };
    }
    public void removeItem(int position){
        variantModellist.remove(position);
        // notifyItemRemoved(position);
    }
}
