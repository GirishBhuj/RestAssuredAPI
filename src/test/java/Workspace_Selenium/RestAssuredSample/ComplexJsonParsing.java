package Workspace_Selenium.RestAssuredSample;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParsing {

	public static void main(String[] args)
	{
		JsonPath mJs = new JsonPath(APIPayload.CourseDtls());
		
		int mCourseCount = mJs.getInt("courses.size()");
		System.out.println("mCourseCount: "+ mCourseCount);
		
		int mPurchaseAmt = 0;
		//mPurchaseAmt = mJs.getInt("dashboard.purchaseAmount");
		//System.out.println("Purchse Amount: "+ mPurchaseAmt);

		 //courses[0].title
		 //courses[0].price
		 //courses[0].copies
		int TotalSoldAmount,mCourseAmt;
		TotalSoldAmount=0;
		
		String mCourseTitle="";
		System.out.println(mJs.get("courses.webAutomation[0].courseTitle"));
		System.out.println(mJs.get("courses.webAutomation[1].courseTitle"));
		System.out.println(mJs.get("courses.webAutomation[2].courseTitle"));
		
		for (int mCount=0; mCount < mCourseCount;mCount++)
		{
			  mCourseTitle=mJs.get("courses["+mCount+"].title");
			  System.out.println("Course Title: "+mCourseTitle + ", Price: " + 
			  mJs.get("courses["+mCount+"].price").toString());
			  mCourseAmt = mJs.getInt("courses["+mCount+"].price") * 
					  mJs.getInt("courses["+mCount+"].copies");
			  TotalSoldAmount = TotalSoldAmount + mCourseAmt;
		}
		
		System.out.println("=============");
		System.out.println("Total Sold Amount: "+TotalSoldAmount + " Purchase amount:"+mPurchaseAmt);
		Assert.assertEquals(TotalSoldAmount, mPurchaseAmt);
		
		/* for(int mCount=0;mCount < mCourseCount;mCount++)
		{
			String courseTitles=mJs.get("courses["+mCount+"].title");
			if(courseTitles.equalsIgnoreCase("RPA"))
			{
				int copies=mJs.get("courses["+mCount+"].copies");
				System.out.println("Number of copies sold by RPA Course: "+ copies);
				break;
			}  
		 }
		 */
	}

}
