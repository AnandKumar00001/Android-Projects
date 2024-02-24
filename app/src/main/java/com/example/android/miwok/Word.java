package com.example.android.miwok;


public class Word {
    private final String mMiwokTranslation;
    private final String mDefaultTranslation;
    private int mImageResourceID = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private final int mAudioResourceID;

    public Word(String MiwokTranslation, String DefaultTranslation, int ImageResourceID, int AudioResourceID) {
        mDefaultTranslation = DefaultTranslation;
        mMiwokTranslation = MiwokTranslation;
        mImageResourceID = ImageResourceID;
        mAudioResourceID = AudioResourceID;
    }

    public Word(String MiwokTranslation, String DefaultTranslation , int AudioResourceID) {
        mDefaultTranslation = DefaultTranslation;
        mMiwokTranslation = MiwokTranslation;
        mAudioResourceID = AudioResourceID;

    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getmImageResourceID() {
        return mImageResourceID;
    }

    public int getmAudioResourceID() {
        return mAudioResourceID;
    }

    public boolean hasImage() {
        return mImageResourceID != NO_IMAGE_PROVIDED;
    }
}