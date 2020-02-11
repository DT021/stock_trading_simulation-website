package com.ssf.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ssf.dao.IUsersMapper;
import com.ssf.entity.Holdshare;
import com.ssf.entity.Stock;
import com.ssf.entity.Subscribe;
import com.ssf.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
//Springmvc���ĵ��õ�����󣬶���springIOC����@ע�������󣬺��ĵĵ���mybaits�������ݿ� �����
//ajax�첽����json�������IOC mybatis@ע��ע��Ķ���
//@Controller:SpringMVC��ע��ע���ʶ��Ψһ��id ��usert
//http://localhost:8088/ssf/usert/login.action
@Controller
@RequestMapping("/usert")
public class UserController {
	//����UserMapp�ӿڲ����Ĺ���
	IUsersMapper userMapper=null;
	public IUsersMapper getUserMapper() {
		return userMapper;
	}
	@Autowired
	public void setUserMapper(@Qualifier("usersModel")IUsersMapper userMapper) {
		this.userMapper = userMapper;
	}
	//�÷��������url: http://127..../SSMdemo/usert/login.action
	//data: {"usn2":$("#usn1").val(),
//	"pwd2":$("#pwd1").val()},
	//@RequestParam("usn")String usn
//	String usn=request.getParameter("usn");
	//@ResponseBody  -->  jackson jar
	//���ðѷ�����list map�����໥��ӳ�䣬ת��Ϊ��ajax��Ҫ��json
	
	@RequestMapping("/login")//��¼
	@ResponseBody  
	public Map<String, Object> UsersLogin(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid2")String uid,
			@RequestParam("pwd2")String pwd) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		System.out.println(uid+"  //  "+pwd);		
//		peter  //  112
		Map<String, Object> map=new HashMap<String, Object>();
		User user=userMapper.SelectUserByLogin(uid, pwd);		
		if(user!=null){
			 HttpSession session=request.getSession();
			 session.setAttribute("uid",user.getUid());
			 session.setAttribute("uname",user.getUname());
			 session.setAttribute("img",user.getImg());
			System.out.println(user.getUid()+"  +");			
			map.put("id", user.getUid());
			map.put("name",user.getUname());
			System.out.println("�ɹ�����");
		}else{
			map.put("id", 0);
			System.out.println("û���ҵ�");
		}
		return map;
	}
	
	@RequestMapping("/androidlogin")//��¼ 10.2.228.167
	@ResponseBody  
	public Map<String, Object> AndroidUsersLogin(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid2")String uid,
			@RequestParam("pwd2")String pwd) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		System.out.println(uid+"  //  "+pwd);		
