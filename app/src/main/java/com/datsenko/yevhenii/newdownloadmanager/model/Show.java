package com.datsenko.yevhenii.newdownloadmanager.model;

/**
 * Created by Yevhenii on 2/26/2016.
 */
public class Show {
    private int id;
    private String url;
    private String title;
    private String imageUrlSmallPreview;
    private int isBuyed = 0;
    private int isChecked = 0;
    private int isDownloaded = 0;

    public Show() {
    }

    public Show(int id, String url, String title, String imageUrlSmallPreview, int isBuyed, int isChecked, int isDownloaded) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.imageUrlSmallPreview = imageUrlSmallPreview;
        this.isBuyed = isBuyed;
        this.isChecked = isChecked;
        this.isDownloaded = isDownloaded;
    }

    public void setIsDownloaded(int isDownloaded) {
        this.isDownloaded = isDownloaded;
    }

    public int isDownloaded() {

        return isDownloaded;
    }

    public void setIsBuyed(int isBuyed) {
        this.isBuyed = isBuyed;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public int isBuyed() {

        return isBuyed;
    }

    public int isChecked() {
        return isChecked;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrlSmallPreview(String imageUrlSmallPreview) {
        this.imageUrlSmallPreview = imageUrlSmallPreview;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrlSmallPreview() {
        return imageUrlSmallPreview;
    }
}
