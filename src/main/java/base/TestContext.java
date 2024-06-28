package base;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
	
	public Map<String,String> sharedData;
	
	public TestContext()
	{
		sharedData=new HashMap<String, String>();
	}

}
