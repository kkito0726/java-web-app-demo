package model.buisinessLogic;

import java.util.List;

import model.daos.SurveyDao;
import model.dtos.SurveyDto;

public class ExecuteShowAllSurveyBL {

	public List<SurveyDto> executeShowAllSurvey() {
		SurveyDao dao = new SurveyDao();
		return dao.selectAll();
	}
}
