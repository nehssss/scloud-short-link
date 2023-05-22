package com.haitaos.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


public interface NotifyService {
    void end(String phone, String templateId, String code);
}
