package com.orazov.msbtrust;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MsbtrustApplicationTests {

	@Test
	void contextLoads() {
	}

	// TODO : Команда для проверки сотрудника к какому проекту он привязан:
//	SELECT p.*, e.first_name AS Имя, e.last_name AS Фамилия
//	FROM project p
//	LEFT JOIN employee_project ep ON p.id = ep.project_id
//	LEFT JOIN employee e ON ep.employee_id = e.id
//	WHERE p.id = 1;


}
