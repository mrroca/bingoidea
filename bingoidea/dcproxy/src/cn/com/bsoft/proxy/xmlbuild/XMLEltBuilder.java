package cn.com.bsoft.proxy.xmlbuild;

import java.util.List;

import org.dom4j.Element;

public interface XMLEltBuilder {

	/**
	 * 按照节点之间的关系组成XML节点
	 * 
	 * @return Element
	 */
	public Element toXMLEltBuild();

	/**
	 * 返回主节点的表名
	 * 
	 * @return String
	 */
	public String getParentTableName();

	/**
	 * 返回检索的时间列的列名
	 * 
	 * @return String
	 */
	public String getKeyName();

	/**
	 * 以单个节点的形式返回一个节点集合
	 * 
	 * @return List
	 */
	public List<?> getElts();
}
