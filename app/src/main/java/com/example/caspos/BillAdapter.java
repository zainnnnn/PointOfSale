package com.example.caspos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class BillAdapter extends RecyclerView.Adapter<BillAdapter.viewHolderbill> {

    Context context;
    List<BillModel> billModelslist;

    public BillAdapter(List<BillModel> billModels, Context context) {
        this.billModelslist = billModels;
        this.context=context;
    }

    @NonNull
    @Override
    public viewHolderbill onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_billrow,null);

        return  new viewHolderbill(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderbill viewHolderbill, int i) {

        BillModel billModel=billModelslist.get(i);
        viewHolderbill.tvProductName.setText(billModel.getProductName());
        viewHolderbill.tvProductQuantity.setText(billModel.getProductQuantity()+"");
        viewHolderbill.tvProductSerialnbr.setText(billModel.getProductSrNo()+"");
        viewHolderbill.tvProductPrice.setText(billModel.getProdctPrice()+"");


        int total;
        total = billModel.getProdctPrice()* billModel.getProductQuantity();
        viewHolderbill.tvbillTotal.setText(total+"");
        int j;
      //  for (int p=0;i<billModelslist.size();p++){
        //    j=p+1;
        ///    viewHolderbill.tvProductSerialnbr.setText(j+"");
       // }
    }

    @Override
    public int getItemCount() {
        return billModelslist.size();
    }

    public  class viewHolderbill extends RecyclerView.ViewHolder{

        TextView tvProductName,tvProductQuantity,tvProductSerialnbr,tvbillTotal,tvProductPrice;
        public viewHolderbill(@NonNull View rootView) {
            super(rootView);
            tvProductQuantity=rootView.findViewById(R.id.rowBilltv_BillProductQt);
            tvProductName=rootView.findViewById(R.id.rowBilltv_BillProductName);
            tvProductSerialnbr=rootView.findViewById(R.id.rowBilltv_SrNO);
            tvbillTotal=rootView.findViewById(R.id.rowBilltv_total);
            tvProductPrice=rootView.findViewById(R.id.rowBilltv_BillProductPrice);


        }
    }
}
