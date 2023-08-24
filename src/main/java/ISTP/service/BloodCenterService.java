package ISTP.service;

import ISTP.dtos.bloodCenter.BloodCenterDTO;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodCenterService {
    public List<BloodCenterDTO> API(String region1) throws Exception {
        String apiKey = "iLym7e7dn3giI%2BmdG330sFyy4RnUHHqDIFfthi7pYXmL9y1sP1DBmODFnyAMr5NxVTsltnvwVXPU7n9irOrADA%3D%3D";
        String uddi = "d70c42b0-ffe2-4ad0-9373-c2d37a0947f3";
            StringBuilder baseURL = new StringBuilder("http://api.odcloud.kr/api/15050729/v1/uddi:")
                .append(uddi)
                .append("?").append("page").append("=").append("1")
                .append("&").append("perPage").append("=").append("10")
                .append("&").append("serviceKey=").append(apiKey);
        // url 매핑해서 데이터 가져오는 것

        URL url = new URL(baseURL.toString());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Conetnt-type", "application/json");

        BufferedReader reader;

        String region = Region(region1);

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
        }
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        conn.disconnect();

        // 여기까지 JSON으로 파싱

        String jsonStr = builder.toString();
        JSONObject jsonObject = new JSONObject(jsonStr);
        // String jsonData = jsonObject.toString(2); // 들여쓰기 2칸

        // JSON 데이터 정렬

        JSONArray dataArray = jsonObject.getJSONArray("data");
        List<BloodCenterDTO> result = new ArrayList<>();
        int currentPage = 1;

        while (true) {
            List<BloodCenterDTO> pageData = fetchDataByPage(currentPage);
            if (currentPage == 16) { // 여기 수정
                break; // 페이지 데이터가 없으면 종료
            }
            result.addAll(pageData);
            currentPage++;
        }

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject dataObject = dataArray.getJSONObject(i);
            BloodCenterDTO centerDTO = new BloodCenterDTO();
            centerDTO.setPhoneNumber(dataObject.getString("전화번호"));
            centerDTO.setAddress(dataObject.getString("주소지"));
            centerDTO.setDonationCenter(dataObject.getString("헌혈의 집"));
            result.add(centerDTO);
        }

        List<BloodCenterDTO> resultData = result.subList(0, Math.min(result.size(), 150));

        List<BloodCenterDTO> regionData = new ArrayList<>();
        for(int k = 0; k < 150; k++) {
            if(resultData.get(k).getAddress().contains(region)) {
                regionData.add(resultData.get(k));
            }
        }

        return regionData;
    }

    public List<BloodCenterDTO> fetchDataByPage(int page) throws Exception {
        String apiKey = "iLym7e7dn3giI%2BmdG330sFyy4RnUHHqDIFfthi7pYXmL9y1sP1DBmODFnyAMr5NxVTsltnvwVXPU7n9irOrADA%3D%3D";
        String uddi = "d70c42b0-ffe2-4ad0-9373-c2d37a0947f3";
        StringBuilder baseURL = new StringBuilder("http://api.odcloud.kr/api/15050729/v1/uddi:")
                .append(uddi)
                .append("?").append("page").append("=").append(page)
                .append("&").append("perPage").append("=").append("10")
                .append("&").append("serviceKey=").append(apiKey);

        URL url = new URL(baseURL.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Conetnt-type", "application/json");

        BufferedReader reader;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
        }

        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        conn.disconnect();

        List<BloodCenterDTO> pageData = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(builder.toString());
        JSONArray dataArray = jsonObject.getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject dataObject = dataArray.getJSONObject(i);
            BloodCenterDTO centerDTO = new BloodCenterDTO();
            centerDTO.setPhoneNumber(dataObject.getString("전화번호"));
            centerDTO.setAddress(dataObject.getString("주소지"));
            centerDTO.setDonationCenter(dataObject.getString("헌혈의 집"));
            pageData.add(centerDTO);
        }

        return pageData;
    }

    public String Region(String region) {
        if (region.equals("경북 경주시") ||
                region.equals("경북 청도군") ||
                region.equals("경북 영천시") ||
                region.equals("경북 청송군") ||
                region.equals("경북 영양군") ||
                region.equals("경북 영덕군") ||
                region.equals("경북 울릉군") ||
                region.equals("경북 울진군")) {
            return "경북 포항시";
        } else if(region.equals("경북 상주시") ||
                region.equals("경북 문경시") ||
                region.equals("경북 김천시") ||
                region.equals("경북 성주군") ||
                region.equals("경북 의성군") ||
                region.equals("경북 고령군") ||
                region.equals("경북 봉화군") ||
                region.equals("경북 영주시") ||
                region.equals("경북 예천군")) {
            return "경북 구미시";
        } else if(region.equals("경남 창녕군") ||
                region.equals("경남 밀양시") ||
                region.equals("경남 의령군") ||
                region.equals("경남 거제시")) {
            return "경남 창원시";
        } else if(region.equals("경남 거창군") ||
                region.equals("경남 함양군") ||
                region.equals("경남 산청군") ||
                region.equals("경남 하동군") ||
                region.equals("경남 사천시") ||
                region.equals("경남 남해군") ||
                region.equals("경남 고성군") ||
                region.equals("경남 함안군") ||
                region.equals("경남 합천군")) {
            return "경남 진주시";
        } else if(region.equals("강원 철원군") ||
            region.equals("강원 화천군") ||
            region.equals("강원 양구군") ||
            region.equals("강원 인제군") ||
            region.equals("강원 홍천군") ||
            region.equals("강원 속초시") ||
            region.equals("강원 고성군") ||
            region.equals("강원 양양군")) {
        return "강원 춘천시";
        } else if(region.equals("강원 횡성군") ||
                region.equals("강원 평창군") ||
                region.equals("강원 영월군") ||
                region.equals("강원 청선군") ||
                region.equals("강원 동해시") ||
                region.equals("강원 삼척시") ||
                region.equals("강원 태백시")) {
            return "강원 원주시";
        } else if(region.equals("전남 신안군") ||
                region.equals("전남 무안군") ||
                region.equals("전남 함평군") ||
                region.equals("전남 진도군") ||
                region.equals("전남 나주시") ||
                region.equals("전남 완도군") ||
                region.equals("전남 강진군")) {
            return "전남 목포시";
        } else if(region.equals("전남 담양군") ||
                region.equals("전남 화순군") ||
                region.equals("전남 보성군") ||
                region.equals("전남 곡성군") ||
                region.equals("전남 구례군") ||
                region.equals("전남 고흥군") ||
                region.equals("전남 장성군")) {
            return "전남 순천시";
        } else if(region.equals("전북 임실군") ||
                region.equals("전북 순창군") ||
                region.equals("전북 남원군") ||
                region.equals("전북 장수군") ||
                region.equals("전북 진안군") ||
                region.equals("전북 무주군") ||
                region.equals("전북 완주군") ||
                region.equals("전북 김제시") ||
                region.equals("전북 부안군") ||
                region.equals("전북 고창군")) {
            return "전북 전주시";
        }
        else if(region.equals("충남 홍성군") ||
                region.equals("충남 보령시") ||
                region.equals("충남 청양군") ||
                region.equals("충남 부여군") ||
                region.equals("충남 논산시") ||
                region.equals("충남 계룡시") ||
                region.equals("충남 당진시") ||
                region.equals("충남 예산군") ||
                region.equals("충남 서산시") ||
                region.equals("충남 태안군")) {
            return "충남 공주시";
        }
        else if(region.equals("충북 제천시") ||
                region.equals("충북 단양군") ||
                region.equals("충북 증평군") ||
                region.equals("충북 괴산군") ||
                region.equals("충북 음성군") ||
                region.equals("충북 진천군") ||
                region.equals("충북 영동구") ||
                region.equals("충북 옥천구") ||
                region.equals("충북 보은군") ||
                region.equals("충북 청원군")) {
            return "충북 청주시";
        }
        else if(region.equals("경기 포천시") ||
                region.equals("경기 연천군") ||
                region.equals("경기 광주시") ||
                region.equals("경기 이천시") ||
                region.equals("경기 과천시") ||
                region.equals("경기 의왕시") ||
                region.equals("경기 여주시") ||
                region.equals("경기 오산시") ||
                region.equals("경기 안성시") ||
                region.equals("경기 포천시")) {
            return "경기 성남시";
        }
        else if(region.equals("경기 연천군") ||
                region.equals("경기 양주시") ||
                region.equals("경기 파주시") ||
                region.equals("경기 동두천시")) {
            return "경기 고양시";
        }
        else if(region.equals("부산 기장군") ||
                region.equals("부산 수영구") ||
                region.equals("부산 연제구")) {
            return "부산 해운대구";
        }
        else if(region.equals("부산 동구") ||
                region.equals("부산 서구") ||
                region.equals("부산 영도구") ||
                region.equals("부산 강서구")) {
            return "부산 부산진구";
        }
        else if(region.equals("인천 남구") ||
                region.equals("인천 동구") ||
                region.equals("인천 중구") ||
                region.equals("인천 강화군")) {
            return "인천 미추홀구";
        }
        else if(region.equals("인천 서구") ||
                region.equals("인천 옹진군")) {
            return "인천 연수구";
        }
        else if(region.equals("서울 도봉구") ||
                region.equals("서울 용산구") ||
                region.equals("서울 금천구")) {
            return "서울 중구";
        }
        else {
            return region;
        }
    }
}
