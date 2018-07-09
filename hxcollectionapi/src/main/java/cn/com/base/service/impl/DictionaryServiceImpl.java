package cn.com.base.service.impl;

import cn.com.base.service.DictionaryService;
import cn.com.mobile.wallet.dao.MyBankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @类功能说明：数据字典集合实现层
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/10 上午9:55
 * @版本：V1.0
 */
@Service
public class DictionaryServiceImpl implements DictionaryService{

    @Autowired
    private MyBankMapper bankMapper;

    @Override
    public List<Map<String, Object>> getValueByKey(String key) {
        return bankMapper.getValueByKey(key);
    }
}
