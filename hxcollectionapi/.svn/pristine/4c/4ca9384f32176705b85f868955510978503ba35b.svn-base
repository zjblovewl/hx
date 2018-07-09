package base.job;

import mobile.BaseControllerTest;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @类功能说明：测试文件服务器上传
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/9 上午10:34
 * @版本：V1.0
 */
public class JobControllerTest extends BaseControllerTest {

    /**
     * @description 添加定时任务
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/5/3 11:23:31
     * @author:zhoujinbing
     */
    @Test
    public void addjobTest()
    {
        try {
            String url = super.baseUrl + "job/addJob";


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
            params.add( "jobClassName", "cn.com.base.job.job.HelloJob" );
            params.add( "jobGroupName", "test" );
            params.add( "cronExpression", "0/2 * * * * ? *");
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
            ResponseEntity<String> response = super.getInstance().exchange(url, HttpMethod.POST, requestEntity, String.class);
            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 暂停定时任务
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/5/3 11:23:40
     * @author:zhoujinbing
     */
    @Test
    public void pauseJobTest()
    {
        try {
            String url = super.baseUrl + "job/pauseJob";


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
            params.add( "jobClassName", "cn.com.base.job.job.HelloJob" );
            params.add( "jobGroupName", "test" );
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
            ResponseEntity<String> response = super.getInstance().exchange(url, HttpMethod.POST, requestEntity, String.class);
            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 启动定时任务
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/5/3 11:23:53
     * @author:zhoujinbing
     */
    @Test
    public void resumeJobTest()
    {
        try {
            String url = super.baseUrl + "job/resumeJob";


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
            params.add( "jobClassName", "cn.com.base.job.job.HelloJob" );
            params.add( "jobGroupName", "test" );
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
            ResponseEntity<String> response = super.getInstance().exchange(url, HttpMethod.POST, requestEntity, String.class);
            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 更新定时任务
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/5/3 11:23:31
     * @author:zhoujinbing
     */
    @Test
    public void rescheduleJobTest()
    {
        try {
            String url = super.baseUrl + "job/rescheduleJob";


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
            params.add( "jobClassName", "cn.com.base.job.job.HelloJob" );
            params.add( "jobGroupName", "test" );
            params.add( "cronExpression", "0/30 * * * * ? ");
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
            ResponseEntity<String> response = super.getInstance().exchange(url, HttpMethod.POST, requestEntity, String.class);
            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 更新定时任务
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/5/3 11:23:31
     * @author:zhoujinbing
     */
    @Test
    public void deleteJobTest()
    {
        try {
            String url = super.baseUrl + "job/deleteJob";


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
            params.add( "jobClassName", "cn.com.base.job.job.HelloJob" );
            params.add( "jobGroupName", "test" );
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
            ResponseEntity<String> response = super.getInstance().exchange(url, HttpMethod.POST, requestEntity, String.class);
            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
