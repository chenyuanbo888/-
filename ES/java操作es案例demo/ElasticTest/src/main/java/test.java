import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * 
 */

/**  
* @Title: test.java
* @Package:
* @Description:
* @author:陈远波 
* @date:2019年1月20日
* @version:V1.0  
*/
public class test {
  
	public static void main(String[] args) throws UnknownHostException {
		//1、指定es集群  cluster.name 是固定的key值，my-application是ES集群的名称
		Settings settings = Settings.builder().put("cluster.name", "my-application").build();
		//2.创建访问ES服务器的客户端
		TransportClient client = new PreBuiltTransportClient(settings)
				//获取es主机中节点的ip地址及端口号(以下是单个节点案例)
								.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.1.94"), 9300));
		//实现数据查询(指定_id查询) 参数分别是 索引名，类型名  id
		GetResponse response = client.prepareGet("lib3","user","1").execute().actionGet();
		//得到查询出的数据
		System.out.println(response.getSourceAsString());//打印出json数据
		client.close();//关闭客户端
	}
}
