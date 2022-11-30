package org.akboom.blogapi.util;

import com.alibaba.fastjson.JSON;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname QiniuUtils
 * @Description 七牛云对象存储服务
 * @Author AoLinChen
 */
@Component
public class QiniuUtils {


    public static String url;
    private static String accessKey;

    private static String accessSecretKey;

    @Value("${qiniu.testDomain}")
    public void setUrl(String url) {
        QiniuUtils.url = url;
    }

    @Value("${qiniu.accessKey}")
    public void setAccessKey(String accessKey) {
        QiniuUtils.accessKey = accessKey;
    }

    @Value("${qiniu.secretKey}")
    public void setAccessSecretKey(String accessSecretKey) {
        QiniuUtils.accessSecretKey = accessSecretKey;
    }

    public static boolean upload(MultipartFile file, String fileName) {

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String bucket = "boomclub-static";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, accessSecretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(uploadBytes, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
