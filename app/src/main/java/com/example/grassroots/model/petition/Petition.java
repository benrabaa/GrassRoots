package com.example.grassroots.model.petition;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;


import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Petition implements Serializable {
    private String mPetitionName;
    private String mPetitionSupporter;
    private String mPetitionDescription;
    private Uri mPetitionImageUri;
    private String mPetitionImageURL;
    private int mPetitionSignature;
    private int mPetitionSignatureGoal;
    private String petitionKey;
    private List<PetitionUpdates> mPetitionUpdatesList =new ArrayList();



    @Exclude
    public String getPetitionKey() {
        return petitionKey;
    }

    @Exclude
    public void setPetitionKey(String petitionKey) {
        this.petitionKey = petitionKey;
    }

    public List<PetitionUpdates> getmPetitionUpdatesList() {
        return mPetitionUpdatesList;
    }

    public void setmPetitionUpdatesList(List<PetitionUpdates> mPetitionUpdatesList) {
        this.mPetitionUpdatesList = mPetitionUpdatesList;
    }

    public Petition() { }

    public Petition( String petitionName, String petitionSupporter, String petitionDescription, String petitionImage,int petitionSignatureGoal,int petitionSignature,List<PetitionUpdates>petitionUpdatesList) {
        mPetitionName =petitionName;
        mPetitionSupporter =petitionSupporter;
        mPetitionDescription =petitionDescription;
        mPetitionImageURL=petitionImage;
        mPetitionSignatureGoal=petitionSignatureGoal;
        mPetitionSignature=petitionSignature;
        mPetitionUpdatesList=petitionUpdatesList;
    }


    public int getmPetitionSignatureGoal() {
        return mPetitionSignatureGoal;
    }

    public void setmPetitionSignatureGoal(int mPetitionSignatureGoal) {
        this.mPetitionSignatureGoal = mPetitionSignatureGoal;
    }

    public void setmPetitionSignature(int mPetitionSignature ){
        this.mPetitionSignature=mPetitionSignature;
    }
    public int getmPetitionSignature(){
        return mPetitionSignature;
    }
    public String getmPetitionName() {
        return mPetitionName;
    }

    public void setmPetitionName(String mPetitionName) {
        this.mPetitionName = mPetitionName;
    }

    public String getmPetitionSupporter() {
        return mPetitionSupporter;
    }

    public void setmPetitionSupporter(String mPetitionSupporter) {
        this.mPetitionSupporter = mPetitionSupporter;
    }

    public String getmPetitionDescription() {
        return mPetitionDescription;
    }

    public void setmPetitionDescription(String mPetitionDescription) {
        this.mPetitionDescription = mPetitionDescription;
    }

    public Uri getmPetitionImageUri() {
        return mPetitionImageUri;
    }

    public void setmPetitionImageUri(Uri mPetitionImageUri) {
        this.mPetitionImageUri = mPetitionImageUri;
    }

    public String getmPetitionImageURL() {
        return mPetitionImageURL;
    }

    public void setmPetitionImageURL(String mPetitionImageURL) {
        this.mPetitionImageURL = mPetitionImageURL;
    }


}

