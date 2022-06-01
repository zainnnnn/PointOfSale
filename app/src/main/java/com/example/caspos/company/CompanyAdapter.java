package com.example.caspos.company;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caspos.R;

import java.util.ArrayList;
import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder> implements Filterable {

    Context context;
    List<CompanyModel> companyModelList, listFull;

    public CompanyAdapter(Context context, List<CompanyModel> companyModelList) {
        this.context = context;
        this.companyModelList = companyModelList;
        listFull = new ArrayList<>(companyModelList);
    }

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_company, null);
        return new CompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
        CompanyModel companyModel = companyModelList.get(position);
        holder.tv_companyTitle.setText(companyModel.getCompany_Title());
        holder.tv_companyDescription.setText(companyModel.getCompany_Description());
    }

    @Override
    public int getItemCount() {
        return companyModelList.size();
    }

    @Override
    public Filter getFilter() {
        return companyFilter;
    }

    private Filter companyFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CompanyModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CompanyModel item : listFull) {
                    if (item.getCompany_Title().toLowerCase().contains(filterPattern)) {
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
            companyModelList.clear();
            companyModelList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

    };

    //////////////////////////////   View Holder Class \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public class CompanyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        int position;
        TextView tv_companyTitle, tv_companyDescription;
        String companyTitle, companyDescription;
        String company_ID;

        public CompanyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_companyTitle = itemView.findViewById(R.id.tv_TitleForCompanyRow);
            tv_companyDescription = itemView.findViewById(R.id.tv_descriptionForCompanyRow);

            itemView.setOnCreateContextMenuListener(this);
        }


        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

             position = getAdapterPosition();
            companyTitle = companyModelList.get(position).getCompany_Title();
            companyDescription = companyModelList.get(position).getCompany_Description();
            company_ID = companyModelList.get(position).getCompany_ID();

            Log.d("Tag", " ID FOR company " + company_ID);
            MenuItem update = contextMenu.add(contextMenu.NONE, 1, 1, "Update");
            MenuItem delete = contextMenu.add(contextMenu.NONE, 2, 2, "Delete");

            update.setOnMenuItemClickListener(showmenu);
            delete.setOnMenuItemClickListener(showmenu);
        }

        public final MenuItem.OnMenuItemClickListener showmenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case 1:
                        DialogUpdateCompany dialogUpdateCompany = new DialogUpdateCompany(context, companyTitle, companyDescription, company_ID);
                        dialogUpdateCompany.show();
                        break;
                    case 2:
                        DialogDeleteCompany dialogDeleteCompany = new DialogDeleteCompany(context, companyTitle, company_ID);
                        dialogDeleteCompany.show();
                        removeItem(position);
                        break;
                }
                return true;
            }
        };

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            return false;
        }

    }

    public void removeItem(int position){
        companyModelList.remove(position);
        // notifyItemRemoved(position);
    }
}