//		peter  //  112
		Map<String, Object> map=new HashMap<String, Object>();
		User user=userMapper.SelectUserByLogin(uid, pwd);		
		if(user!=null){
			 HttpSession session=request.getSession();
			 session.setAttribute("uid",user.getUid());
			 session.setAttribute("uname",user.getUname());
			 session.setAttribute("img",user.getImg());
			System.out.println(user.getUid()+"  +");			
			map.put("id", user.getUid());
			map.put("name",user.getUname());
			System.out.println("�ɹ�����");
		}else{
			map.put("id", 0);
			System.out.println("û���ҵ�");
		}
		JSONObject json = JSONObject.fromObject(map);
		return json;
	}
	
	@RequestMapping("/dynamicProfile")//���˽����ȡ������Ϣ
	@ResponseBody  
	public Map<String, Object> dynamicProfile(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid
			) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Map<String, Object> map=new HashMap<String, Object>();
		User user=userMapper.SelectUserByUid(uid);		
		if(user!=null){	
			map.put("uid", user.getUid());
			map.put("uname",user.getUname());
			map.put("upwd",user.getUpwd());
			map.put("ubirthdate",user.getUbirthdate());
			map.put("ue_mail",user.getUe_mail());
			map.put("udate",user.getUdate());
			map.put("uasset",user.getUasset());
			map.put("uqq",user.getUqq());
			map.put("uwechat",user.getUwechat());
			map.put("usex",user.getUsex());
			map.put("img",user.getImg());
			map.put("location",user.getLocation());
			System.out.println("�ɹ�����");
		}else{
			map.put("id", 0);
			System.out.println("û���ҵ�");
		}
		return map;
	}
	@RequestMapping("/android_dynamicProfile")//���˽����ȡ������Ϣ
	@ResponseBody  
	public Map<String, Object> androiddynamicProfile(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid
			) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Map<String, Object> map=new HashMap<String, Object>();
		User user=userMapper.SelectUserByUid(uid);		
		if(user!=null){	
			map.put("uid", user.getUid());
			map.put("uname",user.getUname());
			map.put("upwd",user.getUpwd());
			map.put("ubirthdate",user.getUbirthdate());
			map.put("ue_mail",user.getUe_mail());
			map.put("udate",user.getUdate());
			map.put("uasset",user.getUasset());
			map.put("uqq",user.getUqq());
			map.put("uwechat",user.getUwechat());
			map.put("usex",user.getUsex());
			map.put("img",user.getImg());
			map.put("location",user.getLocation());
			System.out.println("�ɹ�����");
		}else{
			map.put("id", 0);
			System.out.println("û���ҵ�");
		}
		JSONObject json = JSONObject.fromObject(map);
		return json;
	}
	
	@RequestMapping("/dynamicProfile2")//���˽����ȡ������Ϣ
	@ResponseBody  
	public Map<String, Object> dynamicProfile2(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid
			) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Holdshare numberOfHold = userMapper.numberOfHold(uid);
		Subscribe numberOfConcern = userMapper.numberOfConcern(uid);
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("numberOfHold",numberOfHold.getNumberOfResults());
		map.put("numberOfConcern",numberOfConcern.getNumberOfResults());
		return map;
	}
	
	
	
	@RequestMapping("/regester")//ע��
	@ResponseBody  
	public int InsertIntoUser(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid2")String uid,
			@RequestParam("pwd2")String pwd,@RequestParam("name2")String name) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		System.out.println(uid+"  //  "+pwd);
		
		User user=userMapper.SelectUserByLogin(uid, pwd);
		if(user!= null)
		{
			return 0;//�Ѿ�����
		}
		int result = userMapper.UserRegester(uid, name, pwd);	
		System.out.println(result+"  ///");
		User user1=userMapper.SelectUserByLogin(uid, pwd);
		if(user1 != null)
		{
			System.out.println("����ɹ�����");
			return 1;
		}
		else{
			return 2;
		}
		
	} 
	
	@RequestMapping("/updatePassword")//�ڸ����������޸�����
	@ResponseBody  
	public int updatePassword(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid,
			@RequestParam("pwd")String pwd) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		System.out.println(uid+"  //  "+pwd);
		
		int result = userMapper.updatePwd(uid,pwd);	
		return 1;
		
	} 
	
	@RequestMapping("/updateUser")//�ڸ����������޸��û���Ϣ
	@ResponseBody  
	public int updateUser(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid,
			@RequestParam("uname")String uname,@RequestParam("ue_mail")String ue_mail,@RequestParam("birthday")String birthday,@RequestParam("uwechat")String uwechat,@RequestParam("uqq")String uqq,@RequestParam("usex")String usex,@RequestParam("location")String location) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		int result = userMapper.updateUser(uid,uname,ue_mail,uwechat,uqq,usex,location,birthday);	
		return 1;
		
	} 
	
	@RequestMapping("/getSession")
	@ResponseBody 
	public Map<String,Object> getSession(HttpServletRequest request,
			HttpServletResponse response)throws UnsupportedEncodingException{
		Map<String, Object> map=new HashMap<String, Object>();
		HttpSession session=request.getSession(); 
		Object uidSession = session.getAttribute("uid");
		Object unameSession = session.getAttribute("uname");
		Object imgSession = session.getAttribute("img");
		map.put("uid", uidSession);
		map.put("uname", unameSession);
		map.put("img", imgSession);
		return map;
	}
	
	@RequestMapping("/rewriteSession")
	@ResponseBody 
	public int rewriteSession(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uname")String uname)throws UnsupportedEncodingException{
		Map<String, Object> map=new HashMap<String, Object>();
		HttpSession session=request.getSession(); 
		session.setAttribute("uname",uname);
		return 1;
	}
	
	@RequestMapping("/removeSession")
	@ResponseBody 
	public int removeSession(HttpServletRequest request,
			HttpServletResponse response)throws UnsupportedEncodingException{
		
		HttpSession session=request.getSession(); 
		session.invalidate();
		return 1;
	}
	
	
	
	@RequestMapping("/userEntity")
	@ResponseBody  
	public Map<String, Object> UserEntity(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid)
					throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		System.out.println(uid+"  //  ");		
