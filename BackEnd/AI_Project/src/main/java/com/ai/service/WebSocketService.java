package com.ai.service;

import java.io.IOException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ai.config.WebSocketConfig;
import com.ai.dao.RiskPredictionRepository;
import com.ai.dao.TestGyroRepository;
import com.ai.dao.UserVitalSignRepository;
import com.ai.domain.TestGyro;
import com.ai.domain.UserVitalSign;
import com.ai.dto.VitalSignDTO;
import com.ai.dto.TestGyroDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WebSocketService {
	
	private final UserVitalSignRepository vitalRepo;
	private final TestGyroRepository gyroRepo;
	private final RiskPredictionRepository riskRepo;
	
	private final WebSocketConfig wsConfig;
	private final FlaskService flaskService;
	private int no = 1;
	
	@Scheduled(fixedRate = 1000)
	public void pushData() throws IOException {
		// DB의 user_vital_sign 테이블에서 no를 1씩 증가시키며 해당 행 조회 후 vitalSign 인스턴스에 저장
		System.out.println("no: " + no++);
		
		// Flask로 전송하는 testGyro
		TestGyro testGyro = gyroRepo.findById(no).orElse(null);
		TestGyroDTO testGyroDTO = TestGyroDTO.builder()
				.userCode(testGyro.getUserCode())
				.x(testGyro.getX())
				.y(testGyro.getY())
				.z(testGyro.getZ())
				.vitalDate(testGyro.getVitalDate())
				.build();
		
//		// flask 요청 및 응답
//		flaskService.sendDataToFlask(testGyroDTO);
//		
//		// 위험예측결과 전송
//		RiskPrediction rp = riskRepo.findById(no).orElse(null);
//		RiskPredictionDTO rpDTO = RiskPredictionDTO.builder()
//				.userCode(rp.getUserCode())
//				.registerDate(rp.getRegisterDate())
//				.predictionRiskLevel(rp.getPredictionRiskLevel())
//				.build();
//		wsConfig.sendPushMessage(rpDTO);
		

		// 생체 데이터 전송
		UserVitalSign vs = vitalRepo.findById(no).orElse(null);
		VitalSignDTO vitalSignDTO = VitalSignDTO.builder()
				.userCode(vs.getUserCode())
				.heartbeat(vs.getHeartbeat())
				.latitude(vs.getLatitude())
                .longitude(vs.getLongitude())
                .temperature(vs.getTemperature())
                .build();
		wsConfig.sendPushMessage(vitalSignDTO);
			
	}
}
