package com.taotao.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;


public class FastDFSClient {

    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageClient1 storageClient1 = null;
    private StorageServer storageServer = null;

    public FastDFSClient(String conf) throws Exception {
        if(conf.contains("classpath:")){
            conf = conf.replace("classpath:","");
        }
        ClientGlobal.init(conf);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = trackerClient.getStoreStorage(trackerServer);
        storageClient1 = new StorageClient1(trackerServer,storageServer);
    }

    /*
    * 上传文件方法
    * */
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws IOException, MyException {
        String result = storageClient1.upload_file1(fileName,extName,metas);
        return result;
    }
    public String uploadFile(String fileName) throws IOException, MyException {
        return uploadFile(fileName,null,null);
    }
    public String uploadFile(String fileName,String extName) throws IOException, MyException {
        return uploadFile(fileName,extName,null);
    }
    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] meta_list) throws IOException, MyException {
        String result = storageClient1.upload_file1(fileContent,extName,meta_list);
        return result;
    }
    public String uploadFile(byte[] fileContent, String extName) throws IOException, MyException {
        return uploadFile(fileContent,extName,null);
    }
    public String uploadFile(byte[] fileContent) throws IOException, MyException {
        return uploadFile(fileContent,null,null);
    }

}
