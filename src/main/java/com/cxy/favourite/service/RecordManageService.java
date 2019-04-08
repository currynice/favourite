package com.cxy.favourite.service;

import com.cxy.favourite.jpa.RecordManageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordManageService {
    @Autowired
    private RecordManageRepository recordManageRepository;
}
