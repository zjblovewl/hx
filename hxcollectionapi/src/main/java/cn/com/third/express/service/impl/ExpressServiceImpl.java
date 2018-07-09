package cn.com.third.express.service.impl;

import cn.com.base.constant.BaseConstant;
import cn.com.base.service.DictionaryService;
import cn.com.base.util.BaseLogger;
import cn.com.base.util.StringUtil;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamVo;
import cn.com.third.express.service.ExpressService;
import cn.kuaidi.api.KdniaoTrackQueryAPI;

import com.fasterxml.jackson.databind.ObjectMapper;






import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @类功能说明：物流快递信息业务实现层
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/5/9 上午10:08
 * @版本：V1.0
 */
@Service
public class ExpressServiceImpl extends BaseLogger implements ExpressService {

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private BaseConstant baseConstant;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseParamVo getExpressInfo(RequestParamVo vo) {
        try{
        	Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody(); 
        	String nu = bodyMap.get("nu").toString();
        	URL u = new URL("http://route.showapi.com/880-1?showapi_appid=67707&nu="+nu+"&showapi_sign=6b56aca5144f45f1a44817252d623957");
        	InputStream in = u.openStream();
        	ByteArrayOutputStream out = new ByteArrayOutputStream();
        	
        	byte buf[] = new byte[1024];
        	int read = 0;
        	while((read = in .read(buf)) > 0) 
        	{
        		out.write(buf, 0, read);
        	}
        	if( in != null) 
        	{
        		in .close();
        	}
        	
        	byte b[] = out.toByteArray();
        	String str = new String(b, "utf-8");
        	JSONObject obj = new JSONObject().fromObject(str);
        	int showapi_res_code = Integer.parseInt(obj.get("showapi_res_code").toString());
        	if(showapi_res_code == 0) // 成功
        	{
        		Object showapi_res_body = obj.get("showapi_res_body");
        		Map<String,Object> reusltMap = new HashMap<>();
        		reusltMap.put("res", showapi_res_body);
        		return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询物流信息成功","","",reusltMap);
        	}
        	
        	log.error(obj.get("showapi_res_error").toString());
        	return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询不到物流信息","","","");
        }catch (Exception e)
        {
            log.error("查询物流信息异常:{}"+e.getMessage());
            e.printStackTrace();
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询不到物流信息","","","");
        }
    }

   
}
