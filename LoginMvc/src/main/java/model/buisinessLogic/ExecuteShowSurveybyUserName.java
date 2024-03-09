package model.buisinessLogic;

import java.util.List;

import model.daos.SurveyDao;
import model.dtos.SurveyDto;

public class ExecuteShowSurveybyUserName {

	public List<SurveyDto> executeShowSurveyByUserName(String userName) {
		SurveyDao dao = new SurveyDao();
		return dao.selectByName(userName);
	}

}
