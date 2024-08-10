package seeds.StartupInvestor.domain.constant;

import lombok.Getter;

@Getter
public enum TechTypeConst {

    // AI 분야
    AI_OCR("AI", "광학문자인식(OCR)"),
    AI_DEEP_LEARNING("AI", "딥러닝"),
    AI_MACHINE_LEARNING("AI", "머신러닝"),
    AI_NLP("AI", "자연어처리(NLP)"),
    AI_OTHERS("AI", "기타"),

    // XR 분야
    XR_VR("XR", "가상현실(VR)"),
    XR_AR("XR", "증강현실(AR)"),
    XR_MR("XR", "혼합현실(MR)"),
    XR_OTHERS("XR", "기타"),

    // 데이터 분야
    DATA_API("데이터", "API"),
    DATA_MINING("데이터", "데이터마이닝"),
    DATA_FABRIC("데이터", "데이터패브릭"),
    DATA_DEEP("데이터", "딥데이터"),
    DATA_BIG("데이터", "빅데이터"),
    DATA_OTHERS("데이터", "기타"),

    // 로보틱스 분야
    ROBOTICS_NANO("로보틱스", "나노"),
    ROBOTICS_DRONE("로보틱스", "드론"),
    ROBOTICS_ROBOT("로보틱스", "로봇"),
    ROBOTICS_BIONIC("로보틱스", "바이오닉"),
    ROBOTICS_OTHERS("로보틱스", "기타"),

    // 블록체인 분야
    BLOCKCHAIN_NFT("블록체인", "대체불가능토큰(NFT)"),
    BLOCKCHAIN_DID("블록체인", "탈중앙화신원증명(DID)"),
    BLOCKCHAIN_OTHERS("블록체인", "기타"),

    // 사물인터넷 분야
    IOT_5G("사물인터넷", "5G"),
    IOT_AGPS("사물인터넷", "A-GPS"),
    IOT_LIDAR("사물인터넷", "LiDAR"),
    IOT_X2X("사물인터넷", "X2X"),
    IOT_WEARABLE_DEVICES("사물인터넷", "웨어러블디바이스"),
    IOT_AIOT("사물인터넷", "사물지능(AIoT)"),
    IOT_OTHERS("사물인터넷", "기타"),

    // 신소재 분야
    NEW_MATERIAL_CNF("신소재", "탄소나노섬유(CNF)"),
    NEW_MATERIAL_CNT("신소재", "탄소나노튜브(CNT)"),
    NEW_MATERIAL_OTHERS("신소재", "기타"),

    // 에너지 분야
    ENERGY_SOLID_STATE_BATTERY("에너지", "전고체배터리"),
    ENERGY_OTHERS("에너지", "기타"),

    // 제조 분야
    MANUFACTURING_3D_PRINTING("제조", "3D프린팅"),
    MANUFACTURING_CAD("제조", "컴퓨터지원설계(CAD)"),
    MANUFACTURING_CNC("제조", "컴퓨터수치제어(CNC)"),
    MANUFACTURING_OTHERS("제조", "기타"),

    // 클라우드 분야
    CLOUD_OTA("클라우드", "OTA"),
    CLOUD_MSP("클라우드", "클라우드관리서비스(MSP)"),
    CLOUD_CDN("클라우드", "콘텐츠전송네트워크(CDN)"),
    CLOUD_HYBRID("클라우드", "하이브리드"),
    CLOUD_OTHERS("클라우드", "기타"),

    // 기타 분야
    OTHERS("기타", "기타");

    private final String mainCategory;
    private final String subCategory;

    TechTypeConst(String mainCategory, String subCategory) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

}
