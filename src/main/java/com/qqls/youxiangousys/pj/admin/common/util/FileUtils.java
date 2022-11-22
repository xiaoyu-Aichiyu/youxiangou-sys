package com.qqls.youxiangousys.pj.admin.common.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtils {

    private static final String TX_DOMAIN = "https://yxg-1312422386.cos.ap-guangzhou.myqcloud.com/";

    /**
     * 上传图片
     * @param file
     * @return 文件路径 写到数据库里的img字段里
     */
    public static String uploadImg(MultipartFile file) {
        //得到文件类型
        String contentType = file.getContentType();
        //判断文件类型是否合格
        Assert.isEmpty(contentType.indexOf("image") == -1, "请上传图片！");
        //上传到腾讯云的路径以及文件名
        String key = getNewFileName(file, "image/");
        //创建临时文件
        File localFile = null;
        try {
            localFile = transferToFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
        String bucketName = "yxg-1312422386";
        //上传文件
        String uploadPath = upload(bucketName, key, localFile);
        return uploadPath;
    }

    /**
     * 上传表格
     * @param file
     * @return 文件路径 解析excel
     */
    public static String uploadExcel(MultipartFile file) {
        //得到文件类型
        String contentType = file.getContentType();
        Assert.isEmpty(contentType.indexOf("excel") == -1, "请上传excel的表格！");
        //上传到腾讯云的路径以及文件名
        String key = getNewFileName(file, "excel/");
        //创建临时文件
        File localFile = null;
        try {
            localFile = transferToFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
        String bucketName = "yxg-1312422386";
        //上传文件
        String uploadPath = upload(bucketName, key, localFile);
        return uploadPath;
    }

    /**
     * 上传到腾讯云的路径以及文件名 fileType/fileName
     * @param file
     * @param fileType
     * @return
     */
    public static String getNewFileName(MultipartFile file, String fileType) {
        //得到文件名
        String oldFileName = file.getOriginalFilename();
        //得到后缀
        String ext = oldFileName.substring(oldFileName.lastIndexOf("."));
        //得到一个随机名字
        String uuid = UUID.randomUUID().toString().replace("-","");
        //生成新的文件名
        String fileName = fileType + uuid + ext;
        return fileName;
    }

    /**
     * 用缓冲区来实现这个转换, 即创建临时文件
     * 使用 MultipartFile.transferTo()
     *
     * @param multipartFile
     * @return
     */
    private static File transferToFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String prefix = originalFilename.split("\\.")[0];
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        File file = File.createTempFile(prefix, suffix);
        multipartFile.transferTo(file);
        return file;
    }

    public static COSClient createCOSClient() {
        //SecretId和SecretKey
        String secretId = "AKIDEH4brqY0EBr78tOefPs4zg9ML4XjeKip";
        String secretKey = "rSmpb6LO9qJVQ4XkhW8VcT4PTGm33mEQ";
        BasicCOSCredentials cred = new BasicCOSCredentials(secretId, secretKey);

        // ClientConfig 中包含了后续请求 COS 的客户端设置：
        ClientConfig clientConfig = new ClientConfig();

        // 设置 bucket 的地域
        clientConfig.setRegion(new Region("ap-guangzhou"));

        // 设置请求协议, http 或者 https
        clientConfig.setHttpProtocol(HttpProtocol.https);

        // 生成 cos 客户端。
        return new COSClient(cred, clientConfig);
    }

    /**
     * 上传文件
     * @param bucketName 腾讯云的存储桶
     * @param key 文件上传的路径+文件名
     * @param localFile 临时文件区
     * @return
     */
    public static String upload(String bucketName, String key, File localFile) {
        //COS客户端对象
        COSClient cosClient = createCOSClient();
        //上传
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        return TX_DOMAIN + key;
    }

}
