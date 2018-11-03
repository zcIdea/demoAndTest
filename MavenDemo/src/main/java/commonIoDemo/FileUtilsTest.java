package commonIoDemo;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.comparator.PathFileComparator;
import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.junit.Test;

/**
 * commons-io是一款处理io流的工具，封装了很多处理io流和文件的方法，可以大大简化我们处理io流和操作文件的代码。
 * 从common-io的官方使用文档可以看出，它主要分为工具类、尾端类、行迭代器、文件过滤器、文件比较器和扩展流。
 *
 */

public class FileUtilsTest {

	private String basePath = "E:\\test\\file\\";  
	
	/** 
     * 拷贝文件 
     * @throws IOException 
     */  
    @Test  
    public void testCopy() throws IOException {  
        File srcFile = new File(basePath + "a.txt");  
        File destFile = new File(basePath + "b.txt");  
        FileUtils.copyFile(srcFile, destFile);
    }
    
    /** 
     * 删除文件 
     * @throws IOException 
     */  
    @Test  
    public void testDelete() throws IOException{  
        File delFile = new File(basePath + "b.txt"); 
        FileUtils.forceDelete(delFile);
//        FileUtils.forceMkdir(delFile);
    }  
    
    /** 
     * 比较文件内容 
     * @throws IOException 
     */  
    @Test  
    public void testCompareFile() throws IOException{  
        File srcFile = new File(basePath + "a.txt");  
        File destFile = new File(basePath + "b.txt");  
        boolean result = FileUtils.contentEquals(srcFile, destFile);  
        System.out.println(result);  
    }
    
    /** 
     * 移动文件 
     * @throws IOException 
     */  
    @Test  
    public void testMoveFile() throws IOException{  
        File srcFile = new File(basePath + "b.txt");
        File destDir = new File(basePath + "move");  
        FileUtils.moveFileToDirectory(srcFile, destDir, true);
    }
    
    
    /** 
     * 读取文件内容 
     * @throws IOException 
     */  
    @Test   
    public void testRead() throws IOException{  
        File srcFile = new File(basePath + "a.txt");  
        String content = FileUtils.readFileToString(srcFile,"GBK");  
        List<String> contents = FileUtils.readLines(srcFile,"GBK");  
        System.out.println(content);  
        System.out.println("******************");  
        for (String string : contents) {  
            System.out.println(string);  
        }  
    } 
    
    /** 
     * 写入文件内容 
     * @throws IOException 
     */  
    @Test  
    public void testWrite() throws IOException{  
        File srcFile = new File(basePath + "a.txt");  
        FileUtils.writeStringToFile(srcFile, "\nyes文件","GBK", true);  
    } 
    
    /** 
     * 测试行迭代器 
     * @throws IOException 
     */  
    @Test  
    public void testIterator() throws IOException{  
        File file = new File(basePath + "a.txt");  
        LineIterator li = FileUtils.lineIterator(file,"GBK");  
        while(li.hasNext()){  
            System.out.println(li.nextLine());  
        }  
        LineIterator.closeQuietly(li);  
    }  
    
    /** 
     * 空内容文件过滤器 
     * @throws IOException 
     */  
    @Test  
    public void testEmptyFileFilter() throws IOException{  
        File dir = new File(basePath);  
        String[] files = dir.list(EmptyFileFilter.NOT_EMPTY);  
//        String[] files = dir.list(EmptyFileFilter.EMPTY);  
        for (String file : files) {  
            System.out.println(file);  
        }  
    } 
    
    /** 
     * 文件名称后缀过滤器 
     * @throws IOException 
     */  
    @Test  
    public void testSuffixFileFilter() throws IOException{  
        File dir = new File(basePath);  
        String[] files = dir.list(new SuffixFileFilter("a.txt"));  
        for (String file : files) {  
            System.out.println(file);  
        }  
    } 
    
    /** 
     * 文件名称比较器 
     * @throws IOException 
     */  
    @Test  
    public void testNameFileComparator() throws IOException {  
        File f1 = new File(basePath + "a.txt");  
        File f2 = new File(basePath + "c.txt");  
        int result = NameFileComparator.NAME_COMPARATOR.compare(f1, f2);  
        System.out.println(result);  
    } 
    
    /** 
     * 文件路径比较器 
     * @throws IOException 
     */  
    @Test  
    public void testPathFileComparator() throws IOException {  
        File f1 = new File(basePath + "a.txt");  
        File f2 = new File(basePath + "c.txt");  
        int result = PathFileComparator.PATH_COMPARATOR.compare(f1, f2);  
        System.out.println(result);  
    } 
    
    
    public static void main(String[] args) {
    	Date date=new Date();
		System.out.println(date);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sDate=sdf.format(date);
		System.out.println(sDate);
		
		try {
			Date d=sdf.parse(sDate);
			System.out.println(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}
    
    
    
}
