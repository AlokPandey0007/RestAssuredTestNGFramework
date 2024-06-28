package base;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class RestObject {
	
	private  Object body;
	private Map<String,Object > pathParams;
	private Map<String,Object > queryParams;
	private MultipartObject multipartData;
	
	public RestObject()
	{
		body = new Object();
		pathParams= new HashMap<String, Object>();
		queryParams= new HashMap<String, Object>();
		multipartData= new  MultipartObject();
	}
	
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
	public Map<String, Object> getPathParams() {
		return pathParams;
	}
	public void setPathParams(Map<String, Object> pathParams) {
		this.pathParams = pathParams;
	}
	public Map<String, Object> getQueryParams() {
		return queryParams;
	}
	public void setQueryParams(Map<String, Object> queryParams) {
		this.queryParams = queryParams;
	}
	public MultipartObject getMultipartData() {
		return multipartData;
	}
	public void setMultipartData(MultipartObject multipartData) {
		this.multipartData = multipartData;
	}
	
	

}
