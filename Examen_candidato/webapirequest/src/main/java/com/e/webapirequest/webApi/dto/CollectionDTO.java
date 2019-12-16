package com.e.webapirequest.webApi.dto;

import org.json.JSONObject;

import java.io.Serializable;

public class CollectionDTO  implements Serializable {

    private static final String COLLECTION_KEY = "collection";
    private static final String COLLECTION_ID_KEY = "collection_id";
    private static final String RES_COUNT_KEY = "res_count";
    private static final String IMAGE_URL_KEY = "image_url";
    private static final String URL_KEY = "url";
    private static final String TITLE_KEY = "title";
    private static final String DESCRIPTION_KEY = "description";

    public String getMcollection() {
        return mcollection;
    }

    private String mcollection;
    private int mCollectionId;
    private int mResCount;
    private String mImageURL;
    private String mURL;
    private String mTitle;
    private String mDescription;


    public CollectionDTO(JSONObject nodoPrincipal) {

        mcollection = nodoPrincipal.optString(COLLECTION_KEY);
        mCollectionId = nodoPrincipal.optInt(COLLECTION_ID_KEY);
        mResCount = nodoPrincipal.optInt(RES_COUNT_KEY);
        mImageURL = nodoPrincipal.optString(IMAGE_URL_KEY);
        mURL = nodoPrincipal.optString(URL_KEY);
        mTitle = nodoPrincipal.optString(TITLE_KEY);
        mDescription = nodoPrincipal.optString(DESCRIPTION_KEY);

    }

    public int getCollectionId() {
        return mCollectionId;
    }

    public int getResCount() {
        return mResCount;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public String getURL() {
        return mURL;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

}
