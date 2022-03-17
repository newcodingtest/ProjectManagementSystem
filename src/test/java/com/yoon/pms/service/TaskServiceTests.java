package com.yoon.pms.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import java.util.Optional;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.yoon.pms.TaskFactory;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.repository.TaskRepository;


//@RunWith(MockitoJUnitRunner.class) //Junit 4 
@ExtendWith(MockitoExtension.class) // Junit 5
//@SpringBootTest(classes = TaskService.class)
public class TaskServiceTests {
	
	@InjectMocks //@InjectMocks: 테스트 할 모듈 위에 설정
	private TaskService service;
	
	@Mock // @Mock: 대체할 모듈   -> service는 repository를 호출하여 사용하기 때문
	private TaskRepository repository;
	
	@BeforeEach
	void setup() {
		this.service = new TaskServiceImpl(repository);
	}
	
	
	@Test
	@DisplayName("새 Task 저장")
	void 등록_테스트(){
		//given 
		TaskDTO craeteDTO = TaskFactory.makeTaskDTO();
		
		//TDD와 비슷한 BDD(행위 주도 개발)--> repository 단에서는 기능적으로 테스트를 완료했기때문에
		//service 단에서는 적절히 service 메소드가 호출되는지의 여부만 판단하면 됨.-->행위의 여부 판단
		//why? service 메서드 안에는 결국 repository의 기능을 호출하기 때문
		
		BDDMockito.given(repository.findById(any()))
			.willReturn(Optional.empty());
		BDDMockito.given(repository.save(argThat(task -> task.getContents().equals("테스트 내용"))))
			.willReturn(service.dtoToEntity(craeteDTO));
		
		//when
		Long savedId = service.register(craeteDTO);
		
		//then
		BDDMockito.verify(repository).save(argThat(task->task.getContents().equals("테스트 내용")));
	}
	
	@Test
	void 그냥() {
		System.out.println("test");
	}

}
