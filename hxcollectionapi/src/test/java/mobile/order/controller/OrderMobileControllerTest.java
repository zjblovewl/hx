package mobile.order.controller;

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
public class OrderMobileControllerTest extends BaseControllerTest{
	@Autowired
    private BaseConstant baseConstant;

    String rootUrl =  super.baseUrl +  "api/ordermobile/";

    private static ObjectMapper objectMapper = new ObjectMapper();
    
    private Logger log = LoggerFactory.getLogger(OrderMobileControllerTest.class);
    
    @Test
    public void saveOrderTest()
    {
        try {
            String url = rootUrl + "saveOrder";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();
            body.put("goods_id", "d7aadf6108f9435e82842b332ac708e9");
            body.put("amount", 1);                             
            body.put("buyer_id","96b98347b3f94999b3c530be5ad2e54e");
            body.put("buyer_name", "admin");
            body.put("seller_id", "ebc76960137f414194700969f9085d6a");
            body.put("seller_name", "刘桂1234");
            body.put("order_type", "1");
            body.put("pay_money", 100);
            body.put("postage", 10);
            body.put("pay_method", "1");
            body.put("receive_address", "江苏南京鼓楼区联创大厦B座8F");
            body.put("consignee", "陈晨");
            body.put("contact_information", "15895889836");
            body.put("remark", "哈哈");
                                                                      
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
    public void getSellOrderRecordsTest()
    {
        try {
            String url = rootUrl + "getSellOrderRecords";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();
            body.put("page_num", 1);
            body.put("page_size", 10);
            body.put("user_id", "ebc76960137f414194700969f9085d6a");
           
                                                                      
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
    public void getBuyOrderRecordsTest()
    {
        try {
            String url = rootUrl + "getBuyOrderRecords";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();
            body.put("page_num", 1);
            body.put("page_size", 10);
            body.put("user_id", "96b98347b3f94999b3c530be5ad2e54e");
           
                                                                      
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
    public void getMySellOrderRecordsTest()
    {
        try {
            String url = rootUrl + "getMySellOrderRecords";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();
            body.put("page_num", 1);
            body.put("page_size", 10);
            body.put("order_status", "1");
            body.put("user_id", "ebc76960137f414194700969f9085d6a");
           
                                                                      
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
    public void getMyBuyOrderRecordsTest()
    {
        try {
            String url = rootUrl + "getMyBuyOrderRecords";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();
            body.put("page_num", 1);
            body.put("page_size", 10);
            body.put("order_status", "1");
            body.put("user_id", "96b98347b3f94999b3c530be5ad2e54e");
           
                                                                      
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
    public void getOrderInfoByIdTest(){
    	 try {
             String url = rootUrl + "getOrderInfoById";

             InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
             	
             // 业务参数
             Map<String, Object> body = new HashMap<String, Object>();
             
             body.put("order_id", "44512d177c384613b84dcee162db38b9");
            
                                                                       
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
    public void sendOutGoodsTest(){
    	 try {
             String url = rootUrl + "sendOutGoods";

             InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
             	
             // 业务参数
             Map<String, Object> body = new HashMap<String, Object>();
             
             body.put("order_id", "44512d177c384613b84dcee162db38b9");
             body.put("logistics_company", "顺丰速运");
             body.put("logistics_code", "123456789");
                                                                       
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
    public void saveOrderEvaluateTest(){
    	try {
            String url = rootUrl + "saveOrderEvaluate";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();
            
            body.put("user_type", "1");//1：买家 2：卖家
            body.put("user_id", "ebc76960137f414194700969f9085d6a");
            body.put("order_id", "06630ca56a334a2c91a5992d327f18ae");
            body.put("evaluate_level", "1");//1：好评 2：中评 3：差评
            body.put("evaluate_content", "很好");            
                                                                      
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
    public void cancelOrderTest(){
    	try {
            String url = rootUrl + "cancelOrder";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();                       
            body.put("order_id", "06630ca56a334a2c91a5992d327f18ae");            
                                                                      
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
    public void confirmCollectGoodsTest(){
    	try {
            String url = rootUrl + "confirmCollectGoods";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();                       
            body.put("order_id", "06630ca56a334a2c91a5992d327f18ae");    
            body.put("user_id", "ebc76960137f414194700969f9085d6a");
            body.put("wallet_pwd", 1234567);
                                                                      
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
    public void applyRefundTest(){
    	try {
            String url = rootUrl + "applyRefund";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();                       
            body.put("order_id", "06630ca56a334a2c91a5992d327f18ae");        
            body.put("refund_reason", "不想要了");
                                                                      
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
    public void agreeRefundTest(){
    	try {
            String url = rootUrl + "agreeRefund";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
            	
            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();                       
            body.put("order_id", "06630ca56a334a2c91a5992d327f18ae");                    
                                                                      
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
    public void getPrepaymentCodeByIdTest(){
    	 try {
             String url = super.baseUrl +  "api/chargeOrders/" + "getPrepaymentCodeById";

             InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token
             	
             // 业务参数
             Map<String, Object> body = new HashMap<String, Object>();
             
             body.put("order_id", "44512d177c384613b84dcee162db38b9");
             body.put("channel", "alipay");
                                                                       
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
