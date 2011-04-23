package zws.service.repository.test;
/*
 * DesignState - Design Compression Technology @author: arbind @version: 1.0
 * Created on May 30, 2007 12:05:16 PM Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */

import zws.repository.Repository;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;

import java.util.ArrayList;


/**
 * The Class TestFind.
 *
 * @author arbind
 */
public class TestFind {

  /**
   * The main method.
   *
   * @param args the args
   */
  public static void main(String[] args) {

    TestFind p = new TestFind();
    p.run("agile-sdk");
  }

  /**
   * Run.
   *
   * @param repName the rep name
   */
  private void run(String repName) {
    RepositoryService c = RepositoryClient.getClient();

    ArrayList list = (ArrayList) c.listRepositories();

    {} //System.out.println("------------------available repositories-------------");
    zws.util.PrintUtil.print(list + "\n");
    {} //System.out.println("-------------------------------");

    Repository r = c.findRepository(repName);
    System.out
        .println("------------------>find ilink repository result...<<<<<<<<<<<<<<<");
    {} //System.out.println(r.getName());
    {} //System.out.println(r.getDescription());

  }
}