//		peter  //  112
		Map<String, Object> map=new HashMap<String, Object>();
		User user=userMapper.SelectUserByUid(uid);		
		
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		if(user!=null){
			System.out.println(user.getUid()+"  +");			
			map.put("id", user.getUid());
			map.put("name",user.getUname());
			map.put("pwd",user.getUpwd());
			map.put("age",user.getUage());
		}
		return map;
	}
	//�÷��������url: http://127..../SSMdemo/usert/searchUser.action
	@RequestMapping("/searchStock")//��ѯ���й�Ʊ
	@ResponseBody
	public List<Map<String, Object>> FindUsersAll(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		System.out.println("map:" );
		List<Stock> listStock=this.userMapper.SelectStockAll();
		Map<String, Object> map=null;
//<div class='btn btn-primary' data-toggle='modal' data-target='#addModal'>Edit</div>
		for (Stock stock : listStock) {
			map=new HashMap<String, Object>();
			map.put("sid", stock.getSid());
			map.put("sname",stock.getSname());
			map.put("openTod", stock.getOpenTod());
			map.put("closeYes",stock.getCloseYes());
			map.put("volume",stock.getVolume());
			map.put("turnover", stock.getTurnover());
			map.put("edit", "<a href='javascript:void(0);' class='btn btn-info  btn-sm' title='"
			        +stock.getSid()+"' id='add-without-image1'>�鿴</a>");
			
			res.add(map);
			
		}
		System.out.println("map:" + map);
		//json  [{},{},{}]
		return res;
	}
	
	@RequestMapping("/uploadFile")//�ϴ�ͼƬ
	@ResponseBody
	public String uploadFile(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
		String filePath = request.getSession().getServletContext().getRealPath("/") + "images/userImgs/"
				+ file.getOriginalFilename();
		// ת���ļ�
		System.out.println(filePath);
		file.transferTo(new File(filePath));
		// �ϴ����ļ���
		
		String fileName = "images/userImgs/" + file.getOriginalFilename();
	    HttpSession session=request.getSession(); 
	    String uid =  session.getAttribute("uid").toString();
	    session.setAttribute("img",fileName);
		int result = userMapper.storeImg(uid,fileName);
		//String filename = file.getOriginalFilename();
		//System.out.println("fff "+filename);
//		
		return "success";
	}

	@RequestMapping("/addSubscribe")//������ѡ��
	@ResponseBody  
	public int AddSubscribe(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid,
			@RequestParam("sid")String sid,@RequestParam("sname")String sname) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		System.out.println(uid+"  //  "+sid);
		Subscribe s1 = userMapper.searchSubscribeExistence(uid, sid);
		if(s1 != null)
		{
			System.out.println("�Ѿ�����");
			return 0;
		}
		int result = userMapper.addSubscribe(uid, sid,sname);	
		System.out.println(result+" �����ѡ�ɹ�");
		return 1;
		
	} 
	
	@RequestMapping("/buyStock")//�����Ʊ
	@ResponseBody  
	public int buyStock(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid,
			@RequestParam("sid")String sid,@RequestParam("number")String number, @RequestParam("nowPrice")double nowPrice,@RequestParam("sname")String sname) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		int number1 = Integer.parseInt(number);
		double moneyChange  = nowPrice * number1;
		System.out.println(uid + "," + sid + "," + number1+"," + sname);
		User u1 = userMapper.SelectUserByUid(uid);
		double remainAssets = u1.getUasset();
		if(remainAssets < moneyChange)
		{
			return 2;
		}
		Holdshare hold1 = userMapper.searchHoldExistence(uid, sid);
		if(hold1 != null)
		{//update
			int temp = userMapper.updateHoldshare(uid,sid,number1);
		}
		else
		{//add
			int temp = userMapper.addHoldshare(uid,sid,sname,number1);
		}
		int temp1 = userMapper.changeMoney(uid,moneyChange);
		Holdshare hold2 = userMapper.searchHoldExistence(uid, sid);
		int hoid = hold2.getHoid();
		
		int temp2 = userMapper.addReserve(hoid,uid,sid,sname,number1,nowPrice);
		//int result = userMapper.addSubscribe(uid, sid,sname);	
		//System.out.println(result+" �����ѡ�ɹ�");
		return 1;
		
	} 
	
	@RequestMapping("/sellStock")//����Ʊ
	@ResponseBody  
	public int sellStock(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid,
			@RequestParam("sid")String sid,@RequestParam("number")String number, @RequestParam("nowPrice")double nowPrice,@RequestParam("sname")String sname) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		int number1 = Integer.parseInt(number);
		number1 = -number1;
		double moneyChange  = nowPrice * number1;
		System.out.println(uid + "," + sid + "," + number1+"," + sname);
		
		Holdshare hold1 = userMapper.searchHoldExistence(uid, sid);
		if(hold1 != null)
		{//update
			int volume = hold1.getHvolume();
			if(volume + number1 < 0)
			{
				return 3;
			}
			int temp = userMapper.updateHoldshare(uid,sid,number1);
		}
		else
		{//δ����
			return 2;
		}
		int temp1 = userMapper.changeMoney(uid,moneyChange);
		Holdshare hold2 = userMapper.searchHoldExistence(uid, sid);
		int hoid = hold2.getHoid();
		
		
		int temp2 = userMapper.addReserve(hoid,uid,sid,sname,number1,nowPrice);
		//int result = userMapper.addSubscribe(uid, sid,sname);	
		//System.out.println(result+" �����ѡ�ɹ�");
		return 1;
		
	} 
	
	@RequestMapping("/removeSubscribe")//ȡ����ע
	@ResponseBody  
	public int RemoveSubscribe(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid,
			@RequestParam("sid")String sid) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		System.out.println("111111");
		int result = userMapper.removeSubscribe(uid, sid);	
		return 1;
	} 
	
	
	@RequestMapping("/android_searchMySubscribe")//�����ҵĹ�ע��Ʊ
	@ResponseBody
	public List<Map<String, Object>> android_searchMySubscribe(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		System.out.println("map:" );
		List<Stock> listStock=this.userMapper.SelectMySubscribe(uid);
		Map<String, Object> map=null;
//<div class='btn btn-primary' data-toggle='modal' data-target='#addModal'>Edit</div>
		for (Stock stock : listStock) {
			map=new HashMap<String, Object>();
			map.put("sid", stock.getSid());
			map.put("sname",stock.getSname());
			map.put("openTod", stock.getOpenTod());
			map.put("closeYes",stock.getCloseYes());
			map.put("volume",stock.getVolume());
			map.put("turnover", stock.getTurnover());
			map.put("edit1", "<a href='javascript:void(0);' class='btn btn-info  btn-sm' title='"
			        +stock.getSid()+"' id='add-without-image1'>�鿴</a>");
			map.put("edit2", "<a href='javascript:void(0);' class='btn btn-info  btn-sm' title='"
			        +stock.getSid()+"' id='add-without-image2'>ȡ����ע</a>");
			
			res.add(map);
			
		}
		System.out.println("map:" + map);
		//json  [{},{},{}]

		JSONArray json = JSONArray.fromObject(res);
		return json;
	}
	
	@RequestMapping("/android_searchMyHold")//�����ҵĳ��й�Ʊ�����ع�Ʊ�����Ϣ
	@ResponseBody
	public List<Map<String, Object>> android_searchMyHold(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		System.out.println("map:" );
		List<Stock> listStock=this.userMapper.SelectMyHold(uid);
		Map<String, Object> map=null;
//<div class='btn btn-primary' data-toggle='modal' data-target='#addModal'>Edit</div>
		for (Stock stock : listStock) {
			map=new HashMap<String, Object>();
			map.put("sid", stock.getSid());
			map.put("sname",stock.getSname());
			map.put("openTod", stock.getOpenTod());
			map.put("closeYes",stock.getCloseYes());
			map.put("volume",stock.getVolume());
			map.put("turnover", stock.getTurnover());
			map.put("edit1", "<a href='javascript:void(0);' class='btn btn-info  btn-sm' title='"
			        +stock.getSid()+"' id='add-without-image1'>�鿴</a>");
		
			res.add(map);
		}
		System.out.println("map:" + map);
		//json  [{},{},{}]
		JSONArray json = JSONArray.fromObject(res);
		return json;
	}
	
	@RequestMapping("/android_buyStock")//�����Ʊ
	@ResponseBody  
	public int android_buyStock(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid,
			@RequestParam("sid")String sid,@RequestParam("number")String number, @RequestParam("nowPrice")String nowPrice,@RequestParam("sname")String sname) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		double nowPrice1 =  Double.valueOf(nowPrice);
		int number1 = Integer.parseInt(number);
		double moneyChange  = nowPrice1 * number1;
		System.out.println(uid + "," + sid + "," + number1+"," + sname);
		User u1 = userMapper.SelectUserByUid(uid);
		double remainAssets = u1.getUasset();
		if(remainAssets < moneyChange)
		{
			return 2;
		}
		Holdshare hold1 = userMapper.searchHoldExistence(uid, sid);
		if(hold1 != null)
		{//update
			int temp = userMapper.updateHoldshare(uid,sid,number1);
		}
		else
		{//add
			int temp = userMapper.addHoldshare(uid,sid,sname,number1);
		}
		int temp1 = userMapper.changeMoney(uid,moneyChange);
		Holdshare hold2 = userMapper.searchHoldExistence(uid, sid);
		int hoid = hold2.getHoid();
		
		int temp2 = userMapper.addReserve(hoid,uid,sid,sname,number1,nowPrice1);
		//int result = userMapper.addSubscribe(uid, sid,sname);	
		//System.out.println(result+" �����ѡ�ɹ�");
		return 1;
		
	} 
	@RequestMapping("/android_sellStock")//����Ʊ
	@ResponseBody  
	public int android_sellStock(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid,
			@RequestParam("sid")String sid,@RequestParam("number")String number, @RequestParam("nowPrice")String nowPrice,@RequestParam("sname")String sname) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		double nowPrice1 = Double.valueOf(nowPrice);
		int number1 = Integer.parseInt(number);
		number1 = -number1;
		double moneyChange  = nowPrice1 * number1;
		System.out.println(uid + "," + sid + "," + number1+"," + sname);
		
		Holdshare hold1 = userMapper.searchHoldExistence(uid, sid);
		if(hold1 != null)
		{//update
			int volume = hold1.getHvolume();
			if(volume + number1 < 0)
			{
				return 3;
			}
			int temp = userMapper.updateHoldshare(uid,sid,number1);
		}
		else
		{//δ����
			return 2;
		}
		int temp1 = userMapper.changeMoney(uid,moneyChange);
		Holdshare hold2 = userMapper.searchHoldExistence(uid, sid);
		int hoid = hold2.getHoid();
		
		
		int temp2 = userMapper.addReserve(hoid,uid,sid,sname,number1,nowPrice1);
		//int result = userMapper.addSubscribe(uid, sid,sname);	
		//System.out.println(result+" �����ѡ�ɹ�");
		return 1;
		
	} 
	
	@RequestMapping("/searchSpecificStock")//���ؼ���ģ��������Ʊ
	@ResponseBody
	public List<Map<String, Object>> FindSpecificStock(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("sinfo")String sinfo) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		System.out.println("map:" );
		//sinfo = "'%" + sinfo + "%'";
		//System.out.println(sinfo);
		List<Stock> listStock1=this.userMapper.SelectStockById(sinfo);
		List<Stock> listStock2=this.userMapper.SelectStockByName(sinfo);
		System.out.println(listStock1.size()+" / "+listStock2.size());
		Map<String, Object> map=null;
