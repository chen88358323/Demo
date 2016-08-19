package  org.util.ocr.tesseract;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegeitTest {
	private static final Logger ll = LoggerFactory.getLogger(RegeitTest.class);

	public static void main(String[] args) {

		testReadGIF("D:\\temp\\del\\cc.gif");
	}
	public static String testReadGIF(String gifPath){
		String res=null;
		File f=new File(gifPath);
		try {
			InputStream instream = new FileInputStream(f);
			BufferedImage bi = ImageIO.read(instream);
			instream.close();
			res = ImageRead.read(bi, 0);
			if(StringUtils.isNotBlank(res)){
				res=res.replaceAll(" ","");
			}
			ll.info(res + ":====" +  "   ");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return res;
		}
	}
	private void test1(){
		HttpClient httpclient = new DefaultHttpClient();
		HttpUriRequest getMethod = new HttpGet(
				"http://user.haier.com/ids/cn/abc.code");

		for(int i=1;i<=10;i++){
			try {
				String yzm = "";
				HttpResponse res = httpclient.execute(getMethod);
				HttpEntity entity = res.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					BufferedImage bi = ImageIO.read(instream);
					instream.close();
					/**************************************************************************************/
					yzm = ImageRead.read(bi,i);
					/**************************************************************************************/
				}
				System.out.println(yzm+":===="+i+"   ");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
