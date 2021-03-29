package com.hello.store.test.service.excutePROCEDURE;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beetl.sql.core.OnConnection;
import org.springframework.beans.factory.annotation.Autowired;

import com.hello.store.test.dao.UserAccountDao;

/**
 * 执行数据库存储过程
 * 
 * @author AL
 *
 */
public class ExcutePROCEDURE {

	@Autowired
	UserAccountDao accountDao;

	/**
	 * 执行有两个结果集的存储过程
	 * @param querydata
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> excuteHaveTwoResult(Map querydata){

     String cg1 = "{call proData(?,?,?,?,?,?,?,?,?,?)}";
     final String call2 = cg1;

     List<Map> executeOnConnection = accountDao.getSQLManager().executeOnConnection(new OnConnection<List<Map>>() {

         @Override
         public List<Map> call(Connection conn) throws SQLException {

             CallableStatement callStatement = null;
             ResultSet rs2 = null;

             List<Map> list = new ArrayList<Map>();

             try {
                 callStatement = conn.prepareCall(call2);

                 callStatement.setString(1, querydata.get("a").toString());
                 callStatement.setString(2, querydata.get("b").toString());
                 callStatement.setString(3, "c".toString());
                 callStatement.setString(4, "d".toString());

                 callStatement.setInt(5,Integer.parseInt(querydata.get("999").toString()) );
                 callStatement.setInt(6, Integer.parseInt(querydata.get("888").toString()) );

                 callStatement.setString(7, "");
                 callStatement.setString(8, "");
                 callStatement.setString(9, "");
                 callStatement.setString(10, "");

                 callStatement.execute();

                 rs2 = callStatement.getResultSet();

                 if (rs2 == null) {
                     return null;
                 }

                 Map<String, Object> zongtemp = new HashMap<>();

                 List<Map> listjg1 = new ArrayList<Map>();
                 List<Map> listjg2 = new ArrayList<Map>();

                 if (rs2 != null) {

                     ResultSetMetaData metaData = rs2.getMetaData();
                     int columnCount = metaData.getColumnCount();
                     while (rs2.next()) {

                         Map<String, Object> rowData = new HashMap<>();// 声明Map
                         for (int i = 1; i <= columnCount; i++) {
                             rowData.put(metaData.getColumnName(i).toLowerCase(), rs2.getObject(i));// 获取键名及值
                         }
                         listjg1.add(rowData);
                     }
                     zongtemp.put("jieguo1",listjg1);
                     
                     rs2.close();

                     if (callStatement.getMoreResults()) {
                         rs2 = callStatement.getResultSet();
                         ResultSetMetaData metaData2 = rs2.getMetaData(); // 下标从1开始
                         int columnCount2 = metaData2.getColumnCount();
                         
                         while (rs2.next()) {
                             Map<String, Object> rowData = new HashMap<>();// 声明Map
                             for (int i = 1; i <= columnCount2; i++) {
                                 rowData.put(metaData2.getColumnName(i).toLowerCase(), rs2.getObject(i));// 获取键名及值
                             }
                             listjg2.add(rowData);
                         }

                         zongtemp.put("jieguo2",listjg2);
                     }

                     list.add(zongtemp);
                 }

                 return list;
             } catch (Exception e) {

             } finally {
                 if (rs2 != null) {
                     rs2.close();
                 }
                 callStatement.close();
             }
             return list;
         }
     });

     return executeOnConnection;

 }
	
	/**
	 * 执行一个有一个返回结果的存储过程
	 * 
	 * @param cs
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> excuteHaveOneResult(String cs) {

		String cg1 = "{call dbo.PRO_GET_cs(?)}";

		final String call2 = cg1;

		List<Map> executeOnConnection = accountDao.getSQLManager().executeOnConnection(new OnConnection<List<Map>>() {

			@Override
			public List<Map> call(Connection conn) throws SQLException {

				PreparedStatement callStatement = null;
				ResultSet rs2 = null;

				List<Map> list = new ArrayList<Map>();

				try {
					callStatement = conn.prepareStatement(call2);

					callStatement.setString(1, cs);
//					callStatement.setInt(2, Integer.valueOf(cs2));

					rs2 = callStatement.executeQuery();

					ResultSetMetaData metaData = rs2.getMetaData();
					int columnCount = metaData.getColumnCount();

					while (rs2.next()) {

						Map<String, Object> rowData = new HashMap<>();// 声明Map
						for (int i = 1; i <= columnCount; i++) {
							rowData.put(metaData.getColumnName(i), rs2.getObject(i));// 获取键名及值
						}
						list.add(rowData);
					}

					return list;
				} catch (Exception e) {

				} finally {
					if (rs2 != null) {
						rs2.close();
					}
					callStatement.close();
				}
				return list;
			}
		});

		return executeOnConnection;

	}

	/**
	 * 执行一个没有返回值的存储过程
	 * 
	 * @param map
	 */
	@SuppressWarnings("rawtypes")
	public void excuteNoResult(Map<String, String> map) {

		// 问号是参数占位符，供下方设置参数使用
		String cg1 = "{call dbo.PRO_mc(?)}";

		final String call2 = cg1;

		accountDao.getSQLManager().executeOnConnection(new OnConnection<List<Map>>() {

			@Override
			public List<Map> call(Connection conn) throws SQLException {

				CallableStatement callStatement = null;
				ResultSet rs2 = null;

				List<Map> list = new ArrayList<Map>();

				try {
					// 建立连接
					callStatement = conn.prepareCall(call2);

					// 设置参数
					callStatement.setString(1, map.get(""));
//					callStatement.registerOutParameter(2, Types.NVARCHAR);

					// 执行
					callStatement.execute();

					return list; // 可以不返回List<Map>；可以返回一个是否执行成功的标志（execute()的返回值）。
				} catch (Exception e) {

				} finally {
					if (rs2 != null) {

						rs2.close();
					}
					callStatement.close();
				}
				return list;
			}
		});

	}

}
