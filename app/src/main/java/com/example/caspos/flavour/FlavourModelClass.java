package com.example.caspos.flavour;

public class FlavourModelClass {
    private String flavour_ID;
    private String flavour_title;
    private String flavour_description;
    private String flavour_UUID ;
    private String flavour_InsertBY;
    private String flavour_DeactiveBy;
    private String flavour_ModifiedBy;
    private String flavour_DeactiveReason;
    private String flavour_InsertTimeDate;
    private String flavour_DeactiveTimeDate;
    private String flavour_LastModifiedTimeDate;
    private int flavour_Status;
    private int flavour_Signature ;

    public FlavourModelClass() {
    }

    public FlavourModelClass(String flavour_ID, String flavour_title, String flavour_description, String flavour_UUID, String flavour_InsertBY, String flavour_DeactiveBy, String flavour_ModifiedBy, String flavour_DeactiveReason, String flavour_InsertTimeDate, String flavour_DeactiveTimeDate, String flavour_LastModifiedTimeDate, int flavour_Status, int flavour_Signature) {
        this.flavour_ID = flavour_ID;
        this.flavour_title = flavour_title;
        this.flavour_description = flavour_description;
        this.flavour_UUID = flavour_UUID;
        this.flavour_InsertBY = flavour_InsertBY;
        this.flavour_DeactiveBy = flavour_DeactiveBy;
        this.flavour_ModifiedBy = flavour_ModifiedBy;
        this.flavour_DeactiveReason = flavour_DeactiveReason;
        this.flavour_InsertTimeDate = flavour_InsertTimeDate;
        this.flavour_DeactiveTimeDate = flavour_DeactiveTimeDate;
        this.flavour_LastModifiedTimeDate = flavour_LastModifiedTimeDate;
        this.flavour_Status = flavour_Status;
        this.flavour_Signature = flavour_Signature;
    }

    public String getFlavour_ID() {
        return flavour_ID;
    }

    public void setFlavour_ID(String flavour_ID) {
        this.flavour_ID = flavour_ID;
    }

    public String getFlavour_title() {
        return flavour_title;
    }

    public void setFlavour_title(String flavour_title) {
        this.flavour_title = flavour_title;
    }

    public String getFlavour_description() {
        return flavour_description;
    }

    public void setFlavour_description(String flavour_description) {
        this.flavour_description = flavour_description;
    }

    public String getFlavour_UUID() {
        return flavour_UUID;
    }

    public void setFlavour_UUID(String flavour_UUID) {
        this.flavour_UUID = flavour_UUID;
    }

    public String getFlavour_InsertBY() {
        return flavour_InsertBY;
    }

    public void setFlavour_InsertBY(String flavour_InsertBY) {
        this.flavour_InsertBY = flavour_InsertBY;
    }

    public String getFlavour_DeactiveBy() {
        return flavour_DeactiveBy;
    }

    public void setFlavour_DeactiveBy(String flavour_DeactiveBy) {
        this.flavour_DeactiveBy = flavour_DeactiveBy;
    }

    public String getFlavour_ModifiedBy() {
        return flavour_ModifiedBy;
    }

    public void setFlavour_ModifiedBy(String flavour_ModifiedBy) {
        this.flavour_ModifiedBy = flavour_ModifiedBy;
    }

    public String getFlavour_DeactiveReason() {
        return flavour_DeactiveReason;
    }

    public void setFlavour_DeactiveReason(String flavour_DeactiveReason) {
        this.flavour_DeactiveReason = flavour_DeactiveReason;
    }

    public String getFlavour_InsertTimeDate() {
        return flavour_InsertTimeDate;
    }

    public void setFlavour_InsertTimeDate(String flavour_InsertTimeDate) {
        this.flavour_InsertTimeDate = flavour_InsertTimeDate;
    }

    public String getFlavour_DeactiveTimeDate() {
        return flavour_DeactiveTimeDate;
    }

    public void setFlavour_DeactiveTimeDate(String flavour_DeactiveTimeDate) {
        this.flavour_DeactiveTimeDate = flavour_DeactiveTimeDate;
    }

    public String getFlavour_LastModifiedTimeDate() {
        return flavour_LastModifiedTimeDate;
    }

    public void setFlavour_LastModifiedTimeDate(String flavour_LastModifiedTimeDate) {
        this.flavour_LastModifiedTimeDate = flavour_LastModifiedTimeDate;
    }

    public int getFlavour_Status() {
        return flavour_Status;
    }

    public void setFlavour_Status(int flavour_Status) {
        this.flavour_Status = flavour_Status;
    }

    public int getFlavour_Signature() {
        return flavour_Signature;
    }

    public void setFlavour_Signature(int flavour_Signature) {
        this.flavour_Signature = flavour_Signature;
    }
}
