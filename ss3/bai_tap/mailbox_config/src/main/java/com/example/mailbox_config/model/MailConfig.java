package com.example.mailbox_config.model;

public class MailConfig {
    private String language;
    private int pageSize;
    private boolean spamFilter;
    private String signature;

    public MailConfig() {
    }

    public MailConfig(String language, int pageSize, boolean spamFilter, String signature) {
        this.language = language;
        this.pageSize = pageSize;
        this.spamFilter = spamFilter;
        this.signature = signature;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean getSpamFilter() {
        return spamFilter;
    }

    public void setSpamFilter(boolean spamsFilter) {
        this.spamFilter = spamsFilter;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
