package com.example.mailbox_config.service.impl;

import com.example.mailbox_config.model.MailConfig;
import com.example.mailbox_config.repository.MailConfigRepository;
import com.example.mailbox_config.service.IMailConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailConfigService implements IMailConfigService {
    @Autowired
    private MailConfigRepository repository;

    public MailConfig getCurrentConfig() {
        return repository.getConfig();
    }

    public void saveConfig(MailConfig newConfig) {
        repository.updateConfig(newConfig);
    }

}
