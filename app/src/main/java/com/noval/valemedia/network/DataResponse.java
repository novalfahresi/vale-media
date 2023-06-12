package com.noval.valemedia.network;

import com.google.gson.annotations.SerializedName;

public class DataResponse<T> {
    @SerializedName("results")
    private T data;

    public T getData() {
        return data;
    }
}
