package com.example.keepfitness;

public class TypeItem {
    private String mTypeName;
    private int mTypesImage;

    public TypeItem(String TypeName, int TypeImage) {
        mTypeName = TypeName;
        mTypesImage = TypeImage;
    }

    public String getTypeName() {
        return mTypeName;
    }

    public int getTypeImage() {
        return mTypesImage;
    }
}
