package com.example.caspos.Kids_Products;

import android.app.Dialog;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.example.caspos.Women_Products.CategoriesAdapter;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.viewholder> implements Filterable {
    Context context;
    List<SubCategoriesModel> subCategoriesModelList, listFull;
    int position1;
    public OnItemClickListener onItemClickListener;

    /////////////////////////////////////
    TextView tv_title;
    EditText et_DeletionReason;
    Button btn_DeleteSubCategory, btn_CancelDeleteSubCatagory;
    String subCategoryID;
    DBHelperClass dbHelperClass;
    ///////////////////////////////////////////////

    public SubCategoryAdapter(Context context, List<SubCategoriesModel> subCategoriesModelList) {
        this.context = context;
        this.subCategoriesModelList = subCategoriesModelList;
        listFull = new ArrayList<>(subCategoriesModelList);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    interface OnItemClickListener {
        void OnItem(int position);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowforsubcategory, null);
        context = parent.getContext();
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        SubCategoriesModel categories = subCategoriesModelList.get(position);
        holder.tvTitle.setText(categories.getSub_category_Title());
        holder.tvdescription.setText(categories.getSub_category_Description());
        Glide.with(context).load(categories.getSub_category_Pic()).into(holder.iv_subCategory);
        position1 = position;
    }

    @Override
    public int getItemCount() {
        return subCategoriesModelList.size();
    }

    @Override
    public Filter getFilter() {
        return subCategoryFilter;
    }

    private Filter subCategoryFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<SubCategoriesModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (SubCategoriesModel item : listFull) {
                    if (item.getSub_category_Title().toLowerCase().contains(filterPattern)) {
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
            subCategoriesModelList.clear();
            subCategoriesModelList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

    };

    public class viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        TextView tvTitle, tvdescription;
        ImageView iv_subCategory;

        String subCategoryTitle, subCategoryDescription, subCategoryID;
        int subCategoryStatus;
        int position;
        String CategoryID;



        public viewholder(@NonNull View rootView) {
            super(rootView);
            tvTitle = rootView.findViewById(R.id.tv_Title);
            tvdescription = rootView.findViewById(R.id.tv_describtion);
            iv_subCategory = rootView.findViewById(R.id.imageViewForRowSubCategory);


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
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            position = getAdapterPosition();
            subCategoryTitle = subCategoriesModelList.get(position).getSub_category_Title();
            subCategoryDescription = subCategoriesModelList.get(position).getSub_category_Description();
            subCategoryID = subCategoriesModelList.get(position).getSub_category_Id();
            subCategoryStatus = subCategoriesModelList.get(position).getSubcategory_Status();
            CategoryID = subCategoriesModelList.get(position).getCategory_ID();
            MenuItem update = menu.add(menu.NONE, 1, 1, "Update");
            MenuItem delete = menu.add(menu.NONE, 2, 2, "Delete");

            update.setOnMenuItemClickListener(showmenu);
            delete.setOnMenuItemClickListener(showmenu);
        }

        private final MenuItem.OnMenuItemClickListener showmenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case 1:
                        DialogUpdateSubcategory dialog = new DialogUpdateSubcategory(context, subCategoryTitle, subCategoryDescription, subCategoryID, CategoryID);
                        dialog.show();
                        break;
                    case 2:
                      //  DialogDeleteSubCategory dialogDeleteSubCategory = new DialogDeleteSubCategory(context, subCategoryTitle, subCategoryDescription, subCategoryID, CategoryID);
                      //  dialogDeleteSubCategory.show();
                        removeItem(subCategoryID,subCategoryTitle);
                        break;
                }
                return true;
            }
        };

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            return false;
        }

    }

    public void removeItem(final String id, String title) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.delete_sub_category);
        tv_title = dialog.findViewById(R.id.tvDeleteSubCategoryTitle);
        et_DeletionReason = dialog.findViewById(R.id.etSubCategoryReason);
        btn_DeleteSubCategory = dialog.findViewById(R.id.btnDeleteSubCategory);
        btn_CancelDeleteSubCatagory = dialog.findViewById(R.id.btnCancelDeleteSubCategory);

        dialog.show();

        tv_title.setText(title);

        dbHelperClass = DBHelperClass.getInstance(context);

        btn_DeleteSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_DeletionReason.getText().toString().equals(null)) {
                    et_DeletionReason.setError("put Reason");
                } else if (dbHelperClass.dbSubCategoryDelete(id))
                    Toast.makeText(context, "Delete Successful", Toast.LENGTH_SHORT).show();
                //   Intent intent = new Intent(context, SubCategoryActivity.class);
                //  intent.putExtra("CategoryID", categoryID_get_fromSubCategory);
                //   context.startActivity(intent);
                dialog.dismiss();
                subCategoriesModelList.remove(position1);
                notifyItemRemoved(position1);
                notifyDataSetChanged();
            }
        });
        btn_CancelDeleteSubCatagory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
        // notifyItemRemoved(position);

}

