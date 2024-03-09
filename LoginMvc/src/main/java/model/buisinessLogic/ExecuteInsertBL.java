package model.buisinessLogic;

import model.daos.SurveyDao;
import model.dtos.SurveyDto;

public class ExecuteInsertBL {

	public boolean executeInsertSurvey(SurveyDto dto) {
		boolean successInsert = false;

		SurveyDao dao = new SurveyDao();
		successInsert = dao.doInsert(dto);

		return successInsert;
	}
}
