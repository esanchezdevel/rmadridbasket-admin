package es.basket.rmadrid.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.basket.rmadrid.jpa.entity.Schedule;
import es.basket.rmadrid.model.SchedulePostModel;
import es.basket.rmadrid.util.DateUtils;

@Controller
public class ScheduleController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private SchedulePostModel schedulePostModel;
	
	@PostMapping("/Admin/Schedule")
	@ResponseBody
	public ResponseEntity<HttpStatus> schedulePost(@RequestParam Map<String, String> body) {

		logger.debug("post received: " + body);

		Schedule schedule = new Schedule();
		schedule.setLocal(body.get("local"));
		schedule.setVisitor(body.get("visitor"));
		schedule.setCourt(body.get("court"));
		schedule.setDate(DateUtils.createGameDate(body.get("date")));
		
		schedulePostModel.execute(schedule);
		
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
