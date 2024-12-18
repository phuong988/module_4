package com.example.mailbox_config.service;

import com.example.mailbox_config.model.MailConfig;

public interface IService {
    MailConfig getCurrentConfig();
    void saveConfig(MailConfig newConfig);
}
