package seeds.StartupInvestor.domain.constant;

public enum BusinessTypeConst {

    // IT 분야
    IT_DATA("IT", "데이터"),
    IT_SOFTWARE_APP_WEB("IT", "소프트웨어/앱/웹"),
    IT_SOLUTIONS_SAAS_SECURITY("IT", "솔루션/SaaS/보안"),
    IT_PLATFORM_METAVERSE("IT", "플랫폼/메타버스"),
    IT_OTHERS("IT", "기타"),

    // 교육 분야
    EDUCATION_YOUNG_CHILDREN("교육", "영유아/초중고교육"),
    EDUCATION_SELF_DEVELOPMENT("교육", "자기개발"),
    EDUCATION_OTHERS("교육", "기타"),

    // 금융 분야
    FINANCE_PERSONAL_ASSET_MANAGEMENT("금융", "개인자산관리"),
    FINANCE_PAYMENT_SYSTEM("금융", "결제시스템"),
    FINANCE_INSURANCE_LOANS("금융", "보험/대출"),
    FINANCE_CRYPTOCURRENCY("금융", "암호화폐"),
    FINANCE_INVESTMENT("금융", "투자"),
    FINANCE_OTHERS("금융", "기타"),

    // 내부사업/운영관리 분야
    INTERNAL_BUSINESS_HR("내부사업/운영관리", "HR"),
    INTERNAL_BUSINESS_MONITORING_SECURITY("내부사업/운영관리", "모니터링/보안"),
    INTERNAL_BUSINESS_UTILITY("내부사업/운영관리", "유틸리티"),
    INTERNAL_BUSINESS_COMPLIANCE("내부사업/운영관리", "컴플라이언스"),
    INTERNAL_BUSINESS_COLLABORATION_COMMUNICATION("내부사업/운영관리", "협업/커뮤니케이션"),
    INTERNAL_BUSINESS_OTHERS("내부사업/운영관리", "기타"),

    // 네트워킹 분야
    NETWORKING_SNS_COMMUNITY("네트워킹", "SNS/커뮤니티"),
    NETWORKING_OTHERS("네트워킹", "기타"),

    // 농축수산업 분야
    AGRICULTURE_LIVESTOCK_FISHERIES("농축수산업", "농축수산업"),
    AGRICULTURE_OTHERS("농축수산업", "기타"),

    // 라이프 스타일 분야
    LIFESTYLE_FOOD_BEVERAGE("라이프 스타일", "F&B"),
    LIFESTYLE_FURNITURE("라이프 스타일", "가구"),
    LIFESTYLE_SECURITY("라이프 스타일", "경비/보안"),
    LIFESTYLE_STATIONERY_TOYS("라이프 스타일", "문구/완구류"),
    LIFESTYLE_CULTURE("라이프 스타일", "문화"),
    LIFESTYLE_PETS("라이프 스타일", "반려동물"),
    LIFESTYLE_SHOPPING("라이프 스타일", "쇼핑"),
    LIFESTYLE_SILVER_TECH("라이프 스타일", "실버테크"),
    LIFESTYLE_ACTIVITIES("라이프 스타일", "액티비티"),
    LIFESTYLE_TRAVEL_ACCOMMODATION("라이프 스타일", "여행/숙박"),
    LIFESTYLE_BABY("라이프 스타일", "유아"),
    LIFESTYLE_ELECTRONICS("라이프 스타일", "전자기기"),
    LIFESTYLE_FASHION_BEAUTY("라이프 스타일", "패션/뷰티"),
    LIFESTYLE_ADMIN_LAW_POLITICS("라이프 스타일", "행정/법률/정치"),
    LIFESTYLE_OTHERS("라이프 스타일", "기타"),

    // 마케팅/PR 분야
    MARKETING_CUSTOMER_MANAGEMENT("마케팅/PR", "고객관리"),
    MARKETING_MARKETING("마케팅/PR", "마케팅"),
    MARKETING_CONTENT_CREATION_MANAGEMENT("마케팅/PR", "콘텐츠제작/관리"),
    MARKETING_OTHERS("마케팅/PR", "기타"),

    // 모빌리티 분야
    MOBILITY("모빌리티", "모빌리티"),
    MOBILITY_OTHERS("모빌리티", "기타"),

    // 미디어/엔터테인먼트 분야
    MEDIA_ENTERTAINMENT_MCN("미디어/엔터테인먼트", "MCN"),
    MEDIA_ENTERTAINMENT_MEDIA("미디어/엔터테인먼트", "미디어"),
    MEDIA_ENTERTAINMENT_ENTERTAINMENT("미디어/엔터테인먼트", "엔터테인먼트"),
    MEDIA_ENTERTAINMENT_INTELLECTUAL_PROPERTY("미디어/엔터테인먼트", "지적재산권(IP)"),
    MEDIA_ENTERTAINMENT_CONTENT_CREATION_MANAGEMENT("미디어/엔터테인먼트", "콘텐츠제작/관리"),
    MEDIA_ENTERTAINMENT_OTHERS("미디어/엔터테인먼트", "기타"),

    // 바이오/의료 분야
    BIO_MEDICAL_BIOTECH("바이오/의료", "바이오테크"),
    BIO_MEDICAL_MEDICINE_PHARMACEUTICAL("바이오/의료", "의료/약"),
    BIO_MEDICAL_OTHERS("바이오/의료", "기타"),

    // 에너지 분야
    ENERGY_ELECTRICITY("에너지", "전기"),
    ENERGY_RENEWABLE_ENERGY("에너지", "재생에너지"),
    ENERGY_OTHERS("에너지", "기타"),

    // 유통/물류 분야
    DISTRIBUTION_LOGISTICS_FULFILLMENT("유통/물류", "풀필먼트/로직스틱스"),
    DISTRIBUTION_LOGISTICS_OTHERS("유통/물류", "기타"),

    // 임팩트 분야
    IMPACT_ALTERNATIVE_MEAT_VEGAN("임팩트", "대체육/배양육/비건"),
    IMPACT_ENVIRONMENT_SOCIAL_IMPACT("임팩트", "환경/소셜임팩트"),
    IMPACT_OTHERS("임팩트", "기타"),

    // 재무 분야
    FINANCIAL_LOGISTICS_ERP("재무", "물류/ERP"),
    FINANCIAL_ACCOUNTING_FINANCE_EXPENSE("재무", "회계/재무/경비"),
    FINANCIAL_OTHERS("재무", "기타"),

    // 프롭테크 분야
    PROPTECH_REAL_ESTATE("프롭테크", "부동산"),
    PROPTECH_OTHERS("프롭테크", "기타"),

    // 하드웨어 분야
    HARDWARE_CONSTRUCTION_BUILDING("하드웨어", "건축/건설"),
    HARDWARE_MACHINERY_MANUFACTURING_SEMICONDUCTOR("하드웨어", "기계/제조/반도체"),
    HARDWARE_SHIP("하드웨어", "선박"),
    HARDWARE_WATER_TREATMENT("하드웨어", "수질개선"),
    HARDWARE_NEW_MATERIAL_CHEMICALS_MINERALS("하드웨어", "신소재/화학/광물"),
    HARDWARE_ELECTRICITY("하드웨어", "전력"),
    HARDWARE_AEROSPACE_DEFENSE_SPACE("하드웨어", "항공/방위/우주"),
    HARDWARE_OTHERS("하드웨어", "기타"),

    // 기타 분야
    OTHERS("기타", "기타");

    private final String mainCategory;
    private final String subCategory;

    BusinessTypeConst(String mainCategory, String subCategory) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }
}

