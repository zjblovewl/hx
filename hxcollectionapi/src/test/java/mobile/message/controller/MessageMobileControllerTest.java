package mobile.message.controller;

import java.util.HashMap;
import java.util.Map;

import mobile.BaseControllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.App;
import cn.com.base.constant.BaseConstant;
import cn.com.base.util.AESUtils;
import cn.com.base.vo.RequestParamVo;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年4月13日 下午3:01:47 
 * @版本：V1.0  
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class MessageMobileControllerTest extends BaseControllerTest{
	@Autowired
    private BaseConstant baseConstant;

    String rootUrl =  super.baseUrl +  "api/mymessagemobile/";

    private static ObjectMapper objectMapper = new ObjectMapper();
    
    private Logger log = LoggerFactory.getLogger(MessageMobileControllerTest.class);
    
    @Test
    public void getMyMessageRecordsTest()
    {
    	 try {
             String url = rootUrl + "getMyMessageRecords";

             InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
             	
             // 业务参数
             Map<String, Object> body = new HashMap<String, Object>();
             body.put("user_id", "2");
             body.put("platform", "1");
                                   
             RequestParamVo requestParam = new RequestParamVo(header,body);
             String paras = objectMapper.writeValueAsString(requestParam);
             log.info("params==========" + paras);
             paras = AESUtils.encrypt(baseConstant.getIvString(),baseConstant.getSecretKey(),paras);
             
             HttpHeaders headers = new HttpHeaders();
             headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
             MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
             params.add( "paras", paras );
             HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
             ResponseEntity<String> response = super.getInstance().exchange(url, HttpMethod.POST, requestEntity, String.class);                         
             System.out.println(response.getBody());
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
    
    @Test
    public void getLeavingMessageRecordsByIdTest(){
    	try {
            String url = rootUrl + "getLeavingMessageRecordsById";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();
            body.put("user_id", "2");            
            body.put("page_num", 1);
            body.put("page_size", 10);
                                  
            RequestParamVo requestParam = new RequestParamVo(header,body);
            String paras = objectMapper.writeValueAsString(requestParam);
            log.info("params==========" + paras);
            paras = AESUtils.encrypt(baseConstant.getIvString(),baseConstant.getSecretKey(),paras);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
            params.add( "paras", paras );
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
            ResponseEntity<String> response = super.getInstance().exchange(url, HttpMethod.POST, requestEntity, String.class);                         
            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void getSysMessageRecordsTest(){
    	try {
            String url = rootUrl + "getSysMessageRecords";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();            
            body.put("platform", "1");
            body.put("page_num", 1);
            body.put("page_size", 10);            
                                  
            RequestParamVo requestParam = new RequestParamVo(header,body);
            String paras = objectMapper.writeValueAsString(requestParam);
            log.info("params==========" + paras);
            paras = AESUtils.encrypt(baseConstant.getIvString(),baseConstant.getSecretKey(),paras);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
            params.add( "paras", paras );
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
            ResponseEntity<String> response = super.getInstance().exchange(url, HttpMethod.POST, requestEntity, String.class);                         
            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void getMessageByIdTest(){
    	try {
            String url = rootUrl + "getMessageById";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();            
            body.put("message_id", "1");      
                                  
            RequestParamVo requestParam = new RequestParamVo(header,body);
            String paras = objectMapper.writeValueAsString(requestParam);
            log.info("params==========" + paras);
            paras = AESUtils.encrypt(baseConstant.getIvString(),baseConstant.getSecretKey(),paras);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
            params.add( "paras", paras );
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
            ResponseEntity<String> response = super.getInstance().exchange(url, HttpMethod.POST, requestEntity, String.class);                         
            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
