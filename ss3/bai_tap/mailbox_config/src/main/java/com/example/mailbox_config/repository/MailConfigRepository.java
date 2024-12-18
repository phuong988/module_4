package com.example.mailbox_config.repository;

import com.example.mailbox_config.model.MailConfig;
import org.springframework.stereotype.Repository;

@Repository
public class MailConfigRepository {
    private MailConfig mailConfig = new MailConfig("English", 25, false, "Thor\nKing, Asgard");

    public MailConfig getConfig() {
        return mailConfig;
    }

    public void updateConfig(MailConfig newConfig) {
        this.mailConfig = newConfig;
    }
}
