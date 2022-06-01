package com.example.caspos.Mens_Products;

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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.ViewHolder> implements Filterable {

    Context context;
    List<ProductTypeModel> list;
    List<ProductTypeModel> listFull;
    int position;
    public static String name1, price1;

    public OnItemClickListener onItemClickListener;

    /////////////////////////////
    TextView tv_ProductTitle;
    EditText et_ProductReason;
    String deletedProductTypeID;
    Button btn_DeleteProductType, btn_CancelDeleteProductType;

    DBHelperClass dbHelper;
    ///////////////////////////////////////

    public ProductTypeAdapter(Context context, List<ProductTypeModel> list) {
        this.context = context;
        this.list = list;
        listFull = new ArrayList<>(list);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public Filter getFilter() {
        return productTypeFilter;
    }

    private Filter productTypeFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ProductTypeModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ProductTypeModel item : listFull) {
                    if (item.getProduct_type_title().toLowerCase().contains(filterPattern)) {
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
            list.clear();
            list.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

    };

    interface OnItemClickListener {
        void OnItem(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView;
        rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_row_layout, null);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ProductTypeModel model = list.get(position);
        viewHolder.title.setText(model.getProduct_type_title());
        viewHolder.description.setText(model.getProduct_type_description());

        Log.d("TAG", "onBindViewHolder: " + model.getProductTypePic());
        Glide.with(context).load(model.getProductTypePic()).into(viewHolder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        TextView title, description;
        ImageView iv;

        ScrollView scrollView;
        String productTitle, productDescription, productID;
        int productStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.imageViewForProductRow);
            title = itemView.findViewById(R.id.tv_rowForProductTitle);
            description = itemView.findViewById(R.id.tv_rowForProductDescription);
//            scrollView = itemView.findViewById(R.id.scrollViewForProductType);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

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
        public boolean onMenuItemClick(MenuItem item) {
            return false;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            position = getAdapterPosition();
            productTitle = list.get(position).getProduct_type_title();
            productDescription = list.get(position).getProduct_type_description();
            productStatus = list.get(position).getProductTypeStatus();
            productID = list.get(position).getProduct_type_ID();

            MenuItem update = menu.add(menu.NONE, 1, 1, "Update");
            MenuItem delete = menu.add(menu.NONE, 2, 2, "Delete");

            update.setOnMenuItemClickListener(showMenu);
            delete.setOnMenuItemClickListener(showMenu);
        }

        private final MenuItem.OnMenuItemClickListener showMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case 1:
                        //// Update Dialog Call here ///
                        UpdateProductDialog dialog = new UpdateProductDialog(context, productTitle, productDescription, productID);
                        list.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyDataSetChanged();
                        dialog.show();
                        break;
                    case 2:
                        // DialogDeleteProduct deleteProduct = new DialogDeleteProduct(context, productTitle, position, productID);
                        //  deleteProduct.show();

                        removeItem(productTitle, productID);
                        break;
                }
                return true;
            }
        };
    }

    public void removeItem(String title, final String id) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_dialog_delete_product);

        tv_ProductTitle = dialog.findViewById(R.id.tvProductTitleForDeleteDialog);
        et_ProductReason = dialog.findViewById(R.id.tvProductDescriptionForDeleteDialog);
        btn_DeleteProductType = dialog.findViewById(R.id.btnDeleteProduct);
        btn_CancelDeleteProductType = dialog.findViewById(R.id.btnCancelDeleteProductType);

        dialog.show();
        dbHelper = DBHelperClass.getInstance(context);

        tv_ProductTitle.setText(title);
        final DBHelperClass dbHelper = DBHelperClass.getInstance(context);


        btn_DeleteProductType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_ProductReason.getText().toString().equals(null)) {
                    et_ProductReason.setError("Enter Fields");
                } else if (dbHelper.dbProductTypeDelete(id)) {
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                    //context.startActivity(new Intent(context, ProductTypeActivity.class));
                    dialog.dismiss();
                    list.remove(position);
                    notifyItemRemoved(position);
                    notifyDataSetChanged();
                }
            }
        });
        btn_CancelDeleteProductType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });

    }


    // notifyItemRemoved(position);


}
