package name.katlog.jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by fw on 2020/4/28
 */
public class TestXid {
   static class MyXid implements Xid {
        public int formatId;
        public byte gtrid[];
        public byte bqual[];
        public MyXid(){}
        public MyXid(int formatId,byte gtrid[],byte bqual[]){
            this.formatId=formatId;
            this.gtrid=gtrid;
            this.bqual=bqual;
        }
        @Override
        public int getFormatId(){
            return formatId;
        }
        @Override
        public byte[]getBranchQualifier(){
            return bqual;
        }
        @Override
        public byte[]getGlobalTransactionId(){
            return gtrid;
        }
    }
    public static class xa_demo{
        public static MysqlXADataSource getDataSource(
                String connString,
                String user,
                String passwd){
            try{
                MysqlXADataSource ds=new MysqlXADataSource();
                ds.setUrl(connString);
                ds.setUser(user);
                ds.setPassword(passwd);
                return ds;
            }catch(Exception e){
                System.out.println(e.toString());
                return null;
            }
        }
        public static void main(String[]args){
            String connString1="jdbc:mysql://192.168.24.43:3306/bank_shanghai";
            String connString2="jdbc:mysql://192.168.24.166:3306/bank_beijing";
            try{
                MysqlXADataSource ds1= getDataSource(connString1,"peter","12345");
                XAConnection xaConn1=ds1.getXAConnection();
                XAResource xaRes1=xaConn1.getXAResource();
                Connection conn1=xaConn1.getConnection();
                Statement stmt1=conn1.createStatement();

                MysqlXADataSource ds2= getDataSource(connString2,"david","12345");
                XAConnection xaConn2=ds2.getXAConnection();
                XAResource xaRes2=xaConn2.getXAResource();
                Connection conn2=xaConn2.getConnection();
                Statement stmt2=conn2.createStatement();
                Xid xid1=new MyXid(
                        100,
                        new byte[]{0x01},
                        new byte[]{0x02});
                Xid xid2=new MyXid(
                        100,
                        new byte[]{0x11},
                        new byte[]{0x12});
                try{

                    /*
                     * XA 事务SQL语法
                     * XA{START|BEGIN}xid[JOIN|RESUME]
                     * XA END xid[SUSPEND[FOR MIGRATE]]
                     * XA PREPARE xid
                     * XA COMMIT xid[ONE PHASE]
                     * XA ROLLBACK xid
                     * XA RECOVER
                     *
                     * */

                    // xa 1.准备阶段：有的参与者准备执行事务并锁住需要的资源
                    xaRes1.start(xid1,XAResource.TMNOFLAGS);
                    stmt1.execute("UPDATE account SET money=money-10000 WHERE user='david'");
                    xaRes1.end(xid1,XAResource.TMSUCCESS);

                    xaRes2.start(xid2,XAResource.TMNOFLAGS);
                    stmt2.execute(" UPDATE account SET money=money+10000 WHERE user='mariah'");
                    xaRes2.end(xid2,XAResource.TMSUCCESS);
                    int ret2=xaRes2.prepare(xid2);
                    int ret1=xaRes1.prepare(xid1);

                    // xa 2.提交阶段：当TM 确认所有参与者都Ready 后，向所有参与者发送COMMIT 命令
                    if (ret1 == XAResource.XA_OK
                            && ret2 == XAResource.XA_OK) {
                        xaRes1.commit(xid1, false);
                        xaRes2.commit(xid2, false);
                    } else {
                        xaRes1.rollback(xid1);
                        xaRes2.rollback(xid2);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    xaRes1.rollback(xid1);
                    xaRes2.rollback(xid2);
                }
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }
}
