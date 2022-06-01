package com.example.caspos.varient;

public class VariantModelClass {
    private String variant_ID;
    private String variant_title;
    private String variant_description;
    private String variant_UUID ;
    private String variant_InsertBY;
    private String variant_DeactiveBy;
    private String variant_ModifiedBy;
    private String variant_DeactiveReason;
    private String variant_InsertTimeDate;
    private String variant_DeactiveTimeDate;
    private String variant_LastModifiedTimeDate;
    private int variant_Status;
    private int variant_Signature ;

    public VariantModelClass() {
    }

    public VariantModelClass(String variant_ID, String variant_title, String variant_description, String variant_UUID, String variant_InsertBY, String variant_DeactiveBy, String variant_ModifiedBy, String variant_DeactiveReason, String variant_InsertTimeDate, String variant_DeactiveTimeDate, String variant_LastModifiedTimeDate, int variant_Status, int variant_Signature) {
        this.variant_ID = variant_ID;
        this.variant_title = variant_title;
        this.variant_description = variant_description;
        this.variant_UUID = variant_UUID;
        this.variant_InsertBY = variant_InsertBY;
        this.variant_DeactiveBy = variant_DeactiveBy;
        this.variant_ModifiedBy = variant_ModifiedBy;
        this.variant_DeactiveReason = variant_DeactiveReason;
        this.variant_InsertTimeDate = variant_InsertTimeDate;
        this.variant_DeactiveTimeDate = variant_DeactiveTimeDate;
        this.variant_LastModifiedTimeDate = variant_LastModifiedTimeDate;
        this.variant_Status = variant_Status;
        this.variant_Signature = variant_Signature;
    }

    public String getVariant_ID() {
        return variant_ID;
    }

    public void setVariant_ID(String variant_ID) {
        this.variant_ID = variant_ID;
    }

    public String getVariant_title() {
        return variant_title;
    }

    public void setVariant_title(String variant_title) {
        this.variant_title = variant_title;
    }

    public String getVariant_description() {
        return variant_description;
    }

    public void setVariant_description(String variant_description) {
        this.variant_description = variant_description;
    }

    public String getVariant_UUID() {
        return variant_UUID;
    }

    public void setVariant_UUID(String variant_UUID) {
        this.variant_UUID = variant_UUID;
    }

    public String getVariant_InsertBY() {
        return variant_InsertBY;
    }

    public void setVariant_InsertBY(String variant_InsertBY) {
        this.variant_InsertBY = variant_InsertBY;
    }

    public String getVariant_DeactiveBy() {
        return variant_DeactiveBy;
    }

    public void setVariant_DeactiveBy(String variant_DeactiveBy) {
        this.variant_DeactiveBy = variant_DeactiveBy;
    }

    public String getVariant_ModifiedBy() {
        return variant_ModifiedBy;
    }

    public void setVariant_ModifiedBy(String variant_ModifiedBy) {
        this.variant_ModifiedBy = variant_ModifiedBy;
    }

    public String getVariant_DeactiveReason() {
        return variant_DeactiveReason;
    }

    public void setVariant_DeactiveReason(String variant_DeactiveReason) {
        this.variant_DeactiveReason = variant_DeactiveReason;
    }

    public String getVariant_InsertTimeDate() {
        return variant_InsertTimeDate;
    }

    public void setVariant_InsertTimeDate(String variant_InsertTimeDate) {
        this.variant_InsertTimeDate = variant_InsertTimeDate;
    }

    public String getVariant_DeactiveTimeDate() {
        return variant_DeactiveTimeDate;
    }

    public void setVariant_DeactiveTimeDate(String variant_DeactiveTimeDate) {
        this.variant_DeactiveTimeDate = variant_DeactiveTimeDate;
    }

    public String getVariant_LastModifiedTimeDate() {
        return variant_LastModifiedTimeDate;
    }

    public void setVariant_LastModifiedTimeDate(String variant_LastModifiedTimeDate) {
        this.variant_LastModifiedTimeDate = variant_LastModifiedTimeDate;
    }

    public int getVariant_Status() {
        return variant_Status;
    }

    public void setVariant_Status(int variant_Status) {
        this.variant_Status = variant_Status;
    }

    public int getVariant_Signature() {
        return variant_Signature;
    }

    public void setVariant_Signature(int variant_Signature) {
        this.variant_Signature = variant_Signature;
    }
}
