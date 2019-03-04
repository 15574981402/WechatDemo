package cn.yckj.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtils {
	private static final Logger logger  =LoggerFactory.getLogger(IOUtils.class);
    private IOUtils() {
        // no instances of this class
    }
    /**
    复制输入流input数据到输出流out,并且还未关闭资源close()
    * @see org.apache.poi.util.IOUtils#copy( InputStream, OutputStream)
    */
    public static void copy(InputStream input, OutputStream out) throws IOException {
        byte[] buff = new byte[4096]; // 4*1024  4M
        int count;
        while ((count = input.read(buff)) != -1) {
            if (count > 0) {
                out.write(buff, 0, count);
            }
        }
    }
    
    /**
     * @param closeable
     *            resource to close 所有能调用close方法的类都会implements  java.io.Closeable
     * @see org.apache.poi.util.IOUtils#closeQuietly( final Closeable ) 
     */
    public static void closeQuietly( final Closeable closeable ) {
        // no need to log a NullPointerException here
        if(closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch ( Exception exc ) {
            logger.debug("Unable to close resource: " + exc,exc );
        }
    }
}
