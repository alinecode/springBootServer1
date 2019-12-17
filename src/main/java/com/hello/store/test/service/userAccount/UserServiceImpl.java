package com.hello.store.test.service.userAccount;

import java.util.concurrent.CopyOnWriteArraySet;

import org.beetl.sql.core.query.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hello.store.test.dao.UserAccountDao;
import com.hello.store.test.dto.UserAccountDto;
import com.hello.store.test.entity.UserAccount;
import com.hello.store.test.webSocket.ChatWebSocket;
import com.hello.store.test.webSocket.ImUserInfoData;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserAccountDao userDao;

	@Override
	public String addUser(UserAccountDto accountDto) {
		
		UserAccount entity = new UserAccount();
		
		BeanUtils.copyProperties(accountDto, entity);
		
		Query<UserAccount> query = userDao.getSQLManager().query(UserAccount.class);
		
		UserAccount single = query.andEq("account", entity.getAccount()).single();
		
		if (single!=null) {
			return "0";
		}
		
		userDao.insert(entity);
		
		return "1";
	}

	@Override
	public String login(UserAccountDto accountDto) {
		
		String flag = "";
		
		Query<UserAccount> userquery = userDao.getSQLManager().query(UserAccount.class);
		
		UserAccount single = userquery.andEq("account", accountDto.getAccount())
				.andEq("password", accountDto.getPassword()).single();
		
		if (single==null) {
			flag = "0";
		}else {
			flag = JSON.toJSONString(single);
		}
//		JSONArray.parseArray(JSON.toJSONString(template));
		
//		System.err.println(flag);
		return flag;
	}

	@Override
	public String chatInit(UserAccountDto user) {
		// 方法返回在线用户，以及自己的信息
		String reString = "";
		// 根据官网描述 组织返回的信息结构: code、msg、data{mine、friend{其实是群组，"list"才是群组内的朋友}}
		JSONObject jsonObject = new JSONObject();// 最上层 0层
		jsonObject.put("code", 0); // 0为请求成功  一层
		jsonObject.put("msg", ""); // 错误信息  一层
		JSONObject dataObject = new JSONObject();// data层 一层 一层结束
		
		JSONArray friendList = new JSONArray();// friend层(好友群组层，data层需要)
		
		// 新建一个好友list,好友群组：用于groupObject1  开始
		JSONArray friendList1 = new JSONArray();// friend层(好友群组层) 需要的真实好友列表
		// 新建一个好友list,完毕
		
		
		// 伪造一个好友群组开始
		JSONObject groupObject1 = new JSONObject();// data层
		groupObject1.put("groupname", "测试部");
		groupObject1.put("id", 1);
		// 将好友列表放入该群组 或者将此句放到for循环后
		groupObject1.put("list", friendList1);
		friendList.add(groupObject1);
		// 伪造一个好友群组结束
		
		
		
		// 所有在线用户
		CopyOnWriteArraySet<ChatWebSocket> webSocketSet = ChatWebSocket.webSocketSet;
		 for (ChatWebSocket item : webSocketSet) {
			 String uid = item.getSid();
			 if (uid.equals(user.getAccount())) {
				 // 设置mine的信息
				 ImUserInfoData iUserInfoData = new ImUserInfoData();
				 iUserInfoData.setAvatar("");
				 iUserInfoData.setId(Long.valueOf(uid));
				 iUserInfoData.setSign(uid);
				 iUserInfoData.setStatus("online");
				 iUserInfoData.setUsername(uid);
				 
				 dataObject.put("mine", iUserInfoData);
				 
				continue;
			}
			 ImUserInfoData iUserInfoData = new ImUserInfoData();
			 iUserInfoData.setAvatar("");
			 iUserInfoData.setId(Long.valueOf(uid));
			 iUserInfoData.setSign(uid);
			 iUserInfoData.setStatus("online");
			 iUserInfoData.setUsername(uid);
			 
			 // 循环一个朋友 放入测试部的朋友列表内
			 friendList1.add(iUserInfoData);
			 
		}
		 dataObject.put("friend", friendList);
		 
		 jsonObject.put("data", dataObject);
		
		 reString = JSON.toJSONString(jsonObject);
		 
		return reString;
	}
	
}








