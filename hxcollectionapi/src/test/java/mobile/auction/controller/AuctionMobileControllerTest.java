package mobile.auction.controller;

import cn.com.App;
import cn.com.base.constant.BaseConstant;
import cn.com.base.util.AESUtils;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.auction.GoodsQueryParams;
import com.fasterxml.jackson.databind.ObjectMapper;
import mobile.BaseControllerTest;
import mobile.user.controller.UserMobileControllerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @类功能说明：拍卖Controller测试
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/30 上午16:01
 * @版本：V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class AuctionMobileControllerTest extends BaseControllerTest{

    @Autowired
    private BaseConstant baseConstant;

    String rootUrl =  super.testUrl +  "api/auctionmobile/";

    private static ObjectMapper objectMapper = new ObjectMapper();

    private Logger log = LoggerFactory.getLogger(AuctionMobileControllerTest.class);

    /**
     * @description 获取大小类测试
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/3/30 16:03:18
     * @author:zhoujinbing
     */
    @Test
    public void getSizeOfClassTest()
    {
        try {
            String url = rootUrl + "getSizeOfClass";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","2","1","Iphone","ios10.1");//初始化头请 默认不校验Token

            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();
            body.put("class_type","2");

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

    /**
     * @description 获取拍卖场次测试
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/3/30 16:03:18
     * @author:zhoujinbing
     */
    @Test
    public void getSessionTest()
    {
        try {
            String url = rootUrl + "getSession";

            InitParamHeader("K1yhuJFmCHfZUPKar4k/X/6Br1nHYbNwCUJ7OQCFNgg=","2","1","Iphone","ios10.1");//初始化头请 默认不校验Token

            RequestParamVo requestParam = new RequestParamVo(header,"");
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

    /**
     * @description 发布拍卖测试
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/3/30 16:03:18
     * @author:zhoujinbing
     */
    @Test
    public void publishAcutionTest()
    {
        try {
            String url = rootUrl + "publishAcution";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","2","1","Iphone","ios10.1");//初始化头请 默认不校验Token

            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();
            body.put("goods_name","空调3");
            body.put("goods_description","隔离空调是真的好啊");
            body.put("city_code","321100");//320600  321100   321300   330100
            body.put("image_ids","e284b8b2b8c8436fb51c9fcaa5d7bd47,d724fdd72aac4e16a440a31b8401d981");
            body.put("start_price","80");
            body.put("markup_price","50");
            body.put("big_class_code","0");
            body.put("small_class_code","2");
            body.put("inventory","20");
            body.put("postage","20");
            body.put("end_time",1524552532);

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

    /**
     * @description 加载拍卖列表测试
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/3/30 16:03:18
     * @author:zhoujinbing
     */
    @Test
    public void getGoodsByTypeTest()
    {
        try {
            String url = rootUrl + "getGoodsByType";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","2","1","Iphone","ios10.1");//初始化头请 默认不校验Token

            // 业务参数
            GoodsQueryParams goodsBody = new GoodsQueryParams();
            goodsBody.setPage_num(1);
            goodsBody.setPage_size(10);
            goodsBody.setCity_code("");
            //goodsBody.setSort_type(4);//排序方式  1、默认排序   2、出价最多   3、出价最少     4、当前价最低
            //goodsBody.setBig_class_code("0");
            //goodsBody.setSmall_class_code("2");
            //goodsBody.setSearch_content("玉");
            //goodsBody.setSearch_type(7);//(1、捡漏 2、上新 3、热门 4、我发布的 5、我参与的 6、我收藏的 7、我的收藏)


            RequestParamVo requestParam = new RequestParamVo(header,goodsBody);
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

    /**
     * @description 根据ID加载拍卖详情
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/3/30 16:03:18
     * @author:zhoujinbing
     */
    @Test
    public void getGoodsDetailByIdTest()
    {
        try {
            String url = rootUrl + "getGoodsDetailById";

            InitParamHeader("K1yhuJFmCHfZUPKar4k/X/6Br1nHYbNwCUJ7OQCFNgg=","2","1","Iphone","ios10.1");//初始化头请 默认不校验Token

            // 业务参数
            Map<String,Object> requestParamMap = new HashMap<>();
            requestParamMap.put("goods_id","1e2c04f3530b46aca3e6065e66c68679");


            RequestParamVo requestParam = new RequestParamVo(header,requestParamMap);
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

    /**
     * @description 新增出价测试
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/4/3 13:47:17
     * @author:zhoujinbing
     */
    @Test
    public void addGoodsOfferRecordTest()
    {
        try {
            String url = rootUrl + "addGoodsOfferRecord";

            InitParamHeader("cuR14NGLb6UfxliRqkvzXZT5Fze8YIE+hho/B6WUtvA=","2","1","Iphone","ios10.1");//初始化头请 默认不校验Token

            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();
            body.put("goods_id","1586fdc2a7d841c0ab31273913be9ae8");//0235716ede3948ac946cbd78738532d4 1945b34cc54f4c1bb089f201cace60a7 6b0c54753b824dca9fa4e6daf8bf0079
            body.put("user_id","2");
            body.put("offer_price","1300");

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

    /**
     * @description 出价记录分页查询测试
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/4/3 13:47:17
     * @author:zhoujinbing
     */
    @Test
    public void getGoodsOfferRecordTest()
    {
        try {
            String url = rootUrl + "getGoodsOfferRecord";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","1","1","Iphone","ios10.1");//初始化头请 默认不校验Token

            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();
            body.put("goods_id","977a7c7e815c4ff686b27f146fbff49d");//0235716ede3948ac946cbd78738532d4 1945b34cc54f4c1bb089f201cace60a7 6b0c54753b824dca9fa4e6daf8bf0079
            body.put("page_num","1");
            body.put("page_size","10");

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

    /**
     * @description 评论分页查询测试
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/8 16:10:56
     * @author:zhoujinbing
     */
    @Test
    public void getAuctionCommentListTest()
    {
        try {
            String url = rootUrl + "getAuctionCommentList";

            InitParamHeader("q+gKOnfXlZWcrdiCkb3839tiAVdum8EY","2","1","Iphone","ios10.1");//初始化头请 默认不校验Token

            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();
            body.put("goods_id","b6f3d3406eb2451f9c18141afb7963d9");//0235716ede3948ac946cbd78738532d4 1945b34cc54f4c1bb089f201cace60a7 6b0c54753b824dca9fa4e6daf8bf0079
            body.put("comment_type","2");
            body.put("page_num","1");
            body.put("page_size","10");

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

    /**
     * @description 获取当前最新竞拍价
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/4/8 16:10:56
     * @author:zhoujinbing
     */
    @Test
    public void getPriceByGoodsId()
    {
        try {
            String url = rootUrl + "getPriceByGoodsId";

            InitParamHeader("cuR14NGLb6UfxliRqkvzXZT5Fze8YIE+hho/B6WUtvA=","2","1","Iphone","ios10.1");//初始化头请 默认不校验Token

            // 业务参数
            Map<String, Object> body = new HashMap<String, Object>();
            body.put("goods_id","1586fdc2a7d841c0ab31273913be9ae8");//0235716ede3948ac946cbd78738532d4 1945b34cc54f4c1bb089f201cace60a7 6b0c54753b824dca9fa4e6daf8bf0079

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
