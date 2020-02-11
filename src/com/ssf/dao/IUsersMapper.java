package com.ssf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ssf.entity.Holdshare;
import com.ssf.entity.Stock;
import com.ssf.entity.Subscribe;
import com.ssf.entity.User;


/**
 * mybatis���Ĳ����Ľӿ�
 * 1.IUsersMapper�ӿڲ�����Ե���user�� * 
 * 2.ע��ע�뵽app..xml�����ļ��У����Ǳ�ʶһ��Ψһ��bean id ��usersModel
 * 3.�ýӿ���mybatis�����ĺ��Ľӿ�����û��Classʵ����
 * @author Administrator
 */
@Component("usersModel")
public interface IUsersMapper {
	//4��������1���ֳ����������һ���ӿڵ�����  2ΪSpringMVC�첽json����������json 3 ��½
	//��ѯ����User�����ʺ��ڵ�½,��ӭ��½   Ժ��  jerry
	public User SelectUserByLogin(String uname,String upwd);
	public User SelectUserByUid(String uname);
//	public List<User> SelectUserByLogin1(String uname,String upwd);
	//���صļ��ϵ�size()��1
//	public User SelectUserByObject(User user);
//	public User SelectUserByUid(int uid);
//	//return json
	public int UserRegester(String uid, String name,String pwd);
	public List<Stock> SelectStockAll();
	public List<Stock> SelectStockById(String sinfo);
	public List<Stock> SelectStockByName(String sinfo);
	public int addSubscribe(String uid, String sid, String sname);
	public int removeSubscribe(String uid, String sid);
	public List<Stock> SelectMySubscribe(String uid);
	public List<Stock> SelectMyHold(String uid);
	public Subscribe searchSubscribeExistence(String uid,String sid);
	public Holdshare searchHoldExistence(String uid,String sid);
	public List<Holdshare> SelectHoldshareByUid(String uid);
	public int updatePwd(String uid,String pwd);
	public int updateUser(String uid,String uname, String ue_mail, String uwechat, String uqq, String usex, String location,String birthday);
    public int storeImg(String uid,String filePath);
    public int updateHoldshare(String uid, String sid, int number);
    public int addHoldshare(String uid, String sid, String sname, int number);
    public int changeMoney(String uid, double moneyChange);
    public int addReserve(int hoid,String uid,String sid,String sname,int number,double nowPrice);
    public Holdshare numberOfHold(String uid);
    public Subscribe numberOfConcern(String uid);
    
	
	
	
//	//���ݽ�ɫ���Ʋ�ѯ��Ӧuser���ϼ�¼
//	public List<User> SelectUserByRname(String rname);
//	//return Roloes
//	public Roles SelectRolesByrid(Integer rid);
//	public List<Roles> SelectRolesAll();
//	public int InsertUser(User user);
//	public int InsertUsers(List<User> listuser);
//	public List<Roles> SearchRolUser();
}
