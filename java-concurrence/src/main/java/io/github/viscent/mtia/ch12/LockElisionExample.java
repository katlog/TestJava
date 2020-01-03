/*
授权声明：
本源码系《Java多线程编程实战指南（核心篇）》一书（ISBN：978-7-121-31065-2，以下称之为“原书”）的配套源码，
欲了解本代码的更多细节，请参考原书。
本代码仅为原书的配套说明之用，并不附带任何承诺（如质量保证和收益）。
以任何形式将本代码之部分或者全部用于营利性用途需经版权人书面同意。
将本代码之部分或者全部用于非营利性用途需要在代码中保留本声明。
任何对本代码的修改需在代码中以注释的形式注明修改人、修改时间以及修改内容。
本代码可以从以下网址下载：
https://github.com/Viscent/javamtia
http://www.broadview.com.cn/31065
*/
package io.github.viscent.mtia.ch12;

public class LockElisionExample {

  public static void main(String[] args) {
    ProductInfo pi = new ProductInfo();
    pi.setProductID("P0000001");
    pi.setCategoryID("C0010");
    pi.setInventory(100);
    pi.setRank(18);
    System.out.println(toJSON(pi));

  }

  static class ProductInfo {
    private String productID;
    private String categoryID;
    private int rank;
    private int inventory;

    public String getProductID() {
      return productID;
    }

    public void setProductID(String productID) {
      this.productID = productID;
    }

    public String getCategoryID() {
      return categoryID;
    }

    public void setCategoryID(String categoryID) {
      this.categoryID = categoryID;
    }

    public int getRank() {
      return rank;
    }

    public void setRank(int rank) {
      this.rank = rank;
    }

    public int getInventory() {
      return inventory;
    }

    public void setInventory(int inventory) {
      this.inventory = inventory;
    }

  }

  public static String toJSON(ProductInfo productInfo) {
    StringBuffer sbf = new StringBuffer();
    sbf.append("{\"productID\":\"").append(productInfo.productID);
    sbf.append("\",\"categoryID\":\"").append(productInfo.categoryID);
    sbf.append("\",\"rank\":").append(productInfo.rank);
    sbf.append(",\"inventory\":").append(productInfo.inventory);
    sbf.append('}');

    return sbf.toString();
  }

}
