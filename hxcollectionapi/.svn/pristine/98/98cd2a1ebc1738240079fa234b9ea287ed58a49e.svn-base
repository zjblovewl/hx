package mobile.transaction.controller;

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
 * @创建时间：2018年4月2日 上午10:18:15 
 * @版本：V1.0  
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class TransactionMobileControllerTest extends BaseControllerTest{
	@Autowired
    private BaseConstant baseConstant;

    String rootUrl =  super.baseUrl +  "api/transactionmobile/";

    private static ObjectMapper objectMapper = new ObjectMapper();
    
    private Logger log = LoggerFactory.getLogger(TransactionMobileControllerTest.class);
    
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
            body.put("user_id", "9d1b902aa3514b47b99ce2c0ada75800");
            body.put("goods_name", "虎符");
            body.put("goods_description", "邮票");
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
             body.put("goods_id", "bfc0d9b0c2974b3da130a4d45aa03b80");
                                   
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
    public void saveGoodsCommentTest()
    {
    	 try {
             String url = rootUrl + "saveGoodsComment";

             InitParamHeader("j/0kMt1ssnmIiUC/Q8vx3GZdSR3DVD8Zu+zB6FBrF1U=","2","1","Iphone","ios10.1");//初始化头请 默认不校验Token
             	
             // 业务参数
             Map<String, Object> body = new HashMap<String, Object>();
             body.put("user_id", "2");
             body.put("goods_id", "b6f3d3406eb2451f9c18141afb7963d9");
             body.put("reply_user_id", "3");//251153d3048a46bebf23a0b089d69235
             body.put("comment_content", "很好aaafdafda！");
             body.put("goods_type", "2");
             body.put("comment_type","1");
                                   
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
    public void getHomePageRecordsTest(){
    	 try {
             String url = rootUrl + "getHomePageRecords";

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
    public void deleteGoodsByIdTest(){
    	try {
            String url = rootUrl + "deleteGoodsById";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();   
            body.put("goods_id","14a0f53ae07246f29bb819fc3ae9f871");                  
                                  
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
    public void updateGoodsStatusByIdTest(){
    	try {
            String url = rootUrl + "updateGoodsStatusById";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();   
            body.put("goods_id","14a0f53ae07246f29bb819fc3ae9f871");
            //operation_type 1:上架 2：下架
            body.put("operation_type","1");               
                                  
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
    
    @Test
    public void deleteCollectGoodsTest(){
    	try {
            String url = rootUrl + "deleteCollectGoods";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();   
            body.put("goods_id","2");         
                                  
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
