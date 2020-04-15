package me.mrbluesky.zuultestexample;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

public class PreZuulFilter extends ZuulFilter {
  @Override
  public String filterType() {
    /* Define filter type
     * ERROR_TYPE, POST_TYPE, PRE_TYPE, ROUTE_TYPE
     */
    return PRE_TYPE;
  }

  @Override
  public int filterOrder() {
    /* Define the order of filter
     * FORM_BODY_WRAPPER_FILTER_ORDER
     * PRE_DECORATION_FILTER_ORDER
     * RIBBON_ROUTING_FILTER_ORDER
     * SEND_ERROR_FILTER_ORDER
     * SEND_FORWARD_FILTER_ORDER
     * SEND_RESPONSE_FILTER_ORDER
     * SIMPLE_HOST_ROUTING_FILTER_ORDER
     * SERVLET_30_WRAPPER_FILTER_ORDER
     * SERVLET_DETECTION_FILTER_ORDER
     */
    return PRE_DECORATION_FILTER_ORDER - 1;
  }

  @Override
  public boolean shouldFilter() {
    /* Define whether the filter is executable or not
     */
    return true;
  }

  @Override
  public Object run() throws ZuulException {
    /* To do
     */
    RequestContext context = RequestContext.getCurrentContext();
    HttpServletRequest request = context.getRequest();

    if (!request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")) {
      context.setSendZuulResponse(false);
      context.setResponseStatusCode(HttpStatus.SC_FORBIDDEN);
    }

    return null;
  }
}