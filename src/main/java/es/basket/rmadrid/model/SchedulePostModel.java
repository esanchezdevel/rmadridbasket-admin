package es.basket.rmadrid.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import es.basket.rmadrid.controller.AdminController;
import es.basket.rmadrid.jpa.entity.Schedule;

@Component
public class SchedulePostModel implements Models<Schedule> {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Override
	public void execute(Schedule schedule) {
		
		logger.debug("Register new game in the schedule: " + schedule);
	}
}
