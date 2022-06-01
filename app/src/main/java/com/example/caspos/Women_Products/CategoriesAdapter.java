package com.example.caspos.Women_Products;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.viewholder> implements Filterable {
    Context context;


    List<CategoriesModel> categoriesModels, listFull;
    public OnItemClickListener onItemClickListener;
    DBHelperClass dbHelper;
    int position;
    /////////////////////////////

    TextView tvTitlecategory;
    EditText etCategoryReason;
    Button btnDeleteCategory, btnCancelDeleteCategory;
    String Categoryd;
        //////////////////////

    public CategoriesAdapter(Context context, List<CategoriesModel> categoriesModels) {
        this.context = context;
        this.categoriesModels = categoriesModels;
        listFull = new ArrayList<>(categoriesModels);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.catagory_row_layout, null);
        context = viewGroup.getContext();
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {
        CategoriesModel categoriesModel = categoriesModels.get(i);
        viewholder.tvTitle.setText(categoriesModel.getProduct_Category_Title());
        viewholder.tvdescription.setText(categoriesModel.getProduct_Category_Description());

        Log.d("HH", " Image for Category" + categoriesModel.getProduct_Category_Pic());
        Glide.with(context).load(categoriesModel.getProduct_Category_Pic()).into(viewholder.iv_category);
        //    Glide.with(context).load(categoriesModel.getProduct_Category_Pic()).into(viewholder.iv_category);
    }

    @Override
    public int getItemCount() {
        return categoriesModels.size();
    }

    interface OnItemClickListener {
        void OnItem(int position);
    }

    @Override
    public Filter getFilter() {
        return categoriesFilter;
    }

    private Filter categoriesFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CategoriesModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CategoriesModel item : listFull) {
                    if (item.getProduct_Category_Title().toLowerCase().contains(filterPattern)) {
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
            categoriesModels.clear();
            categoriesModels.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

    };

    public class viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        TextView tvTitle, tvdescription;
        ImageView iv_category;
        String productTypeID;
        String CategoryTitle, CategoryDescription, CategoryID;


        public viewholder(@NonNull View rootView) {
            super(rootView);

            tvTitle = rootView.findViewById(R.id.tv_rowForCategoryTitle);
            tvdescription = rootView.findViewById(R.id.tv_rowForCategoryDescription);
            iv_category = rootView.findViewById(R.id.imageViewForCategoryRow);

            rootView.setOnClickListener(new View.OnClickListener() {
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
            rootView.setOnCreateContextMenuListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            return false;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            position = getAdapterPosition();
            CategoryTitle = categoriesModels.get(position).getProduct_Category_Title();
            CategoryDescription = categoriesModels.get(position).getProduct_Category_Description();
            CategoryID = categoriesModels.get(position).getProduct_Category_Id() + "";
            productTypeID = categoriesModels.get(position).getProduct_Type_Id();

            MenuItem update = menu.add(menu.NONE, 1, 1, "Update");
            MenuItem delete = menu.add(menu.NONE, 2, 2, "Delete");

            update.setOnMenuItemClickListener(showmenu);
            delete.setOnMenuItemClickListener(showmenu);
        }

        public final MenuItem.OnMenuItemClickListener showmenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case 1: {
                        DialogUpdateCategory dialog = new DialogUpdateCategory(context, CategoryTitle, CategoryDescription, CategoryID, productTypeID);
                        dialog.show();
                        break;
                    }
                    case 2: {

                       // DialogDeleteCategory dialogDelete = new DialogDeleteCategory(context, CategoryTitle, CategoryID);
                       // dialogDelete.show();
                        removeItem(CategoryID,CategoryTitle);
                       break;
                    }
                }
                return true;
            }
        };
    }

    public void removeItem(final String id, String title) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_dialog_delete_category);
        tvTitlecategory = dialog.findViewById(R.id.tvCategoryTitleForDeleteDialog);
        etCategoryReason = dialog.findViewById(R.id.etCategoryDescriptionForDeleteDialog);
        btnDeleteCategory = dialog.findViewById(R.id.btnDeleteCategory);
        btnCancelDeleteCategory = dialog.findViewById(R.id.btnCancelDeleteCategory);


        tvTitlecategory.setText(title);
        dbHelper = DBHelperClass.getInstance(context);

       dialog.show();
        btnDeleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etCategoryReason.getText().toString().equals(null)) {
                    etCategoryReason.setError("Enter Fields");
                } else if (dbHelper.DBCategoryDelete(id)) {
                    Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    categoriesModels.remove(position);
                    notifyItemRemoved(position);
                    notifyDataSetChanged();
                }
            }
        });
        btnCancelDeleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

}
