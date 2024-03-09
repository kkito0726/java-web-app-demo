package model.buisinessLogic;

import model.daos.UserInfoDao;
import model.dtos.UserInfoDto;

public class ExecuteLoginBL {
	public UserInfoDto executeSelectUserInfo(String inputUserId, String inputPassword) {
		UserInfoDao dao = new UserInfoDao();
		return dao.doSelect(inputUserId, inputPassword);
	}
}
