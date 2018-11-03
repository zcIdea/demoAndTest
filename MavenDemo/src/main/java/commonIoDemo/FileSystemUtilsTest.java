package commonIoDemo;

import java.io.IOException;

import org.apache.commons.io.FileSystemUtils;
import org.junit.Test;

/**
 *
 * 当前，只有一个用于读取硬盘空余空间的方法可用
 *
 */

public class FileSystemUtilsTest {
	
	/** 
     * 获取磁盘空余空间 
     * @throws IOException 
     */  
    @SuppressWarnings("deprecation")  
    @Test  
    public void testFreeSpace() throws IOException {  
        // 以字节为单位  
        System.out.println(FileSystemUtils.freeSpace("c:\\") / 1024 / 1024 / 1024);  
        System.out.println(FileSystemUtils.freeSpace("d:\\") / 1024 / 1024 / 1024);  
        // 以k为单位  
        System.out.println(FileSystemUtils.freeSpaceKb("e:\\") / 1024 / 1024);  
        System.out.println(FileSystemUtils.freeSpaceKb("f:\\") / 1024 / 1024);  
          
    } 

}
