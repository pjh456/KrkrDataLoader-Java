package KrkrDataLoader.config;

import java.util.List;

public class AutoConfig
{
	private static AutoPathLoader loader;
	
	public static void setLoader(AutoPathLoader loader)
	{
		AutoConfig.loader = loader;
	}
	
	public void setConfigs(List<JsonPath> jsonPathList)
	{
	
	}
	
}
