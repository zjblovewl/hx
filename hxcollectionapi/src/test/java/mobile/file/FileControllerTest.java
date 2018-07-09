package mobile.file;

import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.auction.GoodsQueryParams;
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
public class FileControllerTest extends BaseControllerTest {

    @Test
    public void updateFileTest()
    {
        try {
            String url = "http://192.168.50.215:8081/fileapi/fileController/batch/batchUpload";


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
            FileSystemResource resource = new FileSystemResource("D:\\壁纸\\053bd773b8c5bad5308c3fd07b64c2ae.jpg");
            params.add( "dictionary_name", "test" );
            params.add( "is_source", "test" );
            params.add( "files", resource);
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
            ResponseEntity<String> response = super.getInstance().exchange(url, HttpMethod.POST, requestEntity, String.class);
            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
