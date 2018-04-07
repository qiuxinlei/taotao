package com.taotao.test;

import com.taotao.utils.FastDFSClient;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.IOException;

/**
 * @Package com.taotao.test
 * @User 12428
 * @Date 2018/3/26 21:16
 */

public class FastDFSTest {

    private String[] str = {"z_Zeulq0Zi2AL4PtAAV6ByRioPM823.jpg",
            "z_Zeulq0ZjiAVNBPAADmEvomPAg743.jpg",
            "z_Zeulq0ZlCAJig_AAAux8y0uTk916.jpg",
            "z_Zeulq0ZLiAJBrUAAQdjBxdXvg521.jpg",
            "z_Zeulq0ZluANSYzAAtP7L6piRw816.jpg",
            "z_Zeulq0ZmmAWQKRAARiYGrDLBA787.jpg",
            "z_Zeulq0ZMWAPRu8AAA0T4IG9WI844.jpg",
            "z_Zeulq0ZnSAMx86AAJH8SzWq98219.jpg",
            "z_Zeulq0ZNyAO6pDAAV6ByRioPM520.jpg",
            "z_Zeulq0Zo-AFT2TAAKHDdDA6uA067.jpg",
            "z_Zeulq0ZOWAKRV0AATp61u0P-o828.jpg",
            "z_Zeulq0ZoWAKYiUAARVMIhdDYc682.jpg",
            "z_Zeulq457-AfhMAAAOQjE4rFrQ672.jpg",
            "z_Zeulq457CAF55JAAK7WpQ3KU0514.jpg",
            "z_Zeulq45sCAINOBAAK7WpQ3KU0462.jpg",
            "z_Zeulq45suAO1mzAAaWP0J9tRA785.jpg",
            "z_Zeulq46e6ARQ7cAAAux8y0uTk076.jpg",
            "z_Zeulq46eSAFjTTAAK7WpQ3KU0475.jpg",
            "z_Zeulq4mNqAWUEuAABLfNp2Vmw331_big.jpg",
            "z_Zeulq4mNqAWUEuAABLfNp2Vmw331_big.jpg-m",
            "z_Zeulq4mNqAWUEuAABLfNp2Vmw331.jpg",
            "z_Zeulq4mNqAWUEuAABLfNp2Vmw331.jpg-m",
            "z_Zeulq4mXaAalr3AAtP7L6piRw583.jpg",
            "z_Zeulq4mXmAbFcwAAEMGA5_tHk112.png",
            "z_Zeulq4s86AMlIjAAtP7L6piRw167.jpg",
            "z_Zeulq4s9CAbw2IAAEMGA5_tHk713.png",
            "z_Zeulq4tN-AV-S4AAtP7L6piRw660.jpg",
            "z_Zeulq4tOGAZNLAAAEMGA5_tHk146.png",
            "z_ZeulqybRyAeULXAABLfNp2Vmw343_big.jpg",
            "z_ZeulqybRyAeULXAABLfNp2Vmw343_big.jpg-m",
            "z_ZeulqybRyAeULXAABLfNp2Vmw343.jpg",
            "z_ZeulqybRyAeULXAABLfNp2Vmw343.jpg-m",
            "z_ZeulqycPWADONBAABLfNp2Vmw543_big.jpg",
            "z_ZeulqycPWADONBAABLfNp2Vmw543_big.jpg-m",
            "z_ZeulqycPWADONBAABLfNp2Vmw543.jpg",
            "z_ZeulqycPWADONBAABLfNp2Vmw543.jpg-m",
            "z_ZeulqzIN-AfLlkAABLfNp2Vmw552_big.jpg",
            "z_ZeulqzIN-AfLlkAABLfNp2Vmw552_big.jpg-m",
            "z_ZeulqzIN-AfLlkAABLfNp2Vmw552.jpg",
            "z_ZeulqzIN-AfLlkAABLfNp2Vmw552.jpg-m",
            "z_ZeulqzMQ6AZp6MAAEMGA5_tHk270.png"};
    @Test
    public void deleteTest() throws IOException, MyException {
        ClientGlobal.init("E:\\Other\\IDEAWorkSpace\\taotao\\taotao-common\\src\\main\\resources\\properties\\client.conf");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        for ( String string : str) {
            int i = storageClient1.delete_file("group1","M00/00/00/"+string);
            System.out.println(i);
        }

    }
}
