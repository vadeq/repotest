package zws.service.file.depot;

////import zws.util.{}//Logwriter;
import zws.util.UniqueIdMaker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

public class FileDepot extends HttpServlet {
 
  /**
   * 
   */
  private static final long serialVersionUID = -5206738806758754056L;

  private static final String REPOSITORY_DIR_NAME = "Repository"; 
  private static final String SLASH = "/";
  private static final String EMPTY_STR = "";
  
  private static String REPOSITORY_DIR_PATH;
  private static String SERVLET_CONTEXT_NAME;
  private static String FILE_URL_PROTOCOL = "http://";
  private static String hostname;

  
  public void init()
    throws ServletException
  {
    REPOSITORY_DIR_PATH = this.getServletContext().getRealPath(SLASH)+REPOSITORY_DIR_NAME;
    SERVLET_CONTEXT_NAME = this.getServletContext().getServletContextName();
         
    //get  hostname
    hostname = "UnknownHost";
    try {
      InetAddress addr = InetAddress.getLocalHost();
      
      // Get hostname
      hostname = addr.getHostName();
     } catch (UnknownHostException e) {
       hostname = "UnknownHost";       
    }     
  }

 

  /**
   * doPost():
   * 
   * Uploads (extracts) multipart/form-data files when a client ships
   * the files down via a multipart/form-data doPost() request
   */
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
  throws ServletException, IOException
  {
    System.out.print(new java.sql.Timestamp(System.currentTimeMillis())+" ");    
    {}//Logwriter.printOnConsole("Enter FileDepot.doPost");    
        
    StringBuffer respBuf = new StringBuffer();
    String[] filesUrls;
    try {
      filesUrls = performUpload(request);
    } catch (Exception e) {
      throw new ServletException(e);
    }
    PrintWriter out = response.getWriter();
 
    int i=0;
    respBuf.append(filesUrls[i]); 
    
    if (filesUrls.length > 1) {
      respBuf.append(";");      
      for(i=1; i<filesUrls.length; i++) {
        respBuf.append(filesUrls[i] + ";");  
      }
    }
    respBuf.toString().trim();
    {}//Logwriter.printOnConsole("URL(s) of uploaded file(s) are: " + respBuf.toString().trim());   
    
    out.print(respBuf.toString().trim());        
    out.flush(); out.close();
    
    System.out.print(new java.sql.Timestamp(System.currentTimeMillis())+" ");    
    {}//Logwriter.printOnConsole("Exit FileDepot.doPost");    
  }

  
  /**
   * doGet():
   * 
   * The default implementation of doGet is sufficient to download files
   */

  
  
  
  /**
   * doDelete():
   * 
   * The web server refuses to deliver the request to the servlet. This must
   * be a safety thing. It web server returns code "403 Forbidden". 
   * Following is the definition of this status code:
   * 
   * The server understood the request, but is refusing to fulfill it. 
   * Authorization will not help and the request SHOULD NOT be repeated. 
   * If the request method was not HEAD and the server wishes to make public 
   * why the request has not been fulfilled, it SHOULD describe the reason 
   * for the refusal in the entity. If the server does not wish to make this 
   * information available to the client, the status code 404 (Not Found) 
   * can be used instead. 
   * @throws Exception 
 */

 
  
  
  
  private String[] performUpload(HttpServletRequest request) throws Exception
  {    
    String docUniqueParentDirName = null;
    String uniqueDirSegment = EMPTY_STR;
    
    ServletRequestContext requestCtxt = new ServletRequestContext(request);
    String uniqueDirParameter = request.getQueryString(); 
    if (null != uniqueDirParameter) {
      String[] parameter = uniqueDirParameter.split("=");
      docUniqueParentDirName = parameter[1].trim();    
    }
    else docUniqueParentDirName = UniqueIdMaker.getId(); 
      
    uniqueDirSegment = SLASH + docUniqueParentDirName;
    
    String[] filesUrlsStrs = null;   
    //first check if the upload request coming in is a multipart request
    boolean isMultipart = ServletFileUpload.isMultipartContent(requestCtxt);
    
    try {
      if (!isMultipart)
        throw new ServletException("Request ENCTYPE (ie ContentType) should be Multipart/form-data");
              
      DiskFileItemFactory factory = new DiskFileItemFactory();    
      
      /*
      //configure the factory here, if desired: Set factory constraints
      factory.setSizeThreshold(yourMaxMemorySize);
      factory.setRepository(yourTempDirectory);
      */
      
      ServletFileUpload upload = new ServletFileUpload(factory);
  
      /*
      //set overall request size constraint
      upload.setSizeMax(yourMaxRequestSize);
      */
      
      //parse this request by the handler this gives us a list of 
      //items from the request
      List items = null;
      items = upload.parseRequest(request);
      
      filesUrlsStrs = new String[items.size()];  //this may be slightly bigger than actual number of files urls to be returned
      
      int fileCount = 0;
      Iterator itr = items.iterator();
      while(itr.hasNext()) {
        FileItem item = (FileItem) itr.next();
              
        // check if the current item is a form field or an uploaded file
        if(!item.isFormField()) {
          {}//Logwriter.printOnConsole("Uploading file " + item.getFieldName() + " to Document Repository");          
          // the item must be an uploaded file save it to disk.   
          (new File(REPOSITORY_DIR_PATH+uniqueDirSegment)).mkdir();
          String fileName  = item.getName();  
          File savedFile = new File(REPOSITORY_DIR_PATH+uniqueDirSegment, fileName);
          item.write(savedFile);
                   
          filesUrlsStrs[fileCount++] =  generateFileURLString(docUniqueParentDirName, fileName);
        }
      }//while iter.hasNext()
      
    } catch (ServletException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    catch (FileUploadException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    finally {
    }
    
    return filesUrlsStrs;    
  }
  
  
  protected String generateFileURLString(String uniqueDirName, String fileName) {
    String uniqueDirSegment = null;
    if (null==uniqueDirName || uniqueDirName.equals(EMPTY_STR))
      uniqueDirSegment = EMPTY_STR;
    else uniqueDirSegment = SLASH+uniqueDirName;
    
    String urlstr = FILE_URL_PROTOCOL+hostname+SLASH+SERVLET_CONTEXT_NAME+SLASH 
                    +REPOSITORY_DIR_NAME+uniqueDirSegment+SLASH+fileName;
    
    return urlstr;
    }
  
  public void destroy()
  {
    // nothing to do
    {}//Logwriter.printOnConsole("******FileDepot.destroy() called *******");    
  }
}