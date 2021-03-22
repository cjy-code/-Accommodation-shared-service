package com.test.bnna.member.tripplan;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlanDataController {

	@RequestMapping(value = "/member/tripplan/plandata.action", method = { RequestMethod.GET })
	public void plandata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
//	      response.setContentType("text/html; charset=utf-8");

		String pageNo = request.getParameter("pageNo");
		String keyword = request.getParameter("keyword");
		String areaCode = request.getParameter("areaCode");
		String sigunguCode = request.getParameter("sigunguCode");
		String cat1 = request.getParameter("cat1");

		System.out.println("keyword : " + keyword);
		// 검색어 URLEncoding 처리
		keyword = URLEncoder.encode(keyword, "UTF-8");
		System.out.println("URLEncoding : " + keyword);

		System.out.println("지역코드 : " + areaCode);
		System.out.println("시군구코드 : " + sigunguCode);
		System.out.println("cat1 : " + cat1);

		if (pageNo == "null") {
			pageNo = "1";
		}

		/*
		 * 지역별 : areaBasedList 내주변 : locationBasedList 키워드 : searchKeyword
		 */
		String apiType = "searchKeyword";
		

		String addr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/" + apiType + "?ServiceKey=";
		String serviceKey = "B8kGgQr6Ra%2F9lAcm2NmuR7Reod7526trWkpqj63J2noCq%2B1qs7sPhlErLpbqvHA%2BnFwSIw9VeSjWCouiiuVeEg%3D%3D";
		String parameter = "";
//	      serviceKey = URLEncoder.encode(serviceKey,"utf-8");
		PrintWriter out = response.getWriter();
		parameter = parameter + "&" + "contentTypeId=";
		parameter = parameter + "&" + "areaCode=";
		parameter = parameter + "&" + "sigunguCode=";
		parameter = parameter + "&" + "cat1=" + cat1;
		parameter = parameter + "&" + "listYN=Y";
		parameter = parameter + "&" + "MobileOS=ETC";
		parameter = parameter + "&" + "MobileApp=TourAPI3.0_Guide";
		parameter = parameter + "&" + "arrange=A";
		parameter = parameter + "&" + "numOfRows=15";
		parameter = parameter + "&" + "pageNo=" + pageNo;
		parameter = parameter + "&" + "keyword=" + keyword;
		parameter = parameter + "&" + "_type=json";

		/*
		 * 테스트할 URL
		 * http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?
		 * ServiceKey=인증키 &contentTypeId=32 &areaCode=1 &sigunguCode=1 &cat1= &cat2=
		 * &cat3= &listYN=Y &MobileOS=ETC &MobileApp=TourAPI3.0_Guide &arrange=A
		 * &numOfRows=12 &pageNo=1
		 */

		addr = addr + serviceKey + parameter;
		URL url = new URL(addr);

		InputStream in = url.openStream();
		CachedOutputStream bos = new CachedOutputStream();
		IOUtils.copy(in, bos);
		in.close();
		bos.close();

		// out.println(addr);

		String data = bos.getOut().toString();
		out.println(data);
		out.close();

//	      JSONObject json = new JSONObject();
//	      json.put("data", data);
	}
	
	
		// 상세정보 조회 Ajax
	   @RequestMapping(value = "/member/tripplan/detaildata.action", method = { RequestMethod.GET })
	   protected void detailCommonData(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, IOException {

	      request.setCharacterEncoding("utf-8");

	      String contentid = request.getParameter("contentid");
	      String contenttypeid = request.getParameter("contenttypeid");

	      System.out.println(contentid);
	      System.out.println(contenttypeid);

	      // contentid = "2607436";
	      // contenttypeid = "32";

	      response.setContentType("application/json; charset=utf-8");
//	      response.setContentType("text/html; charset=utf-8");

	      String addr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=";
	      String serviceKey = "B8kGgQr6Ra%2F9lAcm2NmuR7Reod7526trWkpqj63J2noCq%2B1qs7sPhlErLpbqvHA%2BnFwSIw9VeSjWCouiiuVeEg%3D%3D";
	      String parameter = "";
//	      serviceKey = URLEncoder.encode(serviceKey,"utf-8");
	      PrintWriter out = response.getWriter();
	      parameter = parameter + "&" + "contentTypeId=" + contenttypeid;
	      parameter = parameter + "&" + "contentId=" + contentid;
	      parameter = parameter + "&" + "MobileOS=ETC";
	      parameter = parameter + "&" + "MobileApp=TourAPI3.0_Guide";
	      parameter = parameter + "&" + "defaultYN=Y";
	      parameter = parameter + "&" + "firstImageYN=Y";
	      parameter = parameter + "&" + "areacodeYN=Y";
	      parameter = parameter + "&" + "catcodeYN=Y";
	      parameter = parameter + "&" + "addrinfoYN=Y";
	      parameter = parameter + "&" + "mapinfoYN=Y";
	      parameter = parameter + "&" + "overviewYN=Y";
	      parameter = parameter + "&" + "transGuideYN=Y";
	      parameter = parameter + "&" + "_type=json";

	      /*
	       * http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?
	       * ServiceKey=인증키 &contentTypeId=32 &contentId=2607436 &MobileOS=ETC
	       * &MobileApp=TourAPI3.0_Guide &defaultYN=Y &firstImageYN=Y &areacodeYN=Y
	       * &catcodeYN=Y &addrinfoYN=Y &mapinfoYN=Y &overviewYN=Y &transGuideYN=Y
	       */

	      addr = addr + serviceKey + parameter;
	      URL url = new URL(addr);

	      InputStream in = url.openStream();
	      CachedOutputStream bos = new CachedOutputStream();
	      IOUtils.copy(in, bos);
	      in.close();
	      bos.close();

	      // out.println(addr);

	      String data = bos.getOut().toString();
	      out.println(data);
	      out.close();

	      // JSONObject json = new JSONObject();
	      // json.put("data", data);
	   }	
	
	

}
