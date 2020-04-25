package com.example.zcdemo03.service.serviceImpl;



import com.example.zcdemo03.mapper.ZcMapperDao;
import com.example.zcdemo03.service.ZcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZcServiceImpl implements ZcService {
   @Autowired
    private ZcMapperDao zcMapperDao;
    @Override
    public List<String> getName() {
        return zcMapperDao.getName();
    }
}
