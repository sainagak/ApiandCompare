package azureCICD;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import baseCode.ApiRequest;
import com.aventstack.extentreports.Status;
import com.opencsv.exceptions.CsvException;

import baseCode.CompareXMLFiles;
import utilities.ReadXMLFilePathFromCSV;
import utilities.ReadXMLFilePathFromCSV2;

public class Test3 extends CompareXMLFiles {
	@Test(dataProvider = "dp", enabled = true)
	public void testcaseimplement3(String i, String j, String k) throws Exception {

		test.assignAuthor("myteam").assignCategory("REGRESSION").assignDevice("XML");

		test.log(Status.INFO, "TestCase Name: " + k);

		comparefiles(System.getProperty("user.dir") + "//src//test//resources//src//" + i + ".xml",
				ApiRequest.ApiRequest1(System.getProperty("user.dir") + "//src//test//resources//trgt//" + j + ".xml",k)); 

		test.log(Status.INFO, "Total No of actual differences after filtering: " + NoOfDifferences);

		// test.log(Status.INFO, "test counter: ");

		Assert.assertFalse(NoOfDifferences > 0);

	}

	@DataProvider(name = "dp")
	public Object[][] getdata() throws IOException, CsvException {

		return ReadXMLFilePathFromCSV.ReadPaths("testDataworktype2");
	}

}
