package baseCode;


import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.testng.SkipException;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListeners;

public class ApiRequest extends ExtentListeners {

	//private static FileInputStream request;
	public static String ApiRequest1(String Trgtpath,String destination) throws SAXException, IOException {

	
		String trgt = null;
		//FileReader source = new FileReader(srcpath);
	//	FileReader target = new FileReader(targtpath);
		try {
			//String request = Files.readString(Paths.get(Trgtpath));
			String request ="hello";
			
			test.log(Status.INFO, "The API Request File is read.File name:" + Trgtpath);

			//String request = Files.readString(Paths.get(Trgtpath));
			//request = new FileInputStream(Trgtpath);
				//System.out.println(request);
			//String request = "<soapenv:Envelope xmlns:soapenv=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\" xmlns:hs=\\\"http://www.holidaywebservice.com/HolidayService_v2/\\\"><soapenv:Body><hs:GetHolidaysForMonth><hs:year>2018</hs:year><hs:countryCode>UnitedStates</hs:countryCode><hs:month>11</hs:month></hs:GetHolidaysForMonth></soapenv:Body></soapenv:Envelope>";
              
				URL url = new URL("https://mockbin.com/bin/af56a9f6-45ce-401b-baa9-0a398263e463/view");
				//http://mockbin.com/request?foo=bar&foo=baz
				//https://mockbin.com/bin/79252c8f-8b6d-4cb3-a788-fdd08f6c7eee/view
				//http://mockbin.com/bin/471987de-5663-4280-a354-ac0784a93958/view
				//https://mockbin.com/bin/b16f7baf-80ca-4862-99ce-8d4bf1569ae9/view
				//http://jsonplaceholder.typicode.com/albums
				//https://mockbin.com/bin/c32de56f-bb57-4c9f-b3a3-86a0a6f0bdea/view //timeoutapi
				//http://mockbin.com/bin/47d816f0-a5cb-4e8e-a9b7-70091c1e0903/view
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				
				// Set timeout as per needs
				connection.setConnectTimeout(15000);
				connection.setReadTimeout(15000); 

			
				// Set DoOutput to true if you want to use URLConnection for output.
				// Default is false
				
				//System.out.println(" The time out time of connection is : "+connection.getConnectTimeout()); 
				//test.log(Status.SKIP, " The time out time of API URL connection is : "+connection.getConnectTimeout());
				connection.setDoOutput(true);

				connection.setUseCaches(true);
				connection.setRequestMethod("POST");

				// Set Headers
				connection.setRequestProperty("Accept", "application/xml");
				connection.setRequestProperty("Content-Type", "application/xml");
				
				// Write XML
			OutputStream outputStream = connection.getOutputStream();
				byte[] b = request.getBytes("UTF-8");
				outputStream.write(b);
				outputStream.flush();
				outputStream.close();

				
				String responseStatus = connection.getResponseMessage();
				System.out.println(responseStatus);
				test.log(Status.INFO, " Api Response status : "+responseStatus);	 
			
				// Read XML
				InputStream inputStream = connection.getInputStream();
				byte[] res = new byte[2048];
				int i = 0;
				StringBuilder response = new StringBuilder();
				while ((i = inputStream.read(res)) != -1) {
					response.append(new String(res, 0, i));
				}
				
				inputStream.close();

				//System.out.println("Response= " + response.toString());
	          
				//public void whenWriteStringUsingBufferedWritter_thenCorrect() 
					//	  throws IOException {
				
			/*	creating datetime directory
				String dateTime = new DateTime().toString("dd-MM-yy HH:mm:ss");
				File f = new File("C:\\tmp\\" + dateTime);
				f.mkdir();
				*/
				final String directory = LocalDate.now().toString();
				File index = new File(System.getProperty("user.dir")+"/src/test/resources/"+directory);
				if (!index.exists())
				{
				   //  index.mkdir();
				     Files.createDirectory(Paths.get(System.getProperty("user.dir")+"/src/test/resources/", directory));
				     System.out.println("Dir Not present. Creating new one!");
				}else
				{
				
				System.out.println("folder already created successfully");
				}
				
				
				  trgt= System.getProperty("user.dir")+"/src/test/resources/"+directory+"/"+destination +"."+"xml";
				try {
					
						    String str = response.toString();
						
						    BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/src/test/resources/"+directory+"/"+destination +"."+"xml"));
						    writer.write(str);
						    
						    writer.close();
						   
				}
				catch (Exception e)
				{
	                 //System.out.println(e.getMessage());
	                 test.log(Status.SKIP, "API Response is not saved: " + e.getMessage());
	     			e.printStackTrace();
				}
				return trgt;
				
                
               
		} catch (MalformedURLException E2) {
			test.log(Status.SKIP, "HTTP Error: " + E2.getMessage());
			E2.printStackTrace();
			throw new SkipException("HTTP error");

		}catch (FileNotFoundException|NoSuchFileException e) {
			test.log(Status.SKIP, "API Request File Not Found: " + Trgtpath);
			
			e.printStackTrace();
			throw new SkipException("API File Not Found");

		} catch (NullPointerException   n2) {
			test.log(Status.SKIP, "The Target filepath given is NULL: " + Trgtpath);
			test.log(Status.SKIP, "Error: " + n2.getMessage());
			n2.printStackTrace();
			throw new SkipException("Null Pionter Exception");

		}catch (Exception  exception) {
			
			test.log(Status.SKIP, "Error: " + exception.getMessage());
			exception.printStackTrace();
			throw new SkipException("exception");
		
		

		}
	}
	
}
