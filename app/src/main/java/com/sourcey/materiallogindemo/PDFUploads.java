package com.sourcey.materiallogindemo;

/**
 * Created by HP on 23-May-19.
 */

public class PDFUploads {
    public String name;

    public String url;

    public PDFUploads() {
    }

    public PDFUploads(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
