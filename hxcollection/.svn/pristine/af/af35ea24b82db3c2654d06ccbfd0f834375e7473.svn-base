package cn.jpush.api.common.resp;

// TODO: Auto-generated Javadoc
/**
 * The Class DefaultResult.
 */
public class DefaultResult extends BaseResult {

	/**
	 * From response.
	 *
	 * @param responseWrapper the response wrapper
	 * @return the default result
	 */
	public static DefaultResult fromResponse(ResponseWrapper responseWrapper) {
		DefaultResult result = null;

		if (responseWrapper.isServerResponse()) {
			result = new DefaultResult();
		}

		result.setResponseWrapper(responseWrapper);

		return result;
	}

}
