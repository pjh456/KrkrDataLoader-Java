package KrkrDataLoader.config;

import KrkrDataLoader.core.KrkrUtils;
import com.google.gson.JsonObject;
import com.sun.jdi.InvalidTypeException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class AutoPathLoader
{
	public JsonObject data;
	private final Stack<JsonPath> pathStack = new Stack<>();
	
	public AutoPathLoader(JsonObject data)
	{
		this.data = data;
		JsonPath root = new JsonPath(data);
		pathStack.add(root);
	}
	
	public AutoPathLoader(File file)
	throws IOException, InvalidTypeException
	{ this(KrkrUtils.loadJsonFile(file)); }
	
	public AutoPathLoader(String path)
	throws IOException, InvalidTypeException
	{ this(new File(path)); }
	
	public JsonPath getCurrentPath() { return pathStack.peek(); }
	
	public JsonPath getParent() { return getCurrentPath().parent == null ? null : getCurrentPath().parent; }
	
	public JsonPath getChild(String name) { return getParent().getChild(name); }
	
	public JsonPath getChild(int index) { return getParent().getChild(index); }
	
	public List<JsonPath> listChildren() { return getCurrentPath().listChildren(); }
	
	public List<JsonPath> listPath() { return getCurrentPath().listPath(); }
	
	public JsonPath gotoChild(String name)
	{
		JsonPath child = getCurrentPath().getChild(name);
		if(child != null) { pathStack.add(child); }
		return child;
	}
	
	public JsonPath gotoChild(int index)
	{
		JsonPath child = getCurrentPath().getChild(index);
		if(child != null) { pathStack.add(child); }
		return child;
	}
	
	public JsonPath gotoParent()
	{
		if(getCurrentPath().parent != null)
		{
			pathStack.pop();
			return getCurrentPath();
		}
		else { return null; }
	}
	
	public List<JsonPath> getConfigFields()
	{
		//TODO:剖分路径，作为List<String>传入SingleConfig
		return null;
	}
}
