package pc.transaction.controller;

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
 * @创建时间：2018年4月16日 下午1:50:34 
 * @版本：V1.0  
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class TransactionPcControllerTest extends BaseControllerTest{
	@Autowired
    private BaseConstant baseConstant;

    String rootUrl =  super.testUrl +  "api/transactionpc/";

    private static ObjectMapper objectMapper = new ObjectMapper();
    
    private Logger log = LoggerFactory.getLogger(TransactionPcControllerTest.class);
    
    @Test
    public void getHomePageAdvertisementRecordsTest()
    {
        try {
            String url = rootUrl + "getHomePageAdvertisementRecords";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();                
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
    public void getRecommendGoodsRecordsTest(){
    	try {
            String url = rootUrl + "getRecommendGoodsRecords";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();                        
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
    public void getRecommendSellerRecordsTest(){
    	try {
            String url = rootUrl + "getRecommendSellerRecords";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();                        
            body.put("page_num", 1);
            body.put("page_size", 2);            
                                  
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
    public void getGoodsRecordsTest()
    {
    	 try {
             String url = rootUrl + "getGoodsRecords";

             InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
             	
             // 业务参数
             Map<String, Object> body = new HashMap<String, Object>();
             body.put("search_content", "");
             body.put("sort_type","1");
             body.put("big_class_code", "");
             body.put("small_class_code", "");  
             body.put("begin_price", 10);
             body.put("end_price", 100);
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
    public void getGoodsInfoByIdTest()
    {
    	 try {
             String url = rootUrl + "getGoodsInfoById";

             InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
             	
             // 业务参数
             Map<String, Object> body = new HashMap<String, Object>();
             body.put("goods_id", "11fb2cba7c0c4c28857cab99a0196de4");
                                   
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
    public void getMyGoodsRecordsTest(){
    	try {
            String url = rootUrl + "getMyGoodsRecords";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();   
            body.put("user_id","1");
            body.put("service_type","2");
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
    public void getMyOtherGoodsRecordsTest(){
    	try {
            String url = rootUrl + "getMyOtherGoodsRecords";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();   
            body.put("user_id","ba752d5776c44e07b4371025f96f7e2c");
            body.put("goods_id","a0cee7306bdf4d53b73633e0ff3be461");
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
    public void publishGoodsTest()
    {
        try {
            String url = rootUrl + "publishGoods";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();
//            body.put("uuid", CommonUtils.getUUID());
            body.put("big_class_code","111");
            body.put("small_class_code", "222");
            body.put("user_id", "92b9880e552742f4a47d92469e4764db");
            body.put("goods_name", "测试藏品");
            body.put("goods_description", "测试藏品测试藏品测试藏品");
            body.put("inventory", 1);
            body.put("price", 100);
            body.put("postage", 1);
            body.put("publish_customer", "乾隆");
            body.put("city_code", "320100");
            body.put("image_ids", "e284b8b2b8c8436fb51c9fcaa5d7bd47,d724fdd72aac4e16a440a31b8401d981");            
            
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
            body.put("message_id", "2aa482657c48407aac2c807df18227ad");      
                                  
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
    public void getMyTrackRecordsTest(){
    	try {
            String url = rootUrl + "getMyTrackRecords";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();            
            body.put("user_id","2");            
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
    public void saveGoodsCollectionTest(){
    	 try {
             String url = rootUrl + "saveGoodsCollection";

             InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
             	
             // 业务参数
             Map<String, Object> body = new HashMap<String, Object>();          
             body.put("user_id", "1");
             body.put("goods_id", "35f66a3205204856a446f2d70d0fa7ba");
             body.put("goods_type", "1");     
                                   
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
    public void getMyCollectionRecordsTest(){
    	try {
            String url = rootUrl + "getMyCollectionRecords";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();   
            body.put("user_id","2");
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
}
