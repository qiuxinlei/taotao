package com.taotao.fastdfs;

import com.taotao.utils.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;


public class FastDFSTest {

    @Test
    public void testUpload() throws Exception{
        //1、把FastDFS提供的jar包添加到工程中
        //2、初始化全局配置。加载一个配置文件
        ClientGlobal.init("properties/client.conf");
        //3、创建一个TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //4、创建一个TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //5、声明一个StorageServer对象，null
        StorageServer storageServer = null;
        //6、获得StorageClient对象
        StorageClient storageClient = new StorageClient();
        //7、直接调用StorageClient对象方法上传文件即可
        String[] strings = storageClient.upload_file("C:\\Users\\12428\\Pictures\\Android\\wait1.png", "png", null);
        for (String string:strings) {
            System.out.println(string);
        }
    }

    @Test
    public void testFastDFSClient()throws Exception {
        FastDFSClient client = new FastDFSClient("classpath:src/main/resources/properties/client.conf");
        String jpg = client.uploadFile("C:\\Users\\12428\\Pictures\\Camera Roll\\1510655057698.jpg", "jpg");
        System.out.println(jpg);

    }
}
