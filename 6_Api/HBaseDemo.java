package com.bjsxt.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HBaseDemo {

	//��Ĺ�����
	HBaseAdmin admin = null;
	//���ݵĹ�����
	HTable table = null;
	//����
	String tm = "phone";
	
	/**
	 * ��ɳ�ʼ������
	 * @throws Exception
	 */
	@Before
	public void init() throws Exception{
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "node1,node2,node3");
		admin = new HBaseAdmin(conf);
		table = new HTable(conf,tm.getBytes());
	}
	
	/**
	 * ������
	 * @throws Exception
	 */
	@Test
	public void createTable() throws Exception{
		//���������
		HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tm));
		//�����������
		HColumnDescriptor family = new HColumnDescriptor("cf".getBytes());
		desc.addFamily(family);
		if(admin.tableExists(tm)){
			admin.disableTable(tm);
			admin.deleteTable(tm);
		}
		admin.createTable(desc);
	}
	
	@Test
	public void insert() throws Exception{
		Put put = new Put("1111".getBytes());
		put.add("cf".getBytes(), "name".getBytes(), "zhangsan".getBytes());
		put.add("cf".getBytes(), "age".getBytes(), "12".getBytes());
		put.add("cf".getBytes(), "sex".getBytes(), "man".getBytes());
		table.put(put);
	}
	@Test
	public void get() throws Exception{
		Get get = new Get("1111".getBytes());
		//���Ҫ��ȡ���к����壬���������io���൱���ڷ����������˹���
		get.addColumn("cf".getBytes(), "name".getBytes());
		get.addColumn("cf".getBytes(), "age".getBytes());
		get.addColumn("cf".getBytes(), "sex".getBytes());
		Result result = table.get(get);
		Cell cell1 = result.getColumnLatestCell("cf".getBytes(), "name".getBytes());
		Cell cell2 = result.getColumnLatestCell("cf".getBytes(), "age".getBytes());
		Cell cell3 = result.getColumnLatestCell("cf".getBytes(), "sex".getBytes());
		System.out.println(Bytes.toString(CellUtil.cloneValue(cell1)));
		System.out.println(Bytes.toString(CellUtil.cloneValue(cell2)));
		System.out.println(Bytes.toString(CellUtil.cloneValue(cell3)));
	}
	
	@Test
	public void scan() throws Exception{
		Scan scan = new Scan();
//		scan.setStartRow(startRow);
//		scan.setStopRow(stopRow);
		ResultScanner rss = table.getScanner(scan);
		for (Result result : rss) {
			Cell cell1 = result.getColumnLatestCell("cf".getBytes(), "name".getBytes());
			Cell cell2 = result.getColumnLatestCell("cf".getBytes(), "age".getBytes());
			Cell cell3 = result.getColumnLatestCell("cf".getBytes(), "sex".getBytes());
			System.out.println(Bytes.toString(CellUtil.cloneValue(cell1)));
			System.out.println(Bytes.toString(CellUtil.cloneValue(cell2)));
			System.out.println(Bytes.toString(CellUtil.cloneValue(cell3)));
		}
	}
	
	@After
	public void destory() throws Exception{
		if(admin!=null){
			admin.close();
		}
	}
}