//<div class='btn btn-primary' data-toggle='modal' data-target='#addModal'>Edit</div>
		for (Stock stock : listStock1) {
			map=new HashMap<String, Object>();
			map.put("sid", stock.getSid());
			map.put("sname",stock.getSname());
			map.put("openTod", stock.getOpenTod());
			map.put("closeYes",stock.getCloseYes());
			map.put("volume",stock.getVolume());
			map.put("turnover", stock.getTurnover());
			map.put("edit", "<a href='javascript:void(0);' class='btn btn-info  btn-sm' title='"
			        +stock.getSid()+"' id='add-without-image1'>�鿴</a>");
			
			res.add(map);
			
		}
		for (Stock stock : listStock2) {
			map=new HashMap<String, Object>();
			map.put("sid", stock.getSid());
			map.put("sname",stock.getSname());
			map.put("openTod", stock.getOpenTod());
			map.put("closeYes",stock.getCloseYes());
			map.put("volume",stock.getVolume());
			map.put("turnover", stock.getTurnover());
			map.put("edit", "<a href='javascript:void(0);' class='btn btn-info  btn-sm' title='"
			        +stock.getSid()+"' id='add-without-image1'>�鿴</a>");
			
			res.add(map);
			
		}
		System.out.println("map:" + map);
		//json  [{},{},{}]
		return res;
	}
	
	@RequestMapping("/searchMySubscribe")//�����ҵĹ�ע��Ʊ
	@ResponseBody
	public List<Map<String, Object>> FindMySubscribe(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		System.out.println("map:" );
		List<Stock> listStock=this.userMapper.SelectMySubscribe(uid);
		Map<String, Object> map=null;
//<div class='btn btn-primary' data-toggle='modal' data-target='#addModal'>Edit</div>
		for (Stock stock : listStock) {
			map=new HashMap<String, Object>();
			map.put("sid", stock.getSid());
			map.put("sname",stock.getSname());
			map.put("openTod", stock.getOpenTod());
			map.put("closeYes",stock.getCloseYes());
			map.put("volume",stock.getVolume());
			map.put("turnover", stock.getTurnover());
			map.put("edit1", "<a href='javascript:void(0);' class='btn btn-info  btn-sm' title='"
			        +stock.getSid()+"' id='add-without-image1'>�鿴</a>");
			map.put("edit2", "<a href='javascript:void(0);' class='btn btn-info  btn-sm' title='"
			        +stock.getSid()+"' id='add-without-image2'>ȡ����ע</a>");
			
			res.add(map);
			
		}
		System.out.println("map:" + map);
		//json  [{},{},{}]
		return res;
	}
	
	
	
	@RequestMapping("/searchMyHold")//�����ҵĳ��й�Ʊ�����ع�Ʊ�����Ϣ
	@ResponseBody
	public List<Map<String, Object>> FindMyHold(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		System.out.println("map:" );
		List<Stock> listStock=this.userMapper.SelectMyHold(uid);
		Map<String, Object> map=null;
//<div class='btn btn-primary' data-toggle='modal' data-target='#addModal'>Edit</div>
		for (Stock stock : listStock) {
			map=new HashMap<String, Object>();
			map.put("sid", stock.getSid());
			map.put("sname",stock.getSname());
			map.put("openTod", stock.getOpenTod());
			map.put("closeYes",stock.getCloseYes());
			map.put("volume",stock.getVolume());
			map.put("turnover", stock.getTurnover());
			map.put("edit1", "<a href='javascript:void(0);' class='btn btn-info  btn-sm' title='"
			        +stock.getSid()+"' id='add-without-image1'>�鿴</a>");
			
			
			res.add(map);
			
		}
		System.out.println("map:" + map);
		//json  [{},{},{}]
		return res;
	}
	
	@RequestMapping("/android_selectHoldshareByUid")//�����ҵĳ��й�Ʊ�����س��б����Ϣ
	@ResponseBody  
	public List<Map<String, Object>> Android_SelectHoldshareByUid(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		List<Holdshare> listHoldshare=this.userMapper.SelectHoldshareByUid(uid);
		Map<String, Object> map=null;
		for (Holdshare holdshare : listHoldshare) {
			map=new HashMap<String, Object>();
			map.put("hoid", holdshare.getHoid());
			map.put("uid", holdshare.getUid());
			map.put("sid", holdshare.getSid());
			map.put("sname", holdshare.getSname());
			map.put("hvolume", holdshare.getHvolume());
			//System.out.println("hoid:"+holdshare.getHoid());
			map.put("edit1", "<a href='javascript:void(0);' class='btn btn-info  btn-sm' title='"
			        +holdshare.getSid()+ "' id='add-without-image1'>����</a>");
			
			res.add(map);
		}
		JSONArray json = JSONArray.fromObject(res);
		return json;
	}

	
	
	@RequestMapping("/selectHoldshareByUid")//�����ҵĳ��й�Ʊ�����س��б����Ϣ
	@ResponseBody  
	public List<Map<String, Object>> SelectHoldshareByUid(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("uid")String uid) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		List<Holdshare> listHoldshare=this.userMapper.SelectHoldshareByUid(uid);
		Map<String, Object> map=null;
		for (Holdshare holdshare : listHoldshare) {
			map=new HashMap<String, Object>();
			map.put("hoid", holdshare.getHoid());
			map.put("uid", holdshare.getUid());
			map.put("sid", holdshare.getSid());
			map.put("sname", holdshare.getSname());
			map.put("hvolume", holdshare.getHvolume());
			//System.out.println("hoid:"+holdshare.getHoid());
			map.put("edit1", "<a href='javascript:void(0);' class='btn btn-info  btn-sm' title='"
			        +holdshare.getSid()+ "' id='add-without-image1'>����</a>");
			
			res.add(map);
		}
		return res;
	}

	
	

}

