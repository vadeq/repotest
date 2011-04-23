package zws.service.packaging; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.datasource.pkg.PackageSourceBase;
import zws.pkg.DataPackage;
import zws.service.document.DocumentSvc;
import zws.util.FileUtil;
//impoer zws.util.Logwriter;
import zws.util.Zunpack;

import java.io.File;
import java.net.URL;
import zws.Server;

public class PackagingSvc {

  public static void retrievePackage(DataPackage dPkg) throws Exception {
  //Break this into 2 -retrieve package and untarball
  //if (Server.getName().equalsIgnoreCase(dPkg.getServerName())) return;

    PackageSourceBase pkg = new PackageSourceBase();
    pkg.setName(dPkg.getName());
    String s = pkg.getDomainName();
    s = dPkg.getDomainName();
    s = pkg.getServerName();
    s = dPkg.getServerName();
    if (!(pkg.getDomainName().equalsIgnoreCase(dPkg.getDomainName()) &&  pkg.getServerName().equalsIgnoreCase(dPkg.getServerName()))) 
      pkg.clear(); //clears the .tar and tar.gz files if retreiving from remote location
        	
    File tarFile = new File(pkg.getPackageRoot().getParentFile(), pkg.getName()+".tar");
    if (tarFile.exists()) FileUtil.delete(tarFile);
/*    {
      String dupName = tarFile.getName() + "_dup";
      File dup = new File(tarFile.getParentFile(), dupName);
      while (dup.exists()) dup = new File (dup.getParentFile(), dup.getName() + "_dup");
      tarFile.renameTo(dup);
    }
*/
    File tarball = new File(pkg.getPackageRoot().getParentFile(), pkg.getName()+".tar"+".gz");
    if (!tarball.exists()) {
      PackagingService service = EJBLocator.findService(dPkg.getServerName());
      {}//Logwriter.printOnConsole("Attempting to download " + pkg.getName() + " from " + dPkg.getServerName());
      try {
        URL url = service.downloadPackage(pkg.getName());
        FileUtil.save(url.openStream(), tarball, true);
      }
      catch (Exception e) { e.printStackTrace(); }
    }
    Zunpack op = new Zunpack();
    op.setWorkingDirectory(tarball.getParentFile());
    op.setTarget(pkg.getName());
    op.setDeleteTarget(true);
    op.execute();
  }

  public static void unpackTarballs() throws Exception {
    File packageDir = new File(Properties.get(Names.PATH_PACKAGE_ROOT));
    File[] files = packageDir.listFiles();
    for (int i = 0; i < files.length; i++) {
      if (files[i].isFile() && files[i].getName().toLowerCase().endsWith(".tar.gz")) unpackTarball(files[i]);
    }
  }

  public static void unpackTarball(File tarball) throws Exception {
    if (!tarball.exists()) return;
    String target = tarball.getName().trim();
    if (target.length()<8) return;
    target = target.substring(0, target.length()-7);
    Zunpack op = new Zunpack();
    op.setWorkingDirectory(tarball.getParentFile());
    op.setTarget(target);
    op.setDeleteTarget(true);
    op.execute();
  }

  public static URL downloadPackage(String packageName) throws Exception {
    {}//Logwriter.printOnConsole("Download requested for package: " + packageName);
    PackageSourceBase pkg = new PackageSourceBase();
    pkg.setName(packageName);
    try {
      {}//Logwriter.printOnConsole("generating URL for " +pkg.getPackageRoot().getAbsolutePath());
      URL url = DocumentSvc.downloadTarball(pkg.getPackageRoot());
      FileUtil.delete(pkg.getPackageRoot());
      return url;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

}
